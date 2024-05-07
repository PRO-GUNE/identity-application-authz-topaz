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
import org.wso2.carbon.identity.application.authz.topaz.handler.abs.ObjManagementInterface;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DecisionTreeContextObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryRelation;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryRequestObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.IsContextObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.QueryContextObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to handle creating objects used for management and evaluation.
 */
public class ObjManagementHandler implements ObjManagementInterface {

    @Override
    public DirectoryObject createDirectoryObject(String etag, String displayName, String objectType, String objectId) {

        return new DirectoryObject(etag, displayName, objectType, objectId);
    }

    @Override
    public DirectoryObject createDirectoryObject(JSONObject jsonObject) {

        return new DirectoryObject(jsonObject);
    }

    @Override
    public DirectoryRelation createDirectoryRelation(String etag, String objectId, String objectType, String relation,
                                                     String subjectId, String subjectType, String subjectRelation) {

        return new DirectoryRelation(etag, objectId, objectType, relation, subjectId, subjectType, subjectRelation);
    }

    @Override
    public DirectoryRelation createDirectoryRelation(String etag, String objectId, String objectType, String relation,
                                                     String subjectId, String subjectType) {

        return new DirectoryRelation(etag, objectId, objectType, relation, subjectId, subjectType);
    }

    @Override
    public DirectoryRelation createDirectoryRelation(JSONObject jsonObject) {

        return new DirectoryRelation(jsonObject);
    }

    @Override
    public DirectoryRequestObject createDirectoryRequestObject(String subjectType, String subjectId,
                                                               String subjectRelation, String objectType,
                                                               String objectId, String relation) {

        return new DirectoryRequestObject(subjectType, subjectId, subjectRelation, objectType, objectId, relation);
    }

    @Override
    public IsContextObject createIsContextObject(String identityType, String identityId,
                                                 HashMap<String, Object> resourceContext, ArrayList<String> decisions,
                                                 String policyPath) {

        return new IsContextObject(identityType, identityId, resourceContext, decisions, policyPath);
    }

    @Override
    public QueryContextObject createQueryContextObject(String identityType, String identityId,
                                                       HashMap<String, Object> resourceContext, String query,
                                                       HashMap<String, Object> input, ArrayList<String> decisions,
                                                       String policyPath, boolean metrics, boolean traceSummary) {

        return new QueryContextObject(identityType, identityId, resourceContext, query, input, decisions,
                policyPath, metrics, traceSummary);
    }

    @Override
    public DecisionTreeContextObject createDecisionTreeContextObject(String identityType, String identityId,
                                                                     HashMap<String, Object> resourceContext,
                                                                     ArrayList<String> decisions, String policyPath,
                                                                     String pathSeparator) {

        return new DecisionTreeContextObject(identityType, identityId, resourceContext, decisions,
                policyPath, pathSeparator);
    }
}
