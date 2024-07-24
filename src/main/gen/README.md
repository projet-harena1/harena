# openapi-java-client

harena
- API version: 1.0.0
  - Build date: 2024-07-23T10:20:14.118552532+03:00[Indian/Antananarivo]

harena


*Automatically generated by the [OpenAPI Generator](https://openapi-generator.tech)*


## Requirements

Building the API client library requires:
1. Java 1.8+
2. Maven (3.8.3+)/Gradle (7.2+)

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>org.openapitools</groupId>
  <artifactId>openapi-java-client</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
  repositories {
    mavenCentral()     // Needed if the 'openapi-java-client' jar has been published to maven central.
    mavenLocal()       // Needed if the 'openapi-java-client' jar has been published to the local maven repo.
  }

  dependencies {
     implementation "org.openapitools:openapi-java-client:1.0.0"
  }
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/openapi-java-client-1.0.0.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

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

## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*PatrimoineApi* | [**crupdatePatrimoines**](docs/PatrimoineApi.md#crupdatePatrimoines) | **PUT** /patrimoines | met à jour les patrimoines si le nom est donné, sinon on le crée
*PatrimoineApi* | [**getPatrimoineByNom**](docs/PatrimoineApi.md#getPatrimoineByNom) | **GET** /patrimoines/{nom_patrimoine} | obtenir le patrimoine demandé
*PatrimoineApi* | [**getPatrimoines**](docs/PatrimoineApi.md#getPatrimoines) | **GET** /patrimoines | obtenir une liste paginée des patrimoines
*PossessionApi* | [**crupdatePatrimoinePossessions**](docs/PossessionApi.md#crupdatePatrimoinePossessions) | **PUT** /patrimoines/{nom_patrimoine}/possessions | met à jour une possession d&#39;un patrimoine si le nom existe, sinon on le crée
*PossessionApi* | [**deletePatrimoinePossessionByNom**](docs/PossessionApi.md#deletePatrimoinePossessionByNom) | **DELETE** /patrimoines/{nom_patrimoine}/possessions/{nom_possession} | effacer un patrimoine d&#39;une possession
*PossessionApi* | [**getPatrimoinePossessionByNom**](docs/PossessionApi.md#getPatrimoinePossessionByNom) | **GET** /patrimoines/{nom_patrimoine}/possessions/{nom_possession} | obtenir la possession demandée
*PossessionApi* | [**getPatrimoinePossessions**](docs/PossessionApi.md#getPatrimoinePossessions) | **GET** /patrimoines/{nom_patrimoine}/possessions | obtenir une liste paginée des possessions d&#39;un patrimoine
*ProjectionFutureApi* | [**getPatrimoineFluxImpossibles**](docs/ProjectionFutureApi.md#getPatrimoineFluxImpossibles) | **GET** /patrimoines/{nom_patrimoine}/flux-impossibles | obtenir une liste des flux impossibles du patrimoine dans une intervalle donnée
*ProjectionFutureApi* | [**getPatrimoineGraph**](docs/ProjectionFutureApi.md#getPatrimoineGraph) | **GET** /patrimoines/{nom_patrimoine}/graphe | obtenir le graphe de projection d&#39;un patrimoine sur une plage de date donnée


## Documentation for Models

 - [Argent](docs/Argent.md)
 - [ArgentAllOf](docs/ArgentAllOf.md)
 - [BadRequestException](docs/BadRequestException.md)
 - [Devise](docs/Devise.md)
 - [Exception](docs/Exception.md)
 - [FluxArgent](docs/FluxArgent.md)
 - [FluxArgentAllOf](docs/FluxArgentAllOf.md)
 - [FluxImpossibles](docs/FluxImpossibles.md)
 - [GetPatrimoineFluxImpossibles200Response](docs/GetPatrimoineFluxImpossibles200Response.md)
 - [GetPatrimoinePossessions200Response](docs/GetPatrimoinePossessions200Response.md)
 - [GetPatrimoines200Response](docs/GetPatrimoines200Response.md)
 - [InternalServerException](docs/InternalServerException.md)
 - [Materiel](docs/Materiel.md)
 - [MaterielAllOf](docs/MaterielAllOf.md)
 - [NotAuthorizedException](docs/NotAuthorizedException.md)
 - [Patrimoine](docs/Patrimoine.md)
 - [Personne](docs/Personne.md)
 - [Possession](docs/Possession.md)
 - [PossessionAvecType](docs/PossessionAvecType.md)
 - [ResourceNotFoundException](docs/ResourceNotFoundException.md)
 - [TooManyRequestsException](docs/TooManyRequestsException.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author


