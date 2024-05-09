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

package org.wso2.carbon.identity.application.authz.topaz.handler.topaz;

/**
 * Class exposed by the OSGi service.
 */
public class TopazAuthzHandler {
    private final TopazAuthorizerHandler topazAuthorizerHandler = new TopazAuthorizerHandler();
    private final TopazManagementHandler topazManagementHandler = new TopazManagementHandler();
    private final TopazDirectoryHandler topazDirectoryHandler = new TopazDirectoryHandler();
    private final ObjManagementHandler objManagementHandler = new ObjManagementHandler();

    public TopazAuthorizerHandler getTopazAuthorizerHandler() {

        return topazAuthorizerHandler;
    }

    public TopazDirectoryHandler getTopazDirectoryHandler() {

        return topazDirectoryHandler;
    }

    public TopazManagementHandler getTopazManagementHandler() {

        return topazManagementHandler;
    }

    public ObjManagementHandler getObjManagementHandler() {

        return objManagementHandler;
    }
}
