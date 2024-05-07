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
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DecisionTreeContextObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryRelation;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryRequestObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.IsContextObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.QueryContextObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface for managing object classes used for management and evaluation.
 */
public interface ObjManagementInterface {
    DirectoryObject createDirectoryObject(String etag, String displayName, String objectType, String objectId);
    DirectoryObject createDirectoryObject(JSONObject jsonObject);

    DirectoryRelation createDirectoryRelation(
            String etag,
            String objectId,
            String objectType,
            String relation,
            String subjectId,
            String subjectType,
            String subjectRelation);

    DirectoryRelation createDirectoryRelation(
            String etag,
            String objectId,
            String objectType,
            String relation,
            String subjectId,
            String subjectType);

    DirectoryRelation createDirectoryRelation(JSONObject jsonObject);

    DirectoryRequestObject createDirectoryRequestObject(
            String subjectType,
            String subjectId,
            String subjectRelation,
            String objectType,
            String objectId,
            String relation);

    IsContextObject createIsContextObject(
            String identityType,
            String identityId,
            HashMap<String, Object> resourceContext,
            ArrayList<String> decisions,
            String policyPath);

    QueryContextObject createQueryContextObject(
            String identityType,
            String identityId,
            HashMap<String, Object> resourceContext,
            String query,
            HashMap<String, Object> input,
            ArrayList<String> decisions,
            String policyPath,
            boolean metrics,
            boolean traceSummary);

    DecisionTreeContextObject createDecisionTreeContextObject(
            String identityType,
            String identityId,
            HashMap<String, Object> resourceContext,
            ArrayList<String> decisions,
            String policyPath,
            String pathSeparator);
}
