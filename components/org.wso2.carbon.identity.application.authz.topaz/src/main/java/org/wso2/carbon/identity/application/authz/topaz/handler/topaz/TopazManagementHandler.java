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
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryEntityRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryRelationRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.models.ManagementInterface;
import org.wso2.carbon.identity.application.authz.topaz.handler.util.HttpsHandler;

import java.io.IOException;

import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazAuthorizerConstants.HTTPS_AUTHORIZER_POLICY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazDirectoryConstants.HTTPS_DIRECTORY_OBJECT;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazDirectoryConstants.HTTPS_DIRECTORY_RELATION;

/**
 * Handle sending the entity management operations of the Directory.
 * Implements the Management interface.
 */
public class TopazManagementHandler implements ManagementInterface {
    private final HttpsHandler httpsHandler;

    public TopazManagementHandler() {
        this.httpsHandler = new HttpsHandler();
    }

    @Override
    public DirectoryRelationRequest getRelation(DirectoryRelationRequest directoryRelationRequest) {
        String queryParams = directoryRelationRequest.parseToQueryParams();
        String url = HTTPS_DIRECTORY_RELATION + queryParams;

        try {
            String response =  httpsHandler.sendGETRequest(url);
            JSONObject directoryRelationJSON = new JSONObject(response);
            return new DirectoryRelationRequest(directoryRelationJSON);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createRelation(DirectoryRelationRequest directoryRelationRequest) {
        JSONObject jsonObject = directoryRelationRequest.parseToJSON();
        try {
            httpsHandler.sendPOSTRequest(HTTPS_DIRECTORY_RELATION, jsonObject);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteRelation(DirectoryRelationRequest directoryRelationRequest) {
        String queryParams = directoryRelationRequest.parseToQueryParams();
        String url = HTTPS_DIRECTORY_RELATION + queryParams;

        try {
            int status = this.httpsHandler.sendDELETERequest(url);
            return status == 200;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DirectoryEntityRequest getObject(DirectoryEntityRequest directoryEntityRequest) {
        String pathParams = directoryEntityRequest.parseToPathParams();
        String url = HTTPS_DIRECTORY_OBJECT + pathParams;

        try {
            String response = httpsHandler.sendGETRequest(url);
            JSONObject directoryObjectJSON = new JSONObject(response);
            return new DirectoryEntityRequest(directoryObjectJSON);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createObject(DirectoryEntityRequest directoryEntityRequest) {
        JSONObject jsonObject = directoryEntityRequest.parseToJSON();
        try {
            httpsHandler.sendPOSTRequest(HTTPS_DIRECTORY_OBJECT, jsonObject);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteObject(DirectoryEntityRequest directoryEntityRequest) {
        String pathParams = directoryEntityRequest.parseToPathParams();
        String url = HTTPS_DIRECTORY_OBJECT + pathParams;

        try {
            int status = this.httpsHandler.sendDELETERequest(url);
            return status == 200;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONObject getPolicies() {
        try {
            String res = httpsHandler.sendGETRequest(HTTPS_AUTHORIZER_POLICY);
            return new JSONObject(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
