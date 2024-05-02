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

package org.wso2.carbon.identity.application.authz.topaz.handler.impl;

import org.json.JSONObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryRelation;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DecisionTreeContextObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.IsContextObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.QueryContextObject;

import org.mockito.Mock;

import org.testng.annotations.Test;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazAuthorizerConstants.IDENTITY_SUB;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazAuthorizerConstants.PATH_SEPARATOR_DOT;

class TopazAuthorizerHandlerTest {
    @Mock
    TopazAuthorizerHandler topazAuthorizerHandler = new TopazAuthorizerHandler();

    void setup(){
        TopazManagementHandler topazManagementHandler = new TopazManagementHandler();
        // Create a new object
        DirectoryObject directoryUserObject = new DirectoryObject("string","Jane Eyre", "user", "jane@the-eyres.com");
        topazManagementHandler.createObject(directoryUserObject);

        // Create a new identity
        DirectoryObject directoryIdentityObject = new DirectoryObject("string","jane@the-eyres.com", "identity", "jane@the-eyres.com");
        topazManagementHandler.createObject(directoryIdentityObject);

        // Create a new group
        DirectoryObject directoryGroupObject = new DirectoryObject("string","Group Hello", "group", "group-hello");
        topazManagementHandler.createObject(directoryGroupObject);

        // Create a new role
        DirectoryObject directoryRoleObject = new DirectoryObject("string","Hello Reader", "role", "role-hello-resource-reader");
        topazManagementHandler.createObject(directoryRoleObject);

        // Create a new resource
        DirectoryObject directoryAppObject = new DirectoryObject("string","Hello", "resource", "hello-resource");
        topazManagementHandler.createObject(directoryAppObject);

        // Create an identity-user relation
        DirectoryRelation directoryIdentityUserRelation = new DirectoryRelation("string", "jane@the-eyres.com", "identity", "identifier", "jane@the-eyres.com", "user");
        topazManagementHandler.createRelation(directoryIdentityUserRelation);

        // Create a user-group relation
        DirectoryRelation directoryUserGroupRelation = new DirectoryRelation("string", "group-hello", "group", "member", "jane@the-eyres.com", "user");
        topazManagementHandler.createRelation(directoryUserGroupRelation);

        // Create a group-role relation
        DirectoryRelation directoryRoleGroupRelation = new DirectoryRelation("string", "role-hello-resource-reader", "role", "assigned", "group-hello", "group", "member");
        topazManagementHandler.createRelation(directoryRoleGroupRelation);

        // Create a role-resource relation
        DirectoryRelation directoryRoleAppRelation = new DirectoryRelation("string", "hello-resource", "resource", "reader", "role-hello-resource-reader", "role", "assigned");
        topazManagementHandler.createRelation(directoryRoleAppRelation);

    }

    @Test
    void is() {
        // Check using Authorizer - Can Jane Eyre read eyre-app?
        HashMap<String, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("resource_id","hello-resource");
        ArrayList<String> decisions = new ArrayList<>();
        decisions.add("allowed");

        IsContextObject isPolicyContextObject = new IsContextObject(IDENTITY_SUB, "jane@the-eyres.com", hashMap, decisions, "policies.GET.app.__id");
        JSONObject res = topazAuthorizerHandler.is(isPolicyContextObject);
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
        hashMap.put("resource_id","hello-resource");
        ArrayList<String> decisions = new ArrayList<>();
        decisions.add("allowed");

        QueryContextObject queryContextObject = new QueryContextObject(IDENTITY_SUB, "jane@the-eyres.com", hashMap, query, input, decisions, "", false, false);
        JSONObject res = topazAuthorizerHandler.query(queryContextObject);
        System.out.println(res.toString());
        assertNotEquals(res.getJSONObject("response").toString(),"[]");
    }

    @Test
    void decisiontree() {
        // Check using Authorizer - Can Jane Eyre read eyre-app?
        HashMap<String, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("resource_id","hello-resource");
        ArrayList<String> decisions = new ArrayList<>();
        decisions.add("allowed");

        DecisionTreeContextObject decisionTreeDecisionTreeContextObject = new DecisionTreeContextObject(IDENTITY_SUB, "jane@the-eyres.com", hashMap, decisions, "policies.GET", PATH_SEPARATOR_DOT);
        topazAuthorizerHandler.decisiontree(decisionTreeDecisionTreeContextObject);
    }
}