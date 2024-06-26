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
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryEntityRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryEntityResponse;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryRelationRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryRelationResponse;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

class TopazManagementHandlerTest {
    @Mock
    TopazManagementHandler topazManagementHandler = new TopazManagementHandler();

    @Test(groups = "a")
    void getRelation() {
        DirectoryRelationRequest directoryIdentityUserRelation = new DirectoryRelationRequest(
                "orgA-jane-eyre", "identity", "identifier",
                "orgA-jane-eyre", "user");
        DirectoryRelationResponse res = topazManagementHandler.getRelation(directoryIdentityUserRelation);
        assertNotNull(res);
    }

    @Test(groups = "a")
    void createRelation() {
        // Create a new object
        DirectoryEntityRequest directoryUserObject = new DirectoryEntityRequest(
                "Jane Eyre", "user", "orgA-jane-eyre");
        topazManagementHandler.createEntity(directoryUserObject);

        // Create a new identity
        DirectoryEntityRequest directoryIdentityObject = new DirectoryEntityRequest(
                "orgA-jane-eyre", "identity", "orgA-jane-eyre");
        topazManagementHandler.createEntity(directoryIdentityObject);

        // Create an identity-user relation
        DirectoryRelationRequest directoryIdentityUserRelation = new DirectoryRelationRequest(
                "orgA-jane-eyre", "identity", "identifier",
                "orgA-jane-eyre", "user");
        boolean res = topazManagementHandler.createRelation(directoryIdentityUserRelation);
        assertTrue(res);
    }

    @Test(groups = "a")
    void getObject() {
        // Create a new object
        DirectoryEntityRequest directoryUserObject = new DirectoryEntityRequest(
                "Jane Eyre", "user", "orgA-jane-eyre");
        DirectoryEntityResponse res = topazManagementHandler.getEntity(directoryUserObject);
        assertNotNull(res);
    }

    @Test(groups = "a")
    void createObject() {
        // Create a new object
        DirectoryEntityRequest directoryUserObject = new DirectoryEntityRequest(
            "Jane Eyre", "user", "orgA-jane-eyre");
        boolean res = topazManagementHandler.createEntity(directoryUserObject);
        assertTrue(res);
    }

    @Test(dependsOnGroups = "a")
    void deleteRelation() {
        DirectoryRelationRequest directoryIdentityUserRelation = new DirectoryRelationRequest(
                "orgA-jane-eyre", "identity", "identifier",
                "orgA-jane-eyre", "user");
        boolean res = topazManagementHandler.deleteRelation(directoryIdentityUserRelation);
        assertTrue(res);
        topazManagementHandler.createRelation(directoryIdentityUserRelation);
    }

    @Test(dependsOnGroups = "a")
    void deleteObject() {
        DirectoryEntityRequest directoryUserObject = new DirectoryEntityRequest(
                "user", "orgA-jane-eyre");
        boolean res = topazManagementHandler.deleteEntity(directoryUserObject);
        assertTrue(res);
        topazManagementHandler.createEntity(directoryUserObject);
    }

    @Test
    void getPolicies() {
        JSONObject jsonObject = topazManagementHandler.getPolicies();
        assertNotNull(jsonObject.get("result"));
    }
}
