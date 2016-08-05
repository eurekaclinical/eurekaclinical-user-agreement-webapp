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

import javax.inject.Inject;
import org.eurekaclinical.common.comm.clients.Route;
import org.eurekaclinical.common.comm.clients.RouterTable;
import org.eurekaclinical.useragreement.client.EurekaClinicalUserAgreementProxyClient;

/**
 *
 * @author Andrew Post
 */
public class ServiceClientRouterTable implements RouterTable {

    private final EurekaClinicalUserAgreementProxyClient client;

    @Inject
    public ServiceClientRouterTable(EurekaClinicalUserAgreementProxyClient inClient) {
        this.client = inClient;
    }
    
    @Override
    public Route[] load() {
        return new Route[]{new Route("/", "/api/protected/", this.client)};
    }
    
}
