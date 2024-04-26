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

package org.wso2.carbon.identity.application.authz.topaz.handler.obj;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazAuthorizerConstants.PATH_SEPARATOR_DOT;

/**
 * A class to model the authorization requests sent to the topaz authorizer.
 */
public class PolicyContextObject {
    private final String identityType;
    private final String identityId;
    private final HashMap<String, Object> resourceContext;
    private final ArrayList<String> decisions;
    private final String policyPath;
    private final String pathSeparator;
    private final Boolean isOptions;

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
     * @param pathSeparator character used as the separating character when printing the path. Used with the
     *                      decisiontree endpoint.
     * @param isOptions whether the pathSeparator attribute is included or not.
     */
    public PolicyContextObject(
            String identityType,
            String identityId,
            HashMap<String, Object> resourceContext,
            ArrayList<String> decisions,
            String policyPath,
            String pathSeparator,
            Boolean isOptions) {
        this.identityType = identityType;
        this.identityId = identityId;
        this.resourceContext = resourceContext;
        this.decisions = decisions;
        this.policyPath = policyPath;
        this.pathSeparator = pathSeparator;
        this.isOptions = isOptions;
    }

    public PolicyContextObject(
            String identityType,
            String identityId,
            HashMap<String, Object> resourceContext,
            ArrayList<String> decisions,
            String policyPath) {
        this.identityType = identityType;
        this.identityId = identityId;
        this.resourceContext = resourceContext;
        this.decisions = decisions;
        this.policyPath = policyPath;
        this.pathSeparator = PATH_SEPARATOR_DOT;
        this.isOptions = false;
    }

    /**
     * @return a JSON object of the following format
     * {
     *     "identity_context": {
     *         "type": "IDENTITY_TYPE_SUB",
     *         "identity": "jane@the-eyres.com"
     *     },
     *     "resource_context": {
     *         "app_id": "eyre-app"
     *     },
     *     "policy_context": {
     *         "decisions": [
     *             "allowed"
     *         ],
     *         "path": "policies.GET.app.__id"
     *     }
     * }
     *
     */
    public JSONObject parseToJSON() {
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> identityContext = new LinkedHashMap<>();
        identityContext.put("type", this.identityType);
        identityContext.put("identity", this.identityId);
        jsonObject.put("identity_context", identityContext);

        if (this.isOptions) {
            HashMap<String, Object> options = new LinkedHashMap<>();
            identityContext.put("path_separator", this.pathSeparator);
            jsonObject.put("options", options);
        }

        jsonObject.put("resource_context", this.resourceContext);

        HashMap<String, Object> policyContext = new HashMap<>();
        policyContext.put("decisions", this.decisions);
        policyContext.put("path", this.policyPath);
        jsonObject.put("policy_context", policyContext);

        return jsonObject;
    }
}