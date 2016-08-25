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

import org.eurekaclinical.common.config.InjectorSupport;
import com.google.inject.Injector;

import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import org.eurekaclinical.useragreement.webapp.props.UserAgreementWebappProperties;

/**
 * Loaded up on application initialization, sets up the application with Guice
 * injector and any other bootup processes.
 *
 * @author Andrew Post
 *
 */
public class UserAgreementContextListener extends GuiceServletContextListener {

    private final UserAgreementWebappProperties properties = new UserAgreementWebappProperties();
    private InjectorSupport injectorSupport;

    @Override
    protected Injector getInjector() {
        /*
         * Must be created here in order for the modules to initialize 
         * correctly.
         */
        if (this.injectorSupport == null) {
            this.injectorSupport = new InjectorSupport(
                    new Module[]{
                        new AppModule(this.properties),
                        new ServletModule(this.properties),
                    },
                    this.properties);
        }
        return this.injectorSupport.getInjector();
    }

}
