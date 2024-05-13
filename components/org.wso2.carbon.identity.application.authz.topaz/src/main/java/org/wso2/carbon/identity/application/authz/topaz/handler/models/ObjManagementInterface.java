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

package org.wso2.carbon.identity.application.authz.topaz.handler.models;

import org.wso2.carbon.identity.application.authz.topaz.handler.core.CheckAuthzRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DecisionTreeAuthzRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryAuthzRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryEntityRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryRelationRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.QueryAuthzRequest;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface for managing object classes used for management and evaluation.
 */
public interface ObjManagementInterface {
    DirectoryEntityRequest createDirectoryObject(String displayName, String objectType, String objectId,
                                                 Object properties);
    DirectoryEntityRequest createDirectoryObject(String objectType, String objectId);

    DirectoryRelationRequest createDirectoryRelation(
            String objectId,
            String objectType,
            String relation,
            String subjectId,
            String subjectType,
            String subjectRelation);

    DirectoryRelationRequest createDirectoryRelation(
            String objectId,
            String objectType,
            String relation,
            String subjectId,
            String subjectType);

    DirectoryAuthzRequest createDirectoryRequestObject(
            String subjectType,
            String subjectId,
            String subjectRelation,
            String objectType,
            String objectId,
            String relation);

    CheckAuthzRequest createIsContextObject(
            String identityType,
            String identityId,
            HashMap<String, Object> resourceContext,
            ArrayList<String> decisions,
            String policyPath);

    QueryAuthzRequest createQueryContextObject(
            String identityType,
            String identityId,
            HashMap<String, Object> resourceContext,
            String query,
            HashMap<String, Object> input,
            ArrayList<String> decisions,
            String policyPath,
            boolean metrics,
            boolean traceSummary);

    DecisionTreeAuthzRequest createDecisionTreeContextObject(
            String identityType,
            String identityId,
            HashMap<String, Object> resourceContext,
            ArrayList<String> decisions,
            String policyPath,
            String pathSeparator);
}
