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

import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.DISPLAY_NAME_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.ENTITY_ID_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.ENTITY_TYPE_KEY;

/**
 * A class to capture the objects in the Topaz directory.
 * Has the attributes that are required to create a directory object in the Topaz authorizer.
 */
public class DirectoryEntityRequest implements DirectoryRequestInterface {
    private final String displayName;
    private final String entityType;
    private final String entityId;
    private final Object properties;

    /**
     * @param displayName name that the directory object is displayed with.
     * @param entityType the type of the directory object.
     * @param entityId the unique id of the directory object.
     */
    public DirectoryEntityRequest(String displayName, String entityType, String entityId) {
        this.displayName = displayName;
        this.entityId = entityId;
        this.entityType = entityType;
        this.properties = new LinkedHashMap<>();
    }

    public DirectoryEntityRequest(String displayName, String entityType, String entityId,
                                  Object properties) {
        this.displayName = displayName;
        this.entityId = entityId;
        this.entityType = entityType;
        this.properties = properties;
    }

    public DirectoryEntityRequest(String entityType, String entityId) {
        this.displayName = "";
        this.entityId = entityId;
        this.entityType = entityType;
        this.properties = new LinkedHashMap<>();
    }

    public DirectoryEntityResponse getResponse(JSONObject jsonObject) {
        JSONObject result = jsonObject.getJSONObject("result");
        DirectoryEntityResponse directoryEntityResponse = new DirectoryEntityResponse();

        directoryEntityResponse.setDisplayName(result.getString(DISPLAY_NAME_KEY));
        directoryEntityResponse.setEntityId(result.getString("id"));
        directoryEntityResponse.setEntityType(result.getString("type"));
        directoryEntityResponse.setProperties(result.getJSONObject("properties").toMap());

        return directoryEntityResponse;
    }

    /**
     * @return a JSON object of the following format.
     *  {
     *      "object": {
     *         "id": "string",
     *         "display_name": "string",
     *         "etag": "string",
     *         "type": "user"
     *     }
     *  }
     */
    @Override
    public JSONObject parseToJSON() {
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> object = new LinkedHashMap<>();
        object.put(DISPLAY_NAME_KEY, displayName);
        object.put("id", entityId);
        object.put("type", entityType);
        object.put("properties", properties);

        jsonObject.put("object", object);

        return jsonObject;
    }

    public String parseToQueryParams() {
        String queryString = String.format("?%s=%s&%s=%s", ENTITY_TYPE_KEY, entityType, ENTITY_ID_KEY, entityId);
        return URLEncoder.encode(queryString, StandardCharsets.UTF_8);
    }

    public String parseToPathParams() {
        return String.format("/%s/%s",
                URLEncoder.encode(entityType, StandardCharsets.UTF_8),
                URLEncoder.encode(entityId, StandardCharsets.UTF_8));
    }

    public String getEntityType() {

        return entityType;
    }

    public String getEntityId() {

        return entityId;
    }

    public String getDisplayName() {

        return displayName;
    }

    public Object getProperties() {

        return properties;
    }
}
