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

import org.testng.annotations.BeforeClass;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryEntityRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryRelationRequest;

public class TopazEvalTest {

    @BeforeClass
    void setup() {
        TopazManagementHandler topazManagementHandler = new TopazManagementHandler();
        // Create a new organization
        DirectoryEntityRequest directoryOrgObject = new DirectoryEntityRequest(
                "string", "orgA", "organization", "orgA");
        topazManagementHandler.createObject(directoryOrgObject);

        // Create a new object
        DirectoryEntityRequest directoryUserObject = new DirectoryEntityRequest(
                "string", "Jane Eyre", "user", "orgA-jane-eyre");
        topazManagementHandler.createObject(directoryUserObject);

        // Create a new identity
        DirectoryEntityRequest directoryIdentityObject = new DirectoryEntityRequest(
                "string", "Jane Eyre ID", "identity", "orgA-jane-eyre");
        topazManagementHandler.createObject(directoryIdentityObject);

        // Create a new group
        DirectoryEntityRequest directoryGroupObject = new DirectoryEntityRequest(
                "string", "Group Hello", "group", "orgA-group-hello");
        topazManagementHandler.createObject(directoryGroupObject);

        // Create a new role
        DirectoryEntityRequest directoryRoleObject = new DirectoryEntityRequest(
                "string", "Hello Reader", "role", "orgA-role-hello-resource-reader");
        topazManagementHandler.createObject(directoryRoleObject);

        // Create a new resource
        DirectoryEntityRequest directoryAppObject = new DirectoryEntityRequest(
                "string", "Hello", "resource", "orgA-hello-resource");
        topazManagementHandler.createObject(directoryAppObject);

        // Create an org-user relation
        DirectoryRelationRequest directoryOrgUserRelation = new DirectoryRelationRequest(
                "string", "orgA", "organization", "org_user",
                "orgA-jane-eyre", "user");
        topazManagementHandler.createRelation(directoryOrgUserRelation);

        // Create an org-identity relation
        DirectoryRelationRequest directoryOrgIdentityRelation = new DirectoryRelationRequest(
                "string", "orgA", "organization", "org_identity",
                "orgA-jane-eyre", "identity");
        topazManagementHandler.createRelation(directoryOrgIdentityRelation);

        // Create an org-role relation
        DirectoryRelationRequest directoryOrgRoleRelation = new DirectoryRelationRequest(
                "string", "orgA", "organization", "org_role",
                "orgA-role-hello-resource-reader", "role");
        topazManagementHandler.createRelation(directoryOrgRoleRelation);

        // Create an org-resource relation
        DirectoryRelationRequest directoryOrgResourceRelation = new DirectoryRelationRequest(
                "string", "orgA", "organization", "org_resource",
                "orgA-hello-resource", "resource");
        topazManagementHandler.createRelation(directoryOrgResourceRelation);

        // Create an identity-user relation
        DirectoryRelationRequest directoryIdentityUserRelation = new DirectoryRelationRequest(
                "string", "orgA-jane-eyre", "identity", "identifier",
                "orgA-jane-eyre", "user");
        topazManagementHandler.createRelation(directoryIdentityUserRelation);

        // Create a user-group relation
        DirectoryRelationRequest directoryUserGroupRelation = new DirectoryRelationRequest(
                "string", "orgA-group-hello", "group", "member",
                "orgA-jane-eyre", "user");
        topazManagementHandler.createRelation(directoryUserGroupRelation);

        // Create a group-role relation
        DirectoryRelationRequest directoryRoleGroupRelation = new DirectoryRelationRequest(
                "string", "orgA-role-hello-resource-reader", "role", "assigned",
                "orgA-group-hello", "group", "member");
        topazManagementHandler.createRelation(directoryRoleGroupRelation);

        // Create a role-resource relation
        DirectoryRelationRequest directoryRoleAppRelation = new DirectoryRelationRequest(
                "string", "orgA-hello-resource", "resource", "reader",
                "orgA-role-hello-resource-reader", "role", "assigned");
        topazManagementHandler.createRelation(directoryRoleAppRelation);
    }


}
