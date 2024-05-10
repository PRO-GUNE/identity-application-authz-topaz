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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.IDENTITY_CONTEXT;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.POLICY_CONTEXT;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.RESOURCE_CONTEXT;

/**
 * A class to model the query authorization requests sent to the topaz authorizer.
 */
public class QueryAuthzRequest implements AuthorizerRequestInterface {
    private final String query;
    private final HashMap<String, Object> input;
    private final String identityType;
    private final String identityId;
    private final HashMap<String, Object> resourceContext;
    private final ArrayList<String> decisions;
    private final String policyPath;
    private final boolean metrics;
    private final boolean traceSummary;

    /**
     * @param identityType the type of identity of the subject. Can be one of three types:
     *                     IDENTITY_NONE, IDENTITY_SUB, IDENTITY_JWT which are defined in TopazAuthorizerConstants.
     * @param identityId the unique identity of the subject. For correct functioning, an "identity" object type is
     *                   required in the topaz data model, and it should be related with the subject with identifier
     *                   relation.
     * @param resourceContext context to be passed for the evaluation.
     * @param query The query to be evaluated by the authorizer.
     * @param input The input to be evaluated with the query by the authorizer.
     * @param decisions decisions that needed to be evaluated.
     * @param policyPath the base path of the policy. For the is endpoint this will be the path of the policy that is
     *                   evaluated. For the decisiontree endpoint this will be base path of the evaluated policies.
     * @param metrics enables or disables sending metrics data in the response.
     * @param traceSummary enables or disables sending trace_summary data in the response.
     */
    public QueryAuthzRequest(String identityType, String identityId, HashMap<String, Object> resourceContext,
                             String query, HashMap<String, Object> input, ArrayList<String> decisions,
                             String policyPath, boolean metrics, boolean traceSummary) {
        this.query = query;
        this.input = input;
        this.identityType = identityType;
        this.identityId = identityId;
        this.resourceContext = resourceContext;
        this.decisions = decisions;
        this.policyPath = policyPath;
        this.metrics = metrics;
        this.traceSummary = traceSummary;
    }

    @Override
    public JSONObject parseToJSON() {
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> identityContext = new LinkedHashMap<>();
        identityContext.put("type", this.identityType);
        identityContext.put("identity", this.identityId);
        jsonObject.put(IDENTITY_CONTEXT, identityContext);

        jsonObject.put("query", query);
        jsonObject.put("input", input.toString());

        HashMap<String, Object> options = new LinkedHashMap<>();
        identityContext.put("metrics", this.metrics);
        identityContext.put("trace_summary", this.traceSummary);
        jsonObject.put("options", options);

        jsonObject.put(RESOURCE_CONTEXT, this.resourceContext);

        HashMap<String, Object> policyContext = new HashMap<>();
        policyContext.put("decisions", this.decisions);
        policyContext.put("path", this.policyPath);
        jsonObject.put(POLICY_CONTEXT, policyContext);

        return jsonObject;
    }

    public String getPolicyPath() {

        return policyPath;
    }

    public ArrayList<String> getDecisions() {

        return decisions;
    }

    public HashMap<String, Object> getResourceContext() {

        return resourceContext;
    }

    public String getIdentityType() {

        return identityType;
    }

    public String getIdentityId() {

        return identityId;
    }

    public HashMap<String, Object> getInput() {

        return input;
    }

    public String getQuery() {

        return query;
    }
}
