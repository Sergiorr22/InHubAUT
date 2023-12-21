package org.AXA.Inhub.utils;


import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class WebServices {

//    public static String getUrl() throws IOException {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"claveDeAgente\":\"33426\",\r\n    \"stage\": \"uat\",\r\n    \"tipoAgente\": \"intermediario\"\r\n}");
//        Request request = new Request.Builder()
//                .url("https://uat-api.inhub.cloud/api/v1/7f407882-2fdc-4093-bee5-ca514f19a855/axa-cotizacion-portal/c/digital/actions/cotizacion-axa-colpatria/v1/start_session_external?product[portal]=axa-cotizacion-portal&product[route]=root")
//                .method("POST", body)
//                .addHeader("x-cti-debug", "true")
//                .addHeader("Content-Type", "application/json")
//                .build();
//        Response response = client.newCall(request).execute();
//        JSONObject jsonBody = new JSONObject(response.body().string());
//        return jsonBody.getJSONObject("data").getString("url");
//    }

    public static String traerUrl(String clave) throws IOException {
//        ArrayList<String> intermediario = new ArrayList<>();
//        Random random = new Random();
//        intermediario.add("33426");
//        intermediario.add("10426");
//        intermediario.add("12490");
//        intermediario.add("12914");
//
//        ArrayList<String> aliado = new ArrayList<>();
//        aliado.add("59063");
//        aliado.add("59145");
//        aliado.add("58024");
//        aliado.add("994801");
//
//        int randomNumber = random.nextInt(3);
//        String agente = "";
//        if (Objects.equals(perfil, "intermediario")) {
//            agente = intermediario.get(randomNumber);
//        }else {
//            agente = aliado.get(randomNumber);
//        }


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"claveDeAgente\":\""+clave+"\",\r\n    \"stage\": \"uat\",\r\n    \"tipoBienCotizar\": \"autoLiviano\"\r\n}");
        Request request = new Request.Builder()
                .url("https://uat-api.inhub.cloud/api/v1/7f407882-2fdc-4093-bee5-ca514f19a855/axa-cotizacion-portal/c/digital/actions/cotizacion-axa-colpatria/v1/start_session_external?product[portal]=axa-cotizacion-portal&product[route]=root")
                .method("POST", body)
                .addHeader("x-cti-debug", "true")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonBody = new JSONObject(response.body().string());
        return jsonBody.getJSONObject("data").getString("url");



    }


}
