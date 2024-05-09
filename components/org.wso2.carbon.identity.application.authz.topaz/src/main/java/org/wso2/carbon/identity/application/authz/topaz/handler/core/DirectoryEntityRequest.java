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
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.ETAG_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.OBJECT_ID_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.OBJECT_TYPE_KEY;

/**
 * A class to capture the objects in the Topaz directory.
 * Has the attributes that are required to create a directory object in the Topaz authorizer.
 */
public class DirectoryEntityRequest implements DirectoryRequestInterface {
    private final String etag;
    private final String displayName;
    private final String objectType;
    private final String objectId;

    /**
     * @param etag any random string.
     * @param displayName name that the directory object is displayed with.
     * @param objectType the type of the directory object.
     * @param objectId the unique id of the directory object.
     */
    public DirectoryEntityRequest(String etag, String displayName, String objectType, String objectId) {
        this.etag = etag;
        this.displayName = displayName;
        this.objectId = objectId;
        this.objectType = objectType;
    }

    public DirectoryEntityRequest(String objectType, String objectId) {
        this.etag = "";
        this.displayName = "";
        this.objectId = objectId;
        this.objectType = objectType;
    }

    public DirectoryEntityRequest(JSONObject jsonObject) {
        JSONObject result = jsonObject.getJSONObject("result");
        this.etag = result.getString(ETAG_KEY);
        this.displayName = result.getString(DISPLAY_NAME_KEY);
        this.objectId = result.getString("id");
        this.objectType = result.getString("type");
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
        object.put(ETAG_KEY, etag);
        object.put(DISPLAY_NAME_KEY, displayName);
        object.put("id", objectId);
        object.put("type", objectType);

        jsonObject.put("object", object);

        return jsonObject;
    }

    public String parseToQueryParams() {
        String queryString = String.format("?%s=%s&%s=%s", OBJECT_TYPE_KEY, objectType, OBJECT_ID_KEY, objectId);
        return URLEncoder.encode(queryString, StandardCharsets.UTF_8);
    }

    public String parseToPathParams() {
        return String.format("/%s/%s",
                URLEncoder.encode(objectType, StandardCharsets.UTF_8),
                URLEncoder.encode(objectId, StandardCharsets.UTF_8));
    }
}
