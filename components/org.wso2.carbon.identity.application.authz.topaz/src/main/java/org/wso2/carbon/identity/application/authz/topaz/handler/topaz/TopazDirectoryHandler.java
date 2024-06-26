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


import org.json.JSONObject;
import org.wso2.carbon.identity.application.authz.topaz.constants.TopazDirectoryConstants;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryAuthzRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryGraphAuthzResponse;
import org.wso2.carbon.identity.application.authz.topaz.handler.models.DirectoryInterface;
import org.wso2.carbon.identity.application.authz.topaz.handler.util.HttpsHandler;
import org.wso2.carbon.identity.application.authz.topaz.handler.util.HttpsInterface;

import java.io.IOException;

import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.ENTITY_TYPE_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.RELATION_KEY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazKeyConstants.SUBJECT_TYPE_KEY;

/**
 * Handle sending the check and graph requests to the Topaz directory.
 * Implements the Directory interface.
 */
public class TopazDirectoryHandler implements DirectoryInterface {
    private final HttpsInterface httpsHandler;

    public TopazDirectoryHandler() {
        this.httpsHandler = new HttpsHandler();
    }

    @Override
    public boolean check(DirectoryAuthzRequest directoryAuthzRequest) {
        JSONObject jsonObject = directoryAuthzRequest.parseToJSON();

        try {
            String response = httpsHandler.sendPOSTRequest(TopazDirectoryConstants.HTTPS_DIRECTORY_CHECK, jsonObject);
            JSONObject checkObject = new JSONObject(response);
            return checkObject.getBoolean("check");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DirectoryGraphAuthzResponse graph(DirectoryAuthzRequest directoryAuthzRequest) {
        JSONObject jsonObject = directoryAuthzRequest.parseToJSON();
        String baseURL = TopazDirectoryConstants.HTTPS_DIRECTORY_GRAPH + String.format("/%s/%s/%s",
                jsonObject.getString(ENTITY_TYPE_KEY),
                jsonObject.getString(RELATION_KEY),
                jsonObject.getString(SUBJECT_TYPE_KEY));
        String queryParams = directoryAuthzRequest.parseToQueryParamsForGraph();

        String url = baseURL + queryParams;
        try {
            String response = httpsHandler.sendGETRequest(url);
            JSONObject res = new JSONObject(response);
            DirectoryGraphAuthzResponse directoryGraphAuthzResponse = new DirectoryGraphAuthzResponse();
            directoryGraphAuthzResponse.setResults(res.getJSONArray("results").toList());

            return directoryGraphAuthzResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
