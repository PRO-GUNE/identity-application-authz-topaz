/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.application.authz.topaz.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.wso2.carbon.identity.application.authz.topaz.handler.abs.ManagementInterface;
import org.wso2.carbon.identity.application.authz.topaz.handler.impl.TopazManagementHandler;

@Component(
        name = "identity.application.authz.topaz.component",
        immediate = true
)
public class TopazManagementServiceComponent {
    private static final Log log = LogFactory.getLog(TopazAuthorizerServiceComponent.class);

    @Activate
    protected void activate(ComponentContext ctxt) {
        try {
            TopazManagementHandler topazDirectoryHandler = new TopazManagementHandler();
            ctxt.getBundleContext().registerService(ManagementInterface.class, topazDirectoryHandler, null);
            if (log.isDebugEnabled()) {
                log.debug("Application Topaz directory handler bundle is activated");
            }
        } catch (Throwable throwable) {
            log.error("Error while starting topaz management component", throwable);
        }
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        log.debug("Application Topaz management handler bundle is deactivated.");
    }
}
