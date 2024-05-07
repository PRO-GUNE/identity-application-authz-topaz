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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Handles all the calls to sending GET and POST requests to the Topaz Service.
 */
public class HttpsHandler {

    /**
     * Sends a GET request to a given url.
     *
     * @param url URL to where the request should be sent.
     * @return the response of the request
     * @throws IOException if the request made is invalid.
     */
    public String sendGETRequest(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) obj.openConnection();
        urlConnection.setRequestMethod("GET");

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder stringBuilder = new StringBuilder();
            String inputLine = bufferedReader.readLine();
            while (inputLine != null) {
                stringBuilder.append(inputLine).append("\n");
                inputLine = bufferedReader.readLine();
            }

            return stringBuilder.toString();
        } finally {
            urlConnection.disconnect();
        }

    }

    /**
     * Sends a POST request to the given url with the given JSON object in the request body.
     *
     * @param url URL to where the request should be sent.
     * @param jsonObject JSON object to be sent in the request body.
     * @return the response of the request.
     * @throws IOException if the request is invalid.
     */
    public String sendPOSTRequest(String url, JSONObject jsonObject) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) obj.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);

        // Write the JSON object to the connection's output stream
        try (OutputStream outputStream = urlConnection.getOutputStream()) {
            byte[] input = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }

        // Get the response
        try (BufferedReader responseReader = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = responseReader.readLine()) != null) {
                response.append(responseLine.trim());
            }

            return response.toString();
        } finally {
            urlConnection.disconnect();
        }
    }

    /**
     * Sends a DELETE request to the given url.
     *
     * @param url URL to where the request should be sent.
     * @return the status code of the response.
     * @throws IOException if the request is invalid.
     */
    public int sendDELETERequest(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) obj.openConnection();
        urlConnection.setRequestMethod("DELETE");
        urlConnection.setRequestProperty("Accept", "application/json");

        try {
            return urlConnection.getResponseCode();
        } finally {
            urlConnection.disconnect();
        }

    }
}
