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

package org.wso2.carbon.identity.application.authz.topaz.handler.util;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Interface used to implement the Https Handler.
 * This includes the sending GET, POST and DELETE requests.
 */
public interface HttpsInterface {

    /**
     * Sends a GET request to a given url.
     *
     * @param url URL to where the request should be sent.
     * @return the response of the request
     * @throws IOException if the request made is invalid.
     */
    String sendGETRequest(String url) throws IOException;

    /**
     * Sends a POST request to the given url with the given JSON object in the request body.
     *
     * @param url URL to where the request should be sent.
     * @param jsonObject JSON object to be sent in the request body.
     * @return the response of the request.
     * @throws IOException if the request is invalid.
     */
    String sendPOSTRequest(String url, JSONObject jsonObject) throws IOException;

    /**
     * Sends a DELETE request to the given url.
     *
     * @param url URL to where the request should be sent.
     * @return the status code of the response.
     * @throws IOException if the request is invalid.
     */
    int sendDELETERequest(String url) throws IOException;
}
