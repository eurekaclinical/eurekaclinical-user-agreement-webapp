package org.eurekaclinical.useragreement.webapp.servlet;

/*-
 * #%L
 * Eureka! Clinical User Agreement Webapp
 * %%
 * Copyright (C) 2016 Emory University
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eurekaclinical.common.comm.clients.ClientException;
import org.eurekaclinical.useragreement.client.EurekaClinicalUserAgreementProxyClient;
import org.eurekaclinical.useragreement.client.comm.UserAgreementStatus;

/**
 *
 * @author Andrew Post
 */
@Singleton
public class AgreeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final EurekaClinicalUserAgreementProxyClient client;

    @Inject
    public AgreeServlet(EurekaClinicalUserAgreementProxyClient inClient) {
        this.client = inClient;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String service = req.getParameter("service");
        String fullname = req.getParameter("fullname");
        if (fullname == null) {
            throw new ServletException("fullname must be specified");
        }
        String userAgreementIdStr = req.getParameter("useragreementid");
        if (userAgreementIdStr == null) {
            throw new ServletException("The user agreement id must be specified");
        }
        Long userAgreementId;
        try {
            userAgreementId = Long.valueOf(userAgreementIdStr);
        } catch (NumberFormatException ex) {
            throw new ServletException("The user agreement id must be a long");
        }
        
        UserAgreementStatus status = new UserAgreementStatus();
        status.setUsername(req.getRemoteUser());
        status.setFullname(fullname);
        status.setUserAgreement(userAgreementId);
        try {
            this.client.submitUserAgreement(status);
        } catch (ClientException ex) {
            throw new ServletException("User agreement submission failed", ex);
        }
        resp.getWriter().println("Agree");
        resp.sendRedirect(service);
    }
}
