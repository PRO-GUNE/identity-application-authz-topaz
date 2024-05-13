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

package org.wso2.carbon.identity.application.authz.topaz.handler.core;

/**
 * Class to model the response of a Directory Relation Request.
 */
public class DirectoryRelationResponse {

    private String entityId;
    private String entityType;
    private String relation;
    private String subjectId;
    private String subjectType;
    private String subjectRelation;

    public String getEntityId() {

        return entityId;
    }

    public void setEntityId(String entityId) {

        this.entityId = entityId;
    }

    public String getEntityType() {

        return entityType;
    }

    public void setEntityType(String entityType) {

        this.entityType = entityType;
    }

    public String getRelation() {

        return relation;
    }

    public void setRelation(String relation) {

        this.relation = relation;
    }

    public String getSubjectType() {

        return subjectType;
    }

    public void setSubjectType(String subjectType) {

        this.subjectType = subjectType;
    }

    public String getSubjectId() {

        return subjectId;
    }

    public void setSubjectId(String subjectId) {

        this.subjectId = subjectId;
    }

    public String getSubjectRelation() {

        return subjectRelation;
    }

    public void setSubjectRelation(String subjectRelation) {

        this.subjectRelation = subjectRelation;
    }
}
