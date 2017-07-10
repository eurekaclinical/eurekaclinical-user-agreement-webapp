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
import com.google.inject.Injector;
import com.sun.jersey.api.client.ClientResponse;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eurekaclinical.common.comm.clients.ClientException;
import org.eurekaclinical.useragreement.client.EurekaClinicalUserAgreementProxyClient;
import org.eurekaclinical.useragreement.client.comm.Status;
import org.eurekaclinical.useragreement.client.comm.UserAgreementStatus;

/**
 *
 * @author Andrew Post
 */
@Singleton
public class PresentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private Injector injector;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String service = req.getParameter("service");
        boolean foundAndActive;
        try {
            EurekaClinicalUserAgreementProxyClient client = this.injector.getInstance(EurekaClinicalUserAgreementProxyClient.class);
            UserAgreementStatus userAgreementStatus = client.getUserAgreementStatus();
            foundAndActive = userAgreementStatus.getStatus() == Status.ACTIVE;
        } catch (ClientException ex) {
            if (ex.getResponseStatus() == ClientResponse.Status.NOT_FOUND) {
                foundAndActive = false;
            } else {
                throw new ServletException(ex);
            }
        }
        if (foundAndActive) {
            if (service != null) {
                resp.sendRedirect(service);
            } else {
                req.getServletContext().getRequestDispatcher("/WEB-INF/alreadyactive.jsp").forward(req, resp);
            }
        } else {
            req.getServletContext().getRequestDispatcher("/WEB-INF/present.jsp").forward(req, resp);
        }
    }

}
