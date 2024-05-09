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
 * Class contains constants used by the Authorizer.
 */
public class TopazAuthorizerConstants {
    public static final String HTTPS_AUTHORIZER_POLICY = "https://localhost:8383/api/v2/policies";

    public static final String HTTPS_AUTHORIZER = "https://localhost:8383/api/v2/authz";

    public static final String HTTPS_AUTHORIZER_CHECK = HTTPS_AUTHORIZER + "/is";
    public static final String HTTPS_AUTHORIZER_DECISIONTREE = HTTPS_AUTHORIZER + "/decisiontree";
    public static final String HTTPS_AUTHORIZER_QUERY = HTTPS_AUTHORIZER + "/query";

    public static final String IDENTITY_NONE = "IDENTITY_TYPE_NONE";
    public static final String IDENTITY_SUB = "IDENTITY_TYPE_SUB";
    public static final String IDENTITY_JWT = "IDENTITY_TYPE_JWT";

    public static final String PATH_SEPARATOR_DOT = "PATH_SEPARATOR_DOT";

}
