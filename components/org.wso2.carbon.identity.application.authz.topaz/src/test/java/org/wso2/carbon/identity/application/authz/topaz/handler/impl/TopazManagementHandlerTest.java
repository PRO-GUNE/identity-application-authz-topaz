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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryRelation;

import static org.junit.jupiter.api.Assertions.*;

class TopazManagementHandlerTest {
    @Mock
    TopazManagementHandler topazManagementHandler;

    @BeforeEach
    void setup(){
        topazManagementHandler = new TopazManagementHandler();
    }

    @Test
    void getRelation() {
        DirectoryRelation directoryIdentityUserRelation = new DirectoryRelation("string", "jane@the-eyres.com", "identity", "identifier", "jane@the-eyres.com", "user");
        DirectoryRelation res = topazManagementHandler.getRelation(directoryIdentityUserRelation);
        assertNotNull(res);
    }

    @Test
    void createRelation() {
        // Create a new object
        DirectoryObject directoryUserObject = new DirectoryObject("string","Jane Eyre", "user", "jane@the-eyres.com");
        topazManagementHandler.createObject(directoryUserObject);

        // Create a new identity
        DirectoryObject directoryIdentityObject = new DirectoryObject("string","jane@the-eyres.com", "identity", "jane@the-eyres.com");
        topazManagementHandler.createObject(directoryIdentityObject);

        // Create an identity-user relation
        DirectoryRelation directoryIdentityUserRelation = new DirectoryRelation("string", "jane@the-eyres.com", "identity", "identifier", "jane@the-eyres.com", "user");
        boolean res = topazManagementHandler.createRelation(directoryIdentityUserRelation);
        assertTrue(res);
    }

    @Test
    void deleteRelation() {
        DirectoryRelation directoryIdentityUserRelation = new DirectoryRelation("string", "jane@the-eyres.com", "identity", "identifier", "jane@the-eyres.com", "user");
        boolean res = topazManagementHandler.deleteRelation(directoryIdentityUserRelation);
        assertTrue(res);
    }

    @Test
    void getObject() {
        // Create a new object
        DirectoryObject directoryUserObject = new DirectoryObject("string","Jane Eyre", "user", "jane@the-eyres.com");
        DirectoryObject res = topazManagementHandler.getObject(directoryUserObject);
        assertNotNull(res);
    }

    @Test
    void createObject() {
        // Create a new object
        DirectoryObject directoryUserObject = new DirectoryObject("string","Jane Eyre", "user", "jane@the-eyres.com");
        boolean res = topazManagementHandler.createObject(directoryUserObject);
        assertTrue(res);
    }

    @Test
    void deleteObject() {
        DirectoryObject directoryUserObject = new DirectoryObject("string","Jane Eyre", "user", "jane@the-eyres.com");
        boolean res = topazManagementHandler.deleteObject(directoryUserObject);
        assertTrue(res);
    }

    @Test
    void getPolicies() {
        JSONObject jsonObject = topazManagementHandler.getPolicies();
        assertNotNull(jsonObject.get("result"));
    }
}