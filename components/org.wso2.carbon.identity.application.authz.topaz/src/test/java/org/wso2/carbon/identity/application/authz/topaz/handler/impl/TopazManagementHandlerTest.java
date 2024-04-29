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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.DirectoryRelation;

import static org.junit.jupiter.api.Assertions.*;

class TopazManagementHandlerTest {
    @Mock
    TopazManagementHandler topazManagementHandler;

    @BeforeEach
    void setup(){
        topazManagementHandler = new TopazManagementHandler();
    }

    @Test
    void getRelation() {

    }

    @Test
    void createRelation() {

    }

    @Test
    void deleteRelation() {

    }

    @Test
    void getObject() {

    }

    @Test
    void createObject() {

    }

    @Test
    void deleteObject() {

    }

    @Test
    void getPolicies() {

    }
}