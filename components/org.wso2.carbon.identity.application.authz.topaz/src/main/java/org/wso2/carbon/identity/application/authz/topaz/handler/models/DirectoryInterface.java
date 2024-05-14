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

package org.wso2.carbon.identity.application.authz.topaz.handler.models;

import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryAuthzRequest;
import org.wso2.carbon.identity.application.authz.topaz.handler.core.DirectoryGraphAuthzResponse;

/**
 * Interface used to implement the Topaz Directory.
 * This includes the check and graph endpoints exposed by the Directory
 */
public interface DirectoryInterface {

    /**
     * Perform the check operation that returns if a given relation between a subject and object exists.
     *
     * @param directoryAuthzRequest Request to be checked using the directory check endpoint
     * @return evaluation of the directory check endpoint
     */
    boolean check(DirectoryAuthzRequest directoryAuthzRequest);

    /**
     * Perform the graph operation that checks if a given relation exists with a given subject and object type or if a
     * given relation exists between a given object and subject type.
     *
     * @param directoryAuthzRequest Request to be checked using the directory graph endpoint
     * @return a list output of all the values that conform to the requests
     */
    DirectoryGraphAuthzResponse graph(DirectoryAuthzRequest directoryAuthzRequest);
}
