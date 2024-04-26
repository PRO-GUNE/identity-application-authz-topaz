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
import org.wso2.carbon.identity.application.authz.topaz.handler.abs.DirectoryItemInterface;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * A class to capture the relations in the Topaz directory.
 * Has the attributes that are required to create a directory relations in the Topaz authorizer.
 */
public class DirectoryRelation implements DirectoryItemInterface {
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
        this.etag = jsonObject.getString("etag");
        this.objectId = jsonObject.getString("objectId");
        this.objectType = jsonObject.getString("objectType");
        this.relation = jsonObject.getString("relation");
        this.subjectId = jsonObject.getString("subjectId");
        this.subjectType = jsonObject.getString("subjectType");
        this.subjectRelation = jsonObject.getString("subjectRelation");
    }

    public String parseToQueryParams() {
        String queryString =
                "?object_type=" + this.objectType + "&object_id=" + this.objectId + "&relation=" + this.relation +
                        "&subject_type=" + this.subjectType + "&subject_id=" + this.subjectId + "&subject_relation=" +
                        this.subjectRelation;
        return URLEncoder.encode(queryString, StandardCharsets.UTF_8);
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

        relations.put("etag", this.etag);
        relations.put("object_id", this.objectId);
        relations.put("object_type", this.objectType);
        relations.put("relation", this.relation);
        relations.put("subject_type", this.subjectType);
        relations.put("subject_relation", this.subjectRelation);
        relations.put("subject_id", this.subjectId);

        jsonObject.put("relation", relations);

        return jsonObject;
    }


}