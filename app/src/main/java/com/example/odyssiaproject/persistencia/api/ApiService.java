package com.example.odyssiaproject.persistencia.api;


import com.example.odyssiaproject.entidad.Ciudad;
import com.example.odyssiaproject.entidad.FirestoreResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Call;


public interface ApiService {

    /**
     * 1. Obtiene la lista de países (con su campo 'imagen' y 'nombre').
     *    Equivale a GET /v1/projects/[TU_PROJECT_ID]/databases/(default)/documents/paises
     */

    @GET("v1/projects/odyssiaproject-ff99f/databases/(default)/documents/paises")
    Call<FirestoreResponse> getPaises();
    /**
     * 2. Obtiene la lista de ciudades (con su 'imagen' y 'descripcion')
     *    dentro de un país específico, por ejemplo 'belgica'.
     *    Equivale a GET /v1/projects/[TU_PROJECT_ID]/databases/(default)/documents/paises/belgica/listaCiudades
     */
    @GET("v1/projects/odyssiaproject-ff99f/databases/(default)/documents/paises/{paisId}/listaCiudades")
    Call<List<Ciudad>> getCiudadesDePais(
            @Path("paisId") String paisId
    );
}
