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
import java.util.HashMap;
import java.util.Map;
import org.eurekaclinical.common.config.AbstractServletModule;
import org.eurekaclinical.common.servlet.DestroySessionServlet;
import org.eurekaclinical.common.servlet.PostMessageLoginServlet;
import org.eurekaclinical.common.servlet.ProxyServlet;
import org.eurekaclinical.useragreement.webapp.props.UserAgreementWebappProperties;
import org.eurekaclinical.useragreement.webapp.servlet.AgreeServlet;
import org.eurekaclinical.useragreement.webapp.servlet.EditServlet;
import org.eurekaclinical.useragreement.webapp.servlet.PresentServlet;

/**
 * A Guice configuration module for setting up the web infrastructure and
 * binding appropriate implementations to interfaces.
 *
 * @author Andrew Post
 *
 */
public class ServletModule extends AbstractServletModule {
    
    private final UserAgreementWebappProperties properties;

    public ServletModule(UserAgreementWebappProperties inProperties) {
        super(inProperties);
        this.properties = inProperties;
    }
    
    @Override
    protected void setupServlets() {
        serve("/protected/get-session").with(PostMessageLoginServlet.class);
        serve("/protected/edit").with(EditServlet.class);
        serve("/protected/present").with(PresentServlet.class);
        serve("/protected/agree").with(AgreeServlet.class);
        serve("/proxy-resource/*").with(ProxyServlet.class);
        serve("/destroy-session").with(DestroySessionServlet.class);
    }

    @Override
    protected Map<String, String> getCasValidationFilterInitParams() {
        Map<String, String> params = new HashMap<>();
        params.put("casServerUrlPrefix", this.properties.getCasUrl());
        params.put("serverName", this.properties.getProxyCallbackServer());
        params.put("proxyCallbackUrl", getCasProxyCallbackUrl());
        params.put("proxyReceptorUrl", getCasProxyCallbackPath());
        return params;
    }

}
