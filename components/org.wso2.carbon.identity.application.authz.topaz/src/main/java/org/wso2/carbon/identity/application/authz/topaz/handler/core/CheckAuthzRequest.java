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

import org.json.JSONObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.models.AuthorizerRequestInterface;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.IDENTITY_CONTEXT;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.POLICY_CONTEXT;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.RESOURCE_CONTEXT;

/**
 * A class to model the is authorization requests sent to the topaz authorizer.
 */
public class CheckAuthzRequest implements AuthorizerRequestInterface {
    private final String identityType;
    private final String identityId;
    private final Object resourceContext;
    private final List<String> decisions;
    private final String policyPath;

    /**
     * @param identityType the type of identity of the subject. Can be one of three types:
     *                     IDENTITY_NONE, IDENTITY_SUB, IDENTITY_JWT which are defined in TopazAuthorizerConstants.
     * @param identityId the unique identity of the subject. For correct functioning, an "identity" object type is
     *                   required in the topaz data model, and it should be related with the subject with identifier
     *                   relation.
     * @param resourceContext context to be passed for the evaluation.
     * @param decisions decisions that needed to be evaluated.
     * @param policyPath the base path of the policy. For the is endpoint this will be the path of the policy that is
     *                   evaluated. For the decisiontree endpoint this will be base path of the evaluated policies.
     */
    public CheckAuthzRequest(String identityType, String identityId, Object resourceContext,
                             List<String> decisions, String policyPath) {
        this.identityType = identityType;
        this.identityId = identityId;
        this.resourceContext = resourceContext;
        this.decisions = decisions;
        this.policyPath = policyPath;
    }

    /**
     * @return a JSON object of the following format
     * {
     *     "identity_context": {
     *         "type": "IDENTITY_TYPE_SUB",
     *         "identity": "jane@the-eyres.com"
     *     },
     *     "query":"",
     *     "input":"{}",
     *     "resource_context": {
     *         "resource_id": "hello-resource"
     *     },
     *     "policy_context": {
     *         "decisions": [
     *             "allowed"
     *         ],
     *         "path": "policies.GET.app.__id"
     *     }
     * }
     */
    @Override
    public JSONObject parseToJSON() {
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> identityContext = new LinkedHashMap<>();
        identityContext.put("type", this.identityType);
        identityContext.put("identity", this.identityId);
        jsonObject.put(IDENTITY_CONTEXT, identityContext);

        jsonObject.put(RESOURCE_CONTEXT, this.resourceContext);

        HashMap<String, Object> policyContext = new HashMap<>();
        policyContext.put("decisions", this.decisions);
        policyContext.put("path", this.policyPath);
        jsonObject.put(POLICY_CONTEXT, policyContext);

        return jsonObject;
    }

    public List<String> getDecisions() {

        return decisions;
    }

    public Object getResourceContext() {

        return resourceContext;
    }

    public String getIdentityId() {

        return identityId;
    }

    public String getIdentityType() {

        return identityType;
    }

    public String getPolicyPath() {

        return policyPath;
    }
}
