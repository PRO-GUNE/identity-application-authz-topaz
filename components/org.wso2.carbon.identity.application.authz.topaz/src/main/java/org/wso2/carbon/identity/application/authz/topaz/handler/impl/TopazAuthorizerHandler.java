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
import org.wso2.carbon.identity.application.authz.topaz.constants.TopazAuthorizerConstants;
import org.wso2.carbon.identity.application.authz.topaz.handler.abs.AuthorizerInterface;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.PolicyContextObject;

import java.io.IOException;

/**
 * Handle sending the is, query and decisiontree requests to the Topaz authorizer.
 */
public class TopazAuthorizerHandler implements AuthorizerInterface {
    private final HttpsHandler httpsHandler;
    private final boolean isDebug;

    public TopazAuthorizerHandler() {
        this.isDebug = false;
        this.httpsHandler = new HttpsHandler(false);
    }

    public TopazAuthorizerHandler(boolean isDebug) {
        this.isDebug = isDebug;
        this.httpsHandler = new HttpsHandler(isDebug);
    }

    @Override
    public JSONObject is(PolicyContextObject policyContextObject) {
        JSONObject jsonObject = policyContextObject.parseToJSON();
        try {
            String response = httpsHandler.sendPOSTRequest(TopazAuthorizerConstants.HTTPS_AUTHORIZER_IS, jsonObject);
            if (isDebug) {
                System.out.println(response);
            }
            return new JSONObject(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONObject query(PolicyContextObject policyContextObject) {
        JSONObject jsonObject = policyContextObject.parseToJSON();
        try {
            String response = httpsHandler.sendPOSTRequest(TopazAuthorizerConstants.HTTPS_AUTHORIZER_QUERY, jsonObject);
            if (isDebug) {
                System.out.println(response);
            }
            return new JSONObject(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONObject decisiontree(PolicyContextObject policyContextObject) {
        JSONObject jsonObject = policyContextObject.parseToJSON();
        try {
            String response = httpsHandler.sendPOSTRequest(TopazAuthorizerConstants.HTTPS_AUTHORIZER_DECISIONTREE,
                    jsonObject);
            if (isDebug) {
                System.out.println(response);
            }
            return new JSONObject(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}