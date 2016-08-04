package org.eurekaclinical.useragreement.webapp.config;

/*-
 * #%L
 * Eureka! Clinical User Agreement Service
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

import com.google.inject.AbstractModule;
import org.eurekaclinical.useragreement.webapp.client.ServiceClientRouterTable;
import org.eurekaclinical.common.comm.clients.RouterTable;
import org.eurekaclinical.standardapis.props.CasEurekaClinicalProperties;
import org.eurekaclinical.useragreement.webapp.props.UserAgreementWebappProperties;

/**
 * @author Andrew Post
 */
public class AppModule extends AbstractModule {

    private final UserAgreementWebappProperties properties;
    
    public AppModule(UserAgreementWebappProperties inProperties) {
        this.properties = inProperties;
    }

    @Override
    protected void configure() {
        bind(RouterTable.class).to(ServiceClientRouterTable.class);
        bind(CasEurekaClinicalProperties.class).toInstance(this.properties);
    }
}
