package com.william.Biblioteca.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    public static String obtenerDatos(String url) {

        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> json = client.send(request, HttpResponse.BodyHandlers.ofString());

            return json.body();

        } catch (Exception e){
            throw new RuntimeException("No se pudo llamar a la API " + e.getMessage());
        }


    }

}
