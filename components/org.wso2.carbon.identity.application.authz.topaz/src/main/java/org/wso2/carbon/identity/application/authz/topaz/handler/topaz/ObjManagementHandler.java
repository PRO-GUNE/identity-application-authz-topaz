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

import org.wso2.carbon.identity.application.authz.topaz.handler.core.CheckAuthzRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DecisionTreeAuthzRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryAuthzRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryEntityRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryRelationRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.QueryAuthzRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.models.ObjManagementInterface;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to handle creating objects used for management and evaluation.
 */
public class ObjManagementHandler implements ObjManagementInterface {

    @Override
    public DirectoryEntityRequest createDirectoryObject(String displayName, String objectType, String objectId,
                                                        Object properties) {

        return new DirectoryEntityRequest(displayName, objectType, objectId, properties);
    }

    @Override
    public DirectoryEntityRequest createDirectoryObject(String objectType, String objectId) {

        return new DirectoryEntityRequest(objectType, objectId);
    }

    @Override
    public DirectoryRelationRequest createDirectoryRelation(String objectId, String objectType,
                                                            String relation, String subjectId, String subjectType,
                                                            String subjectRelation) {

        return new DirectoryRelationRequest(objectId, objectType, relation,
                subjectId, subjectType, subjectRelation);
    }

    @Override
    public DirectoryRelationRequest createDirectoryRelation(String objectId, String objectType,
                                                            String relation, String subjectId, String subjectType) {

        return new DirectoryRelationRequest(objectId, objectType, relation, subjectId, subjectType);
    }

    @Override
    public DirectoryAuthzRequest createDirectoryRequestObject(String subjectType, String subjectId,
                                                              String subjectRelation, String objectType,
                                                              String objectId, String relation) {

        return new DirectoryAuthzRequest(subjectType, subjectId, subjectRelation, objectType, objectId, relation);
    }

    @Override
    public CheckAuthzRequest createIsContextObject(String identityType, String identityId,
                                                   HashMap<String, Object> resourceContext, ArrayList<String> decisions,
                                                   String policyPath) {

        return new CheckAuthzRequest(identityType, identityId, resourceContext, decisions, policyPath);
    }

    @Override
    public QueryAuthzRequest createQueryContextObject(String identityType, String identityId,
                                                      HashMap<String, Object> resourceContext, String query,
                                                      HashMap<String, Object> input, ArrayList<String> decisions,
                                                      String policyPath, boolean metrics, boolean traceSummary) {

        return new QueryAuthzRequest(identityType, identityId, resourceContext, query, input, decisions,
                policyPath, metrics, traceSummary);
    }

    @Override
    public DecisionTreeAuthzRequest createDecisionTreeContextObject(String identityType, String identityId,
                                                                    HashMap<String, Object> resourceContext,
                                                                    ArrayList<String> decisions, String policyPath,
                                                                    String pathSeparator) {

        return new DecisionTreeAuthzRequest(identityType, identityId, resourceContext, decisions,
                policyPath, pathSeparator);
    }
}
