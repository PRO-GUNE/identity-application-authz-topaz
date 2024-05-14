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

package org.wso2.carbon.identity.application.authz.topaz.handler.core;

import java.util.Map;

/**
 * Class to model the authorization response of the decision tree endpoint.
 */
public class DecisionTreeAuthzResponse {

    private Map<String, Object> response;

    public Map<String, Object> getResponse() {

        return response;
    }

    public void setResponse(Map<String, Object> response) {

        this.response = response;
    }

    public Map<String, Object> getPaths() {

        return (Map<String, Object>) response.get("path");
    }
}
