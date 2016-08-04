package org.eurekaclinical.useragreement.webapp.client;

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

import java.net.URI;
import javax.inject.Inject;
import org.eurekaclinical.common.comm.clients.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eurekaclinical.common.comm.clients.EurekaClinicalClient;
import org.eurekaclinical.useragreement.client.comm.UserAgreementStatus;
import org.eurekaclinical.useragreement.webapp.props.UserAgreementWebappProperties;

/**
 * @author Andrew Post
 */
public class ServiceClient extends EurekaClinicalClient {

    private final String serviceUrl;

    @Inject
    public ServiceClient(UserAgreementWebappProperties inProperties) {
        super(null);
        this.serviceUrl = inProperties.getServiceUrl();
    }

    @Override
    protected String getResourceUrl() {
        return this.serviceUrl;
    }
    
    public UserAgreementStatus getUserAgreementStatus() throws ClientException {
        return doGet("/api/protected/useragreementstatuses/me", UserAgreementStatus.class);
    }
    
    public Long submitUserAgreement(UserAgreementStatus userAgreementStatus) throws ClientException {
        URI uri = doPostCreate("/api/protected/useragreementstatuses", userAgreementStatus);
        return extractId(uri);
    }
    
}
