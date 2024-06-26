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


import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.ENTITY_ID_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.ENTITY_TYPE_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.RELATION_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.SUBJECT_ID_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.SUBJECT_RELATION_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.SUBJECT_TYPE_KEY;

/**
 * A class to model the authorization requests sent to the topaz directory.
 */
public class DirectoryAuthzRequest implements DirectoryRequestInterface {
    private final String subjectType;
    private final String subjectId;
    private final String subjectRelation;
    private final String entityType;
    private final String entityId;
    private final String relation;

    public DirectoryAuthzRequest(
            String subjectType,
            String subjectId,
            String subjectRelation,
            String entityType,
            String entityId,
            String relation) {
        this.subjectType = subjectType;
        this.subjectId = subjectId;
        this.subjectRelation = subjectRelation;
        this.entityType = entityType;
        this.entityId = entityId;
        this.relation = relation;
    }

    /**
     * @return a JSON object of the following format
     * {
     *     "subject_type": "user",
     *     "subject_id": "jerry@the-smiths.com",
     *     "subject_relation": "",
     *     "object_type": "application",
     *     "object_id": "bye-app",
     *     "relation": "can_read"
     * }
     */
    @Override
    public JSONObject parseToJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("subject_type", this.subjectType);
        jsonObject.put("subject_id", this.subjectId);
        jsonObject.put("subject_relation", this.subjectRelation);
        jsonObject.put("object_type", this.entityType);
        jsonObject.put("object_id", this.entityId);
        jsonObject.put("relation", this.relation);

        return jsonObject;
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

    public String parseToQueryParamsForGraph() {
        return String.format("?%s=%s", ENTITY_ID_KEY, URLEncoder.encode(entityId, StandardCharsets.UTF_8)) +
                String.format("&%s=%s", SUBJECT_ID_KEY, URLEncoder.encode(subjectId, StandardCharsets.UTF_8)) +
                String.format("&%s=%s", SUBJECT_RELATION_KEY, URLEncoder.encode(subjectRelation,
                        StandardCharsets.UTF_8));

    }

    public String getRelation() {

        return relation;
    }

    public String getSubjectType() {

        return subjectType;
    }

    public String getEntityType() {

        return entityType;
    }

    public String getSubjectRelation() {

        return subjectRelation;
    }

    public String getSubjectId() {

        return subjectId;
    }

    public String getEntityId() {

        return entityId;
    }
}
