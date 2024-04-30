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

package org.wso2.carbon.identity.application.authz.topaz.handler.obj;

import org.json.JSONObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.abs.JSONConvertibleInterface;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.ETAG_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.OBJECT_ID_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.OBJECT_TYPE_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.RELATION_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.SUBJECT_ID_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.SUBJECT_RELATION_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.SUBJECT_TYPE_KEY;

/**
 * A class to capture the relations in the Topaz directory.
 * Has the attributes that are required to create a directory relations in the Topaz authorizer.
 */
public class DirectoryRelation implements JSONConvertibleInterface {
    private final String etag;
    private final String objectId;
    private final String objectType;
    private final String relation;
    private final String subjectId;
    private final String subjectType;
    private final String subjectRelation;

    /**
     * A relation between a subject and object. Both the subject and object are directory objects.
     *
     * @param etag a random string.
     * @param objectId the unique id of the object.
     * @param objectType the type of the object.
     * @param relation the relation of the object and the subject.
     * @param subjectId the unique id of the subject.
     * @param subjectType the type of the subject.
     * @param subjectRelation the relationship of the subject that is involved in the relation. For example:
     *                        group#member - here the subjectRelation is member
     */
    public DirectoryRelation(
            String etag,
            String objectId,
            String objectType,
            String relation,
            String subjectId,
            String subjectType,
            String subjectRelation) {
        this.etag = etag;
        this.objectId = objectId;
        this.objectType = objectType;
        this.relation = relation;
        this.subjectId = subjectId;
        this.subjectType = subjectType;
        this.subjectRelation = subjectRelation;
    }

    public DirectoryRelation(
            String etag,
            String objectId,
            String objectType,
            String relation,
            String subjectId,
            String subjectType) {
        this.etag = etag;
        this.objectId = objectId;
        this.objectType = objectType;
        this.relation = relation;
        this.subjectId = subjectId;
        this.subjectType = subjectType;
        this.subjectRelation = "";
    }

    public DirectoryRelation(JSONObject jsonObject) {
        JSONObject result = jsonObject.getJSONObject("result");
        this.etag = result.getString("etag");
        this.objectId = result.getString(OBJECT_ID_KEY);
        this.objectType = result.getString(OBJECT_TYPE_KEY);
        this.relation = result.getString(RELATION_KEY);
        this.subjectId = result.getString(SUBJECT_ID_KEY);
        this.subjectType = result.getString(SUBJECT_TYPE_KEY);
        this.subjectRelation = result.getString(SUBJECT_RELATION_KEY);
    }

    public String parseToQueryParams() {
        return String.format("?%s=%s", OBJECT_TYPE_KEY, URLEncoder.encode(objectType, StandardCharsets.UTF_8)) +
                String.format("&%s=%s", OBJECT_ID_KEY, URLEncoder.encode(objectId, StandardCharsets.UTF_8)) +
                String.format("&%s=%s",RELATION_KEY, URLEncoder.encode(relation, StandardCharsets.UTF_8)) +
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

        relations.put(ETAG_KEY, this.etag);
        relations.put(OBJECT_ID_KEY, this.objectId);
        relations.put(OBJECT_TYPE_KEY, this.objectType);
        relations.put(RELATION_KEY, this.relation);
        relations.put(SUBJECT_TYPE_KEY, this.subjectType);
        relations.put(SUBJECT_RELATION_KEY, this.subjectRelation);
        relations.put(SUBJECT_ID_KEY, this.subjectId);

        jsonObject.put("relation", relations);

        return jsonObject;
    }

}