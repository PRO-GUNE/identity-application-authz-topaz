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

import org.testng.annotations.BeforeClass;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryRelation;

public class TopazEvalTest {

    @BeforeClass
    void setup() {
        TopazManagementHandler topazManagementHandler = new TopazManagementHandler();
        // Create a new organization
        DirectoryObject directoryOrgObject = new DirectoryObject(
                "string", "orgA", "organization", "orgA");
        topazManagementHandler.createObject(directoryOrgObject);

        // Create a new object
        DirectoryObject directoryUserObject = new DirectoryObject(
                "string", "Jane Eyre", "user", "orgA-jane-eyre");
        topazManagementHandler.createObject(directoryUserObject);

        // Create a new identity
        DirectoryObject directoryIdentityObject = new DirectoryObject(
                "string", "Jane Eyre ID", "identity", "orgA-jane-eyre");
        topazManagementHandler.createObject(directoryIdentityObject);

        // Create a new group
        DirectoryObject directoryGroupObject = new DirectoryObject(
                "string", "Group Hello", "group", "orgA-group-hello");
        topazManagementHandler.createObject(directoryGroupObject);

        // Create a new role
        DirectoryObject directoryRoleObject = new DirectoryObject(
                "string", "Hello Reader", "role", "orgA-role-hello-resource-reader");
        topazManagementHandler.createObject(directoryRoleObject);

        // Create a new resource
        DirectoryObject directoryAppObject = new DirectoryObject(
                "string", "Hello", "resource", "orgA-hello-resource");
        topazManagementHandler.createObject(directoryAppObject);

        // Create an org-user relation
        DirectoryRelation directoryOrgUserRelation = new DirectoryRelation(
                "string", "orgA", "organization", "org_user",
                "orgA-jane-eyre", "user");
        topazManagementHandler.createRelation(directoryOrgUserRelation);

        // Create an org-identity relation
        DirectoryRelation directoryOrgIdentityRelation = new DirectoryRelation(
                "string", "orgA", "organization", "org_identity",
                "orgA-jane-eyre", "identity");
        topazManagementHandler.createRelation(directoryOrgIdentityRelation);

        // Create an org-role relation
        DirectoryRelation directoryOrgRoleRelation = new DirectoryRelation(
                "string", "orgA", "organization", "org_role",
                "orgA-role-hello-resource-reader", "role");
        topazManagementHandler.createRelation(directoryOrgRoleRelation);

        // Create an org-resource relation
        DirectoryRelation directoryOrgResourceRelation = new DirectoryRelation(
                "string", "orgA", "organization", "org_resource",
                "orgA-hello-resource", "resource");
        topazManagementHandler.createRelation(directoryOrgResourceRelation);

        // Create an identity-user relation
        DirectoryRelation directoryIdentityUserRelation = new DirectoryRelation(
                "string", "orgA-jane-eyre", "identity", "identifier",
                "orgA-jane-eyre", "user");
        topazManagementHandler.createRelation(directoryIdentityUserRelation);

        // Create a user-group relation
        DirectoryRelation directoryUserGroupRelation = new DirectoryRelation(
                "string", "orgA-group-hello", "group", "member",
                "orgA-jane-eyre", "user");
        topazManagementHandler.createRelation(directoryUserGroupRelation);

        // Create a group-role relation
        DirectoryRelation directoryRoleGroupRelation = new DirectoryRelation(
                "string", "orgA-role-hello-resource-reader", "role", "assigned",
                "orgA-group-hello", "group", "member");
        topazManagementHandler.createRelation(directoryRoleGroupRelation);

        // Create a role-resource relation
        DirectoryRelation directoryRoleAppRelation = new DirectoryRelation(
                "string", "orgA-hello-resource", "resource", "reader",
                "orgA-role-hello-resource-reader", "role", "assigned");
        topazManagementHandler.createRelation(directoryRoleAppRelation);
    }


}
