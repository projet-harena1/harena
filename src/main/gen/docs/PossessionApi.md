# PossessionApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**crupdatePatrimoinePossessions**](PossessionApi.md#crupdatePatrimoinePossessions) | **PUT** /patrimoines/{nom_patrimoine}/possessions | met à jour une possession d&#39;un patrimoine si le nom existe, sinon on le crée |
| [**deletePatrimoinePossessionByNom**](PossessionApi.md#deletePatrimoinePossessionByNom) | **DELETE** /patrimoines/{nom_patrimoine}/possessions/{nom_possession} | effacer un patrimoine d&#39;une possession |
| [**getPatrimoinePossessionByNom**](PossessionApi.md#getPatrimoinePossessionByNom) | **GET** /patrimoines/{nom_patrimoine}/possessions/{nom_possession} | obtenir la possession demandée |
| [**getPatrimoinePossessions**](PossessionApi.md#getPatrimoinePossessions) | **GET** /patrimoines/{nom_patrimoine}/possessions | obtenir une liste paginée des possessions d&#39;un patrimoine |


<a name="crupdatePatrimoinePossessions"></a>
# **crupdatePatrimoinePossessions**
> GetPatrimoinePossessions200Response crupdatePatrimoinePossessions(nomPatrimoine, page, pageSize, getPatrimoinePossessions200Response)

met à jour une possession d&#39;un patrimoine si le nom existe, sinon on le crée

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PossessionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PossessionApi apiInstance = new PossessionApi(defaultClient);
    String nomPatrimoine = "nomPatrimoine_example"; // String | nom du patrimoine avec les espaces remplacés par \"_\" s'il y en a
    Integer page = 56; // Integer | 
    Integer pageSize = 56; // Integer | 
    GetPatrimoinePossessions200Response getPatrimoinePossessions200Response = new GetPatrimoinePossessions200Response(); // GetPatrimoinePossessions200Response | liste de possessions à ajouter ou à modifier
    try {
      GetPatrimoinePossessions200Response result = apiInstance.crupdatePatrimoinePossessions(nomPatrimoine, page, pageSize, getPatrimoinePossessions200Response);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PossessionApi#crupdatePatrimoinePossessions");
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
| **page** | **Integer**|  | [optional] |
| **pageSize** | **Integer**|  | [optional] |
| **getPatrimoinePossessions200Response** | [**GetPatrimoinePossessions200Response**](GetPatrimoinePossessions200Response.md)| liste de possessions à ajouter ou à modifier | [optional] |

### Return type

[**GetPatrimoinePossessions200Response**](GetPatrimoinePossessions200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | liste de possessions ajoutées ou modifiées |  -  |
| **400** | Bad request |  -  |
| **404** | Not found |  -  |
| **429** | Too many requests to the API |  -  |
| **500** | Internal server error |  -  |

<a name="deletePatrimoinePossessionByNom"></a>
# **deletePatrimoinePossessionByNom**
> deletePatrimoinePossessionByNom(nomPatrimoine, nomPossession)

effacer un patrimoine d&#39;une possession

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PossessionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PossessionApi apiInstance = new PossessionApi(defaultClient);
    String nomPatrimoine = "nomPatrimoine_example"; // String | nom du patrimoine avec les espaces remplacés par \"_\" s'il y en a
    String nomPossession = "nomPossession_example"; // String | nom de la possession avec les espaces remplacés par \"_\" s'il y en a
    try {
      apiInstance.deletePatrimoinePossessionByNom(nomPatrimoine, nomPossession);
    } catch (ApiException e) {
      System.err.println("Exception when calling PossessionApi#deletePatrimoinePossessionByNom");
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
| **nomPossession** | **String**| nom de la possession avec les espaces remplacés par \&quot;_\&quot; s&#39;il y en a | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | no content |  -  |
| **400** | Bad request |  -  |
| **404** | Not found |  -  |
| **429** | Too many requests to the API |  -  |
| **500** | Internal server error |  -  |

<a name="getPatrimoinePossessionByNom"></a>
# **getPatrimoinePossessionByNom**
> Possession getPatrimoinePossessionByNom(nomPatrimoine, nomPossession)

obtenir la possession demandée

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PossessionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PossessionApi apiInstance = new PossessionApi(defaultClient);
    String nomPatrimoine = "nomPatrimoine_example"; // String | nom du patrimoine avec les espaces remplacés par \"_\" s'il y en a
    String nomPossession = "nomPossession_example"; // String | nom de la possession avec les espaces remplacés par \"_\" s'il y en a
    try {
      Possession result = apiInstance.getPatrimoinePossessionByNom(nomPatrimoine, nomPossession);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PossessionApi#getPatrimoinePossessionByNom");
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
| **nomPossession** | **String**| nom de la possession avec les espaces remplacés par \&quot;_\&quot; s&#39;il y en a | |

### Return type

[**Possession**](Possession.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | la possession demandée |  -  |
| **400** | Bad request |  -  |
| **404** | Not found |  -  |
| **429** | Too many requests to the API |  -  |
| **500** | Internal server error |  -  |

<a name="getPatrimoinePossessions"></a>
# **getPatrimoinePossessions**
> GetPatrimoinePossessions200Response getPatrimoinePossessions(nomPatrimoine, page, pageSize)

obtenir une liste paginée des possessions d&#39;un patrimoine

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PossessionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    PossessionApi apiInstance = new PossessionApi(defaultClient);
    String nomPatrimoine = "nomPatrimoine_example"; // String | nom du patrimoine avec les espaces remplacés par \"_\" s'il y en a
    Integer page = 56; // Integer | 
    Integer pageSize = 56; // Integer | 
    try {
      GetPatrimoinePossessions200Response result = apiInstance.getPatrimoinePossessions(nomPatrimoine, page, pageSize);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PossessionApi#getPatrimoinePossessions");
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
| **page** | **Integer**|  | [optional] |
| **pageSize** | **Integer**|  | [optional] |

### Return type

[**GetPatrimoinePossessions200Response**](GetPatrimoinePossessions200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | liste paginée des possessions |  -  |
| **400** | Bad request |  -  |
| **404** | Not found |  -  |
| **429** | Too many requests to the API |  -  |
| **500** | Internal server error |  -  |

