# PatrimoineApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**crupdatePatrimoines**](PatrimoineApi.md#crupdatePatrimoines) | **PUT** /patrimoines | met à jour les patrimoines si le nom est donné, sinon on le crée |
| [**getPatrimoineByNom**](PatrimoineApi.md#getPatrimoineByNom) | **GET** /patrimoines/{nom_patrimoine} | obtenir le patrimoine demandé |
| [**getPatrimoines**](PatrimoineApi.md#getPatrimoines) | **GET** /patrimoines | obtenir une liste paginée des patrimoines |


<a name="crupdatePatrimoines"></a>
# **crupdatePatrimoines**
> GetPatrimoines200Response crupdatePatrimoines(getPatrimoines200Response)

met à jour les patrimoines si le nom est donné, sinon on le crée

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PatrimoineApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PatrimoineApi apiInstance = new PatrimoineApi(defaultClient);
    GetPatrimoines200Response getPatrimoines200Response = new GetPatrimoines200Response(); // GetPatrimoines200Response | 
    try {
      GetPatrimoines200Response result = apiInstance.crupdatePatrimoines(getPatrimoines200Response);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PatrimoineApi#crupdatePatrimoines");
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
| **getPatrimoines200Response** | [**GetPatrimoines200Response**](GetPatrimoines200Response.md)|  | [optional] |

### Return type

[**GetPatrimoines200Response**](GetPatrimoines200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | liste de patrimoines créés ou mis à jour |  -  |
| **400** | Bad request |  -  |
| **404** | Not found |  -  |
| **429** | Too many requests to the API |  -  |
| **500** | Internal server error |  -  |

<a name="getPatrimoineByNom"></a>
# **getPatrimoineByNom**
> Patrimoine getPatrimoineByNom(nomPatrimoine)

obtenir le patrimoine demandé

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PatrimoineApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PatrimoineApi apiInstance = new PatrimoineApi(defaultClient);
    String nomPatrimoine = "nomPatrimoine_example"; // String | nom du patrimoine avec les espaces remplacés par \"_\" s'il y en a
    try {
      Patrimoine result = apiInstance.getPatrimoineByNom(nomPatrimoine);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PatrimoineApi#getPatrimoineByNom");
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

### Return type

[**Patrimoine**](Patrimoine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | le patrimoine demandé |  -  |
| **400** | Bad request |  -  |
| **404** | Not found |  -  |
| **429** | Too many requests to the API |  -  |
| **500** | Internal server error |  -  |

<a name="getPatrimoines"></a>
# **getPatrimoines**
> GetPatrimoines200Response getPatrimoines(page, pageSize)

obtenir une liste paginée des patrimoines

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PatrimoineApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PatrimoineApi apiInstance = new PatrimoineApi(defaultClient);
    Integer page = 56; // Integer | 
    Integer pageSize = 56; // Integer | 
    try {
      GetPatrimoines200Response result = apiInstance.getPatrimoines(page, pageSize);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PatrimoineApi#getPatrimoines");
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
| **page** | **Integer**|  | [optional] |
| **pageSize** | **Integer**|  | [optional] |

### Return type

[**GetPatrimoines200Response**](GetPatrimoines200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | liste paginée de patrimoines |  -  |
| **400** | Bad request |  -  |
| **404** | Not found |  -  |
| **429** | Too many requests to the API |  -  |
| **500** | Internal server error |  -  |

