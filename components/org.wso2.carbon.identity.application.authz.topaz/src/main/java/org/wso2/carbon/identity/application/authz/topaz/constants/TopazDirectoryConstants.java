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
 * Class contains constants used by the Directory.
 */
public class TopazDirectoryConstants {
    public static final String HTTPS_DIRECTORY = "https://localhost:9393/api/v3/directory";

    public static final String HTTPS_DIRECTORY_CHECK = HTTPS_DIRECTORY + "/check";
    public static final String HTTPS_DIRECTORY_GRAPH = HTTPS_DIRECTORY + "/graph";

    public static final String HTTPS_DIRECTORY_OBJECT = HTTPS_DIRECTORY + "/object";
    public static final String HTTPS_DIRECTORY_RELATION = HTTPS_DIRECTORY + "/relation";
}
