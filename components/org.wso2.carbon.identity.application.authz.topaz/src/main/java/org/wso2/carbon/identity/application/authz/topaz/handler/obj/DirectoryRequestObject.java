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
import java.util.ArrayList;

/**
 * A class to model the authorization requests sent to the topaz directory.
 */
public class DirectoryRequestObject implements DirectoryItemInterface {
    private final String subjectType;
    private final String subjectId;
    private final String subjectRelation;
    private final String objectType;
    private final String objectId;
    private final String relation;

    public DirectoryRequestObject(
            String subjectType,
            String subjectId,
            String subjectRelation,
            String objectType,
            String objectId,
            String relation) {
        this.subjectType = subjectType;
        this.subjectId = subjectId;
        this.subjectRelation = subjectRelation;
        this.objectType = objectType;
        this.objectId = objectId;
        this.relation = relation;
    }

    public DirectoryRequestObject(
            String subjectType,
            String subjectId,
            String subjectRelation,
            String objectType,
            String relation) {
        this.subjectType = subjectType;
        this.subjectId = subjectId;
        this.subjectRelation = subjectRelation;
        this.objectType = objectType;
        this.objectId = "";
        this.relation = relation;
    }

    public DirectoryRequestObject(String subjectType, String objectType, String objectId, String relation) {
        this.subjectType = subjectType;
        this.subjectId = "";
        this.subjectRelation = "";
        this.objectType = objectType;
        this.objectId = objectId;
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
        jsonObject.put("object_type", this.objectType);
        jsonObject.put("object_id", this.objectId);
        jsonObject.put("relation", this.relation);

        return jsonObject;
    }

    @Override
    public String parseToQueryParams() {
        String queryString =
                "?object_type=" + this.objectType + "&object_id=" + this.objectId + "&relation=" + this.relation +
                        "&subject_type=" + this.subjectType + "&subject_id=" + this.subjectId + "&subject_relation=" +
                        this.subjectRelation;
        return URLEncoder.encode(queryString, StandardCharsets.UTF_8);
    }

    public String parseToQueryParamsForGraph() {
        ArrayList<String> keys = new ArrayList<>();
        keys.add("object_id");
        keys.add("subject_id");
        keys.add("subject_relation");

        StringBuilder queryString = new StringBuilder("?");
        JSONObject jsonObject = this.parseToJSON();
        for (String key : keys) {
            queryString.append(String.format("%s=%s&", key, jsonObject.getString(key)));
        }

        return URLEncoder.encode(queryString.toString(), StandardCharsets.UTF_8);
    }
}