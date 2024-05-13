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

/**
 * Class to model the response of a Directory Entity Request.
 */
public class DirectoryEntityResponse {
    private String displayName;
    private String entityType;
    private String entityId;
    private Object properties;

    public String getDisplayName() {

        return displayName;
    }

    public void setDisplayName(String displayName) {

        this.displayName = displayName;
    }

    public Object getProperties() {

        return properties;
    }

    public void setProperties(Object properties) {

        this.properties = properties;
    }

    public String getEntityId() {

        return entityId;
    }

    public void setEntityId(String entityId) {

        this.entityId = entityId;
    }

    public String getEntityType() {

        return entityType;
    }

    public void setEntityType(String entityType) {

        this.entityType = entityType;
    }
}
