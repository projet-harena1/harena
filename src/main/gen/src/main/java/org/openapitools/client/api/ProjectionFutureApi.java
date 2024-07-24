/*
 * harena
 * harena
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiCallback;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.Configuration;
import org.openapitools.client.Pair;
import org.openapitools.client.ProgressRequestBody;
import org.openapitools.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.openapitools.client.model.BadRequestException;
import org.openapitools.client.model.GetPatrimoineFluxImpossibles200Response;
import org.openapitools.client.model.InternalServerException;
import java.time.LocalDate;
import org.openapitools.client.model.ResourceNotFoundException;
import org.openapitools.client.model.TooManyRequestsException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

public class ProjectionFutureApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public ProjectionFutureApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ProjectionFutureApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for getPatrimoineFluxImpossibles
     * @param nomPatrimoine nom du patrimoine avec les espaces remplacés par \&quot;_\&quot; s&#39;il y en a (required)
     * @param debut  (optional)
     * @param fin  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> flux impossibles du patrimoine actuel selon une plage de date donnée </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad request </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not found </td><td>  -  </td></tr>
        <tr><td> 429 </td><td> Too many requests to the API </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getPatrimoineFluxImpossiblesCall(String nomPatrimoine, LocalDate debut, LocalDate fin, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/patrimoines/{nom_patrimoine}/flux-impossibles"
            .replaceAll("\\{" + "nom_patrimoine" + "\\}", localVarApiClient.escapeString(nomPatrimoine.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (debut != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("debut", debut));
        }

        if (fin != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("fin", fin));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getPatrimoineFluxImpossiblesValidateBeforeCall(String nomPatrimoine, LocalDate debut, LocalDate fin, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'nomPatrimoine' is set
        if (nomPatrimoine == null) {
            throw new ApiException("Missing the required parameter 'nomPatrimoine' when calling getPatrimoineFluxImpossibles(Async)");
        }
        

        okhttp3.Call localVarCall = getPatrimoineFluxImpossiblesCall(nomPatrimoine, debut, fin, _callback);
        return localVarCall;

    }

    /**
     * obtenir une liste des flux impossibles du patrimoine dans une intervalle donnée
     * 
     * @param nomPatrimoine nom du patrimoine avec les espaces remplacés par \&quot;_\&quot; s&#39;il y en a (required)
     * @param debut  (optional)
     * @param fin  (optional)
     * @return GetPatrimoineFluxImpossibles200Response
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> flux impossibles du patrimoine actuel selon une plage de date donnée </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad request </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not found </td><td>  -  </td></tr>
        <tr><td> 429 </td><td> Too many requests to the API </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error </td><td>  -  </td></tr>
     </table>
     */
    public GetPatrimoineFluxImpossibles200Response getPatrimoineFluxImpossibles(String nomPatrimoine, LocalDate debut, LocalDate fin) throws ApiException {
        ApiResponse<GetPatrimoineFluxImpossibles200Response> localVarResp = getPatrimoineFluxImpossiblesWithHttpInfo(nomPatrimoine, debut, fin);
        return localVarResp.getData();
    }

    /**
     * obtenir une liste des flux impossibles du patrimoine dans une intervalle donnée
     * 
     * @param nomPatrimoine nom du patrimoine avec les espaces remplacés par \&quot;_\&quot; s&#39;il y en a (required)
     * @param debut  (optional)
     * @param fin  (optional)
     * @return ApiResponse&lt;GetPatrimoineFluxImpossibles200Response&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> flux impossibles du patrimoine actuel selon une plage de date donnée </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad request </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not found </td><td>  -  </td></tr>
        <tr><td> 429 </td><td> Too many requests to the API </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<GetPatrimoineFluxImpossibles200Response> getPatrimoineFluxImpossiblesWithHttpInfo(String nomPatrimoine, LocalDate debut, LocalDate fin) throws ApiException {
        okhttp3.Call localVarCall = getPatrimoineFluxImpossiblesValidateBeforeCall(nomPatrimoine, debut, fin, null);
        Type localVarReturnType = new TypeToken<GetPatrimoineFluxImpossibles200Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * obtenir une liste des flux impossibles du patrimoine dans une intervalle donnée (asynchronously)
     * 
     * @param nomPatrimoine nom du patrimoine avec les espaces remplacés par \&quot;_\&quot; s&#39;il y en a (required)
     * @param debut  (optional)
     * @param fin  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> flux impossibles du patrimoine actuel selon une plage de date donnée </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad request </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not found </td><td>  -  </td></tr>
        <tr><td> 429 </td><td> Too many requests to the API </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getPatrimoineFluxImpossiblesAsync(String nomPatrimoine, LocalDate debut, LocalDate fin, final ApiCallback<GetPatrimoineFluxImpossibles200Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = getPatrimoineFluxImpossiblesValidateBeforeCall(nomPatrimoine, debut, fin, _callback);
        Type localVarReturnType = new TypeToken<GetPatrimoineFluxImpossibles200Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getPatrimoineGraph
     * @param nomPatrimoine nom du patrimoine avec les espaces remplacés par \&quot;_\&quot; s&#39;il y en a (required)
     * @param debut  (optional)
     * @param fin  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> image montrant l&#39;évolution du graphe </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad request </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not found </td><td>  -  </td></tr>
        <tr><td> 429 </td><td> Too many requests to the API </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getPatrimoineGraphCall(String nomPatrimoine, LocalDate debut, LocalDate fin, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/patrimoines/{nom_patrimoine}/graphe"
            .replaceAll("\\{" + "nom_patrimoine" + "\\}", localVarApiClient.escapeString(nomPatrimoine.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (debut != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("debut", debut));
        }

        if (fin != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("fin", fin));
        }

        final String[] localVarAccepts = {
            "image/png", "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getPatrimoineGraphValidateBeforeCall(String nomPatrimoine, LocalDate debut, LocalDate fin, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'nomPatrimoine' is set
        if (nomPatrimoine == null) {
            throw new ApiException("Missing the required parameter 'nomPatrimoine' when calling getPatrimoineGraph(Async)");
        }
        

        okhttp3.Call localVarCall = getPatrimoineGraphCall(nomPatrimoine, debut, fin, _callback);
        return localVarCall;

    }

    /**
     * obtenir le graphe de projection d&#39;un patrimoine sur une plage de date donnée
     * 
     * @param nomPatrimoine nom du patrimoine avec les espaces remplacés par \&quot;_\&quot; s&#39;il y en a (required)
     * @param debut  (optional)
     * @param fin  (optional)
     * @return byte[]
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> image montrant l&#39;évolution du graphe </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad request </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not found </td><td>  -  </td></tr>
        <tr><td> 429 </td><td> Too many requests to the API </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error </td><td>  -  </td></tr>
     </table>
     */
    public byte[] getPatrimoineGraph(String nomPatrimoine, LocalDate debut, LocalDate fin) throws ApiException {
        ApiResponse<byte[]> localVarResp = getPatrimoineGraphWithHttpInfo(nomPatrimoine, debut, fin);
        return localVarResp.getData();
    }

    /**
     * obtenir le graphe de projection d&#39;un patrimoine sur une plage de date donnée
     * 
     * @param nomPatrimoine nom du patrimoine avec les espaces remplacés par \&quot;_\&quot; s&#39;il y en a (required)
     * @param debut  (optional)
     * @param fin  (optional)
     * @return ApiResponse&lt;byte[]&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> image montrant l&#39;évolution du graphe </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad request </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not found </td><td>  -  </td></tr>
        <tr><td> 429 </td><td> Too many requests to the API </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<byte[]> getPatrimoineGraphWithHttpInfo(String nomPatrimoine, LocalDate debut, LocalDate fin) throws ApiException {
        okhttp3.Call localVarCall = getPatrimoineGraphValidateBeforeCall(nomPatrimoine, debut, fin, null);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * obtenir le graphe de projection d&#39;un patrimoine sur une plage de date donnée (asynchronously)
     * 
     * @param nomPatrimoine nom du patrimoine avec les espaces remplacés par \&quot;_\&quot; s&#39;il y en a (required)
     * @param debut  (optional)
     * @param fin  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> image montrant l&#39;évolution du graphe </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad request </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not found </td><td>  -  </td></tr>
        <tr><td> 429 </td><td> Too many requests to the API </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getPatrimoineGraphAsync(String nomPatrimoine, LocalDate debut, LocalDate fin, final ApiCallback<byte[]> _callback) throws ApiException {

        okhttp3.Call localVarCall = getPatrimoineGraphValidateBeforeCall(nomPatrimoine, debut, fin, _callback);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}