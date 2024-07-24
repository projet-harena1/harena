# ProjectionFutureApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getPatrimoineFluxImpossibles**](ProjectionFutureApi.md#getPatrimoineFluxImpossibles) | **GET** /patrimoines/{nom_patrimoine}/flux-impossibles | obtenir une liste des flux impossibles du patrimoine dans une intervalle donnée |
| [**getPatrimoineGraph**](ProjectionFutureApi.md#getPatrimoineGraph) | **GET** /patrimoines/{nom_patrimoine}/graphe | obtenir le graphe de projection d&#39;un patrimoine sur une plage de date donnée |


<a name="getPatrimoineFluxImpossibles"></a>
# **getPatrimoineFluxImpossibles**
> GetPatrimoineFluxImpossibles200Response getPatrimoineFluxImpossibles(nomPatrimoine, debut, fin)

obtenir une liste des flux impossibles du patrimoine dans une intervalle donnée

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProjectionFutureApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    ProjectionFutureApi apiInstance = new ProjectionFutureApi(defaultClient);
    String nomPatrimoine = "nomPatrimoine_example"; // String | nom du patrimoine avec les espaces remplacés par \"_\" s'il y en a
    LocalDate debut = LocalDate.now(); // LocalDate | 
    LocalDate fin = LocalDate.now(); // LocalDate | 
    try {
      GetPatrimoineFluxImpossibles200Response result = apiInstance.getPatrimoineFluxImpossibles(nomPatrimoine, debut, fin);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProjectionFutureApi#getPatrimoineFluxImpossibles");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **nomPatrimoine** | **String**| nom du patrimoine avec les espaces remplacés par \&quot;_\&quot; s&#39;il y en a | |
| **debut** | **LocalDate**|  | [optional] |
| **fin** | **LocalDate**|  | [optional] |

### Return type

[**GetPatrimoineFluxImpossibles200Response**](GetPatrimoineFluxImpossibles200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | flux impossibles du patrimoine actuel selon une plage de date donnée |  -  |
| **400** | Bad request |  -  |
| **404** | Not found |  -  |
| **429** | Too many requests to the API |  -  |
| **500** | Internal server error |  -  |

<a name="getPatrimoineGraph"></a>
# **getPatrimoineGraph**
> byte[] getPatrimoineGraph(nomPatrimoine, debut, fin)

obtenir le graphe de projection d&#39;un patrimoine sur une plage de date donnée

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProjectionFutureApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    ProjectionFutureApi apiInstance = new ProjectionFutureApi(defaultClient);
    String nomPatrimoine = "nomPatrimoine_example"; // String | nom du patrimoine avec les espaces remplacés par \"_\" s'il y en a
    LocalDate debut = LocalDate.now(); // LocalDate | 
    LocalDate fin = LocalDate.now(); // LocalDate | 
    try {
      byte[] result = apiInstance.getPatrimoineGraph(nomPatrimoine, debut, fin);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProjectionFutureApi#getPatrimoineGraph");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **nomPatrimoine** | **String**| nom du patrimoine avec les espaces remplacés par \&quot;_\&quot; s&#39;il y en a | |
| **debut** | **LocalDate**|  | [optional] |
| **fin** | **LocalDate**|  | [optional] |

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: image/png, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | image montrant l&#39;évolution du graphe |  -  |
| **400** | Bad request |  -  |
| **404** | Not found |  -  |
| **429** | Too many requests to the API |  -  |
| **500** | Internal server error |  -  |

