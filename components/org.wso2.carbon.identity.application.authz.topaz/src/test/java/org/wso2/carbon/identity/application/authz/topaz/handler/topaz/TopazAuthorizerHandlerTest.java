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

import org.json.JSONObject;
import org.mockito.Mock;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.CheckAuthzRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DecisionTreeAuthzRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.QueryAuthzRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazAuthorizerConstants.IDENTITY_SUB;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazAuthorizerConstants.PATH_SEPARATOR_DOT;

class TopazAuthorizerHandlerTest extends TopazEvalTest {
    @Mock
    TopazAuthorizerHandler topazAuthorizerHandler = new TopazAuthorizerHandler();


    @Test
    void is() {
        // Check using Authorizer - Can Jane Eyre read eyre-app?
        HashMap<String, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("resource_id", "orgA-hello-resource");
        hashMap.put("org_id", "orgA");
        ArrayList<String> decisions = new ArrayList<>();
        decisions.add("allowed");

        CheckAuthzRequest isPolicyContextObject = new CheckAuthzRequest(
                IDENTITY_SUB, "orgA-jane-eyre", hashMap, decisions, "policies.GET.app.__id");
        JSONObject res = topazAuthorizerHandler.check(isPolicyContextObject);
        JSONObject decision = (JSONObject) res.getJSONArray("decisions").get(0);
        assertTrue(decision.getBoolean("is"));
    }

    @Test
    void query() {
        String query = "x:=ds.check({\"object_type\": \"resource\",\n" +
                       "\"object_id\": input.resource.resource_id,\n" +
                       "\"relation\": \"can_read\",\n" +
                       "\"subject_type\": \"user\",\n" +
                       "\"subject_id\": input.user.id,\n" +
                       "})";

        HashMap<String, Object> input = new LinkedHashMap<>();
        HashMap<String, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("resource_id", "orgA-hello-resource");
        hashMap.put("org_id", "orgA");
        ArrayList<String> decisions = new ArrayList<>();
        decisions.add("allowed");

        QueryAuthzRequest queryAuthzRequest = new QueryAuthzRequest(
                IDENTITY_SUB, "orgA-jane-eyre", hashMap, query, input,
                decisions, "", false, false);
        JSONObject res = topazAuthorizerHandler.query(queryAuthzRequest);
        assertNotEquals(res.getJSONObject("response").toString(), "[]");
    }

    @Test
    void decisiontree() {
        // Check using Authorizer - Can Jane Eyre read eyre-app?
        HashMap<String, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("resource_id", "orgA-hello-resource");
        hashMap.put("org_id", "orgA");
        ArrayList<String> decisions = new ArrayList<>();
        decisions.add("allowed");

        DecisionTreeAuthzRequest decisionTreeAuthzRequest = new DecisionTreeAuthzRequest(
                IDENTITY_SUB, "orgA-jane-eyre", hashMap, decisions,
                "policies.GET", PATH_SEPARATOR_DOT);

        topazAuthorizerHandler.decisiontree(decisionTreeAuthzRequest);
    }
}
