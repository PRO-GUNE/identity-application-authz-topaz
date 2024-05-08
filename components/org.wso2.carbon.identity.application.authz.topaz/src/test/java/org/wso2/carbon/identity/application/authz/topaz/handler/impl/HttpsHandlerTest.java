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

package org.wso2.carbon.identity.application.authz.topaz.handler.impl;

import org.json.JSONObject;
import org.mockito.Mock;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.util.HttpsHandler;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazAuthorizerConstants.HTTPS_AUTHORIZER_POLICY;
import static org.wso2.carbon.identity.application.authz.topaz.constants.TopazDirectoryConstants.HTTPS_DIRECTORY_OBJECT;


class HttpsHandlerTest {

    @Mock
    private HttpsHandler httpsHandler = new HttpsHandler();

    @Test
    void sendGETRequest() {

        try {
            String response = httpsHandler.sendGETRequest(HTTPS_AUTHORIZER_POLICY);
            assertTrue(response.contains("result"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void sendPOSTRequest() {
        DirectoryObject directoryUserObject = new DirectoryObject(
                "string", "Jane Eyre", "user", "orgA-jane-eyre");
        JSONObject jsonObject = directoryUserObject.parseToJSON();
        try {
            String response = httpsHandler.sendPOSTRequest(HTTPS_DIRECTORY_OBJECT, jsonObject);
            assertTrue(response.contains("result"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void sendDELETERequest() {
        String url = HTTPS_DIRECTORY_OBJECT + "/" + "user" + "/" + "orgA-jane-eyre";
        try {
            int response = httpsHandler.sendDELETERequest(url);
            assertEquals(200, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
