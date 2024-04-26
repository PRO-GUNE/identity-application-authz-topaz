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

package org.wso2.carbon.identity.application.authz.topaz.handler.abs;

import org.json.JSONObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryRelation;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryRequestObject;

/**
 * Interface for managing the entity management tasks of the topaz directory.
 */
public interface ManagementInterface {
    DirectoryRelation getRelation(DirectoryRequestObject directoryRequestObject);
    void createRelation(DirectoryRelation directoryRelation);
    boolean deleteRelation(DirectoryRequestObject directoryRequestObject);

    DirectoryObject getObject(DirectoryRequestObject directoryRequestObject);
    void createObject(DirectoryObject directoryObject);
    boolean deleteObject(DirectoryRequestObject directoryRequestObject);

    JSONObject getPolicies();
}