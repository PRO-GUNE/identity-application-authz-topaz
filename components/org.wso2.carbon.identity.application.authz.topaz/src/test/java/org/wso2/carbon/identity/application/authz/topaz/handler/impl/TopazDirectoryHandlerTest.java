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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryRequestObject;

import static org.junit.jupiter.api.Assertions.*;

class TopazDirectoryHandlerTest {

    @Mock
    TopazDirectoryHandler topazDirectoryHandler;

    @BeforeEach
    void setup(){
        topazDirectoryHandler = new TopazDirectoryHandler();
    }

    @Test
    void check() {
        DirectoryRequestObject directoryRequestObject = new DirectoryRequestObject("user", "jane@the-eyres.com", "", "resource", "hello-resource", "reader");
        boolean res = topazDirectoryHandler.check(directoryRequestObject);
        assertTrue(res);
    }

    @Test
    void graph() {
        DirectoryRequestObject directoryRequestObject = new DirectoryRequestObject("user", "", "", "resource", "hello-resource", "reader");
        JSONObject res = topazDirectoryHandler.graph(directoryRequestObject);
        System.out.println(res.get("results").toString());
        assertNotEquals(res.get("results").toString(), "[]");
    }
}