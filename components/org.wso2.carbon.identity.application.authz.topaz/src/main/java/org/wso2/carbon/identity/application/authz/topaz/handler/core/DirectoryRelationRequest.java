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

import org.json.JSONObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.models.DirectoryRequestInterface;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.ENTITY_ID_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.ENTITY_TYPE_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.RELATION_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.SUBJECT_ID_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.SUBJECT_RELATION_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.SUBJECT_TYPE_KEY;

/**
 * A class to capture the relations in the Topaz directory.
 * Has the attributes that are required to create a directory relations in the Topaz authorizer.
 */
public class DirectoryRelationRequest implements DirectoryRequestInterface {
    private final String entityId;
    private final String entityType;
    private final String relation;
    private final String subjectId;
    private final String subjectType;
    private final String subjectRelation;

    /**
     * A relation between a subject and object. Both the subject and object are directory objects.
     *
     * @param entityId the unique id of the object.
     * @param entityType the type of the object.
     * @param relation the relation of the object and the subject.
     * @param subjectId the unique id of the subject.
     * @param subjectType the type of the subject.
     * @param subjectRelation the relationship of the subject that is involved in the relation. For example:
     *                        group#member - here the subjectRelation is member
     */
    public DirectoryRelationRequest(
            String entityId,
            String entityType,
            String relation,
            String subjectId,
            String subjectType,
            String subjectRelation) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.relation = relation;
        this.subjectId = subjectId;
        this.subjectType = subjectType;
        this.subjectRelation = subjectRelation;
    }

    public DirectoryRelationRequest(
            String entityId,
            String entityType,
            String relation,
            String subjectId,
            String subjectType) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.relation = relation;
        this.subjectId = subjectId;
        this.subjectType = subjectType;
        this.subjectRelation = "";
    }

    public DirectoryRelationResponse getResponse(JSONObject jsonObject) {
        JSONObject result = jsonObject.getJSONObject("result");
        DirectoryRelationResponse directoryRelationResponse = new DirectoryRelationResponse();

        directoryRelationResponse.setEntityId(result.getString(ENTITY_ID_KEY));
        directoryRelationResponse.setEntityType(result.getString(ENTITY_TYPE_KEY));
        directoryRelationResponse.setRelation(result.getString(RELATION_KEY));
        directoryRelationResponse.setSubjectId(result.getString(SUBJECT_ID_KEY));
        directoryRelationResponse.setSubjectType(result.getString(SUBJECT_TYPE_KEY));
        directoryRelationResponse.setSubjectRelation(result.getString(SUBJECT_RELATION_KEY));

        return directoryRelationResponse;
    }

    public String parseToQueryParams() {
        return String.format("?%s=%s", ENTITY_TYPE_KEY, URLEncoder.encode(entityType, StandardCharsets.UTF_8)) +
                String.format("&%s=%s", ENTITY_ID_KEY, URLEncoder.encode(entityId, StandardCharsets.UTF_8)) +
                String.format("&%s=%s", RELATION_KEY, URLEncoder.encode(relation, StandardCharsets.UTF_8)) +
                String.format("&%s=%s", SUBJECT_TYPE_KEY, URLEncoder.encode(subjectType, StandardCharsets.UTF_8)) +
                String.format("&%s=%s", SUBJECT_ID_KEY, URLEncoder.encode(subjectId, StandardCharsets.UTF_8)) +
                String.format("&%s=%s", SUBJECT_RELATION_KEY, URLEncoder.encode(subjectRelation,
                        StandardCharsets.UTF_8));

    }

    /**
     * @return a JSON object of the following format
     * {
     *      "relation": {
     *         "subject_id": "eyre-app",
     *         "subject_relation": "",
     *         "subject_type": "user",
     *         "object_type": "user",
     *         "etag": "string",
     *         "object_id": "jane@the-eyres.com",
     *         "relation": "identifier"
     *       }
     * }
     */
    @Override
    public JSONObject parseToJSON() {
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> relations = new LinkedHashMap<>();

        relations.put(ENTITY_ID_KEY, this.entityId);
        relations.put(ENTITY_TYPE_KEY, this.entityType);
        relations.put(RELATION_KEY, this.relation);
        relations.put(SUBJECT_TYPE_KEY, this.subjectType);
        relations.put(SUBJECT_RELATION_KEY, this.subjectRelation);
        relations.put(SUBJECT_ID_KEY, this.subjectId);

        jsonObject.put("relation", relations);

        return jsonObject;
    }

    public String getEntityType() {

        return entityType;
    }

    public String getEntityId() {

        return entityId;
    }

    public String getSubjectRelation() {

        return subjectRelation;
    }

    public String getSubjectId() {

        return subjectId;
    }

    public String getRelation() {

        return relation;
    }

    public String getSubjectType() {

        return subjectType;
    }
}
