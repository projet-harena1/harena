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

import org.openapitools.client.ApiException;
import org.openapitools.client.model.BadRequestException;
import org.openapitools.client.model.GetPatrimoineFluxImpossibles200Response;
import org.openapitools.client.model.InternalServerException;
import java.time.LocalDate;
import org.openapitools.client.model.ResourceNotFoundException;
import org.openapitools.client.model.TooManyRequestsException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ProjectionFutureApi
 */
@Disabled
public class ProjectionFutureApiTest {

    private final ProjectionFutureApi api = new ProjectionFutureApi();

    /**
     * obtenir une liste des flux impossibles du patrimoine dans une intervalle donnée
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getPatrimoineFluxImpossiblesTest() throws ApiException {
        String nomPatrimoine = null;
        LocalDate debut = null;
        LocalDate fin = null;
        GetPatrimoineFluxImpossibles200Response response = api.getPatrimoineFluxImpossibles(nomPatrimoine, debut, fin);
        // TODO: test validations
    }

    /**
     * obtenir le graphe de projection d&#39;un patrimoine sur une plage de date donnée
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getPatrimoineGraphTest() throws ApiException {
        String nomPatrimoine = null;
        LocalDate debut = null;
        LocalDate fin = null;
        byte[] response = api.getPatrimoineGraph(nomPatrimoine, debut, fin);
        // TODO: test validations
    }

}