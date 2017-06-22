package org.eurekaclinical.useragreement.webapp.config;

/*-
 * #%L
 * Eureka WebApp
 * %%
 * Copyright (C) 2012 - 2017 Emory University
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
import com.google.inject.ConfigurationException;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.eurekaclinical.useragreement.client.EurekaClinicalUserAgreementProxyClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Andrew Post
 */
public class ClientSessionListener implements HttpSessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientSessionListener.class);

    @Inject
    private Injector injector;

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        LOGGER.info("Creating session for " + hse.getSession().getServletContext().getContextPath());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        LOGGER.info("Destroying session for " + hse.getSession().getServletContext().getContextPath());
        try {
            this.injector.getInstance(EurekaClinicalUserAgreementProxyClient.class).close();
        } catch (ConfigurationException ce) {
        }
    }

}
