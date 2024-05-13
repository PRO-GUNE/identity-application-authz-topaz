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

package org.wso2.carbon.identity.application.authz.topaz.constants;

/**
 * Class contains constants used when parsing objects to JSON payloads.
 */
public class TopazKeyConstants {
    public static final String ENTITY_TYPE_KEY = "object_type";
    public static final String ENTITY_ID_KEY = "object_id";
    public static final String RELATION_KEY = "relation";
    public static final String SUBJECT_TYPE_KEY = "subject_type";
    public static final String SUBJECT_ID_KEY = "subject_id";
    public static final String SUBJECT_RELATION_KEY = "subject_relation";
    public static final String DISPLAY_NAME_KEY = "display_name";

    public static final String IDENTITY_CONTEXT = "identity_context";
    public static final String RESOURCE_CONTEXT = "resource_context";
    public static final String POLICY_CONTEXT = "policy_context";

}
