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

package org.wso2.carbon.identity.application.authz.topaz.handler.abs;

import org.json.JSONObject;
import org.wso2.carbon.identity.application.authz.topaz.handler.obj.PolicyContextObject;

/**
 * Interface used to implement the Topaz Authorizer.
 * This includes the is, query and decisiontree endpoints exposed by the Authorizer.
 */
public interface AuthorizerInterface {

    /**
     * Evaluate a single policy and retrieve the result.
     *
     * @param policyContextObject the request sent to the Authorizer and evaluated.
     * @return a list of the decisions and the result of the evaluation of each decision.
     */
    JSONObject is(PolicyContextObject policyContextObject);

    /**
     * Most abstract endpoint provided by the topaz authorizer.
     * Can execute a query sent with the request with the given context.
     *
     * @param policyContextObject the request sent to the Authorizer and evaluated.
     * @return a list of the results of the evaluation.
     */
    JSONObject query(PolicyContextObject policyContextObject);

    /**
     * Evaluates all the policies at a given policy path for the requested decisions.
     *
     * @param policyContextObject the request sent to the Authorizer and evaluated.
     * @return a list of the results of the evaluation of the requested decisions for the policies.
     */
    JSONObject decisiontree(PolicyContextObject policyContextObject);
}
