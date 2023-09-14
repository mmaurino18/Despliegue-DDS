package dominio.api;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dominio.Pruebas;
import dominio.actores.Ciudadano;
import dominio.comunidad.Comunidad;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;


public class FusionComunidades {
    List<Comunidad> comunidades;

    Comunidad comunidad ;
    public static void main(String[] args) {
        FusionComunidades fusionComunidades = new FusionComunidades();
        Comunidad comunidad = new Comunidad(null);
        comunidad.setNombre("COMUNIDAD1");
        Comunidad comunidad2 = new Comunidad(null);
        comunidad2.setNombre("COMUNIDAD2");
        Comunidad comunidad3 = new Comunidad(null);
        comunidad3.setNombre("COMUNIDAD3");
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setNombre("Carlos");
        Ciudadano ciudadano2 = new Ciudadano();
        ciudadano2.setNombre("Jose");
        Ciudadano ciudadano3 = new Ciudadano();
        ciudadano3.setNombre("Luis");
        List<Ciudadano> miembros = new ArrayList<>();
        miembros.add(ciudadano);
        miembros.add(ciudadano2);
        miembros.add(ciudadano3);
        List<Ciudadano> miembros2 = new ArrayList<>();
        miembros2.add(ciudadano);
        miembros2.add(ciudadano2);
        comunidad.setMiembros(miembros);
        comunidad2.setMiembros(miembros2);
        comunidad3.setMiembros(miembros2);
        List<Comunidad> comunidades = new ArrayList<>();
        comunidades.add(comunidad);
        comunidades.add(comunidad2);
        comunidades.add(comunidad3);
        fusionComunidades.sendCommunitiesToApi(comunidades);
        fusionComunidades.mostrarJson("http://localhost:8000/comunidades/fusionables/");
    }
    public void sendCommunitiesToApi(List<Comunidad> comunidades) {
        RestTemplate restTemplate = new RestTemplate();

        // Crear encabezados HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Convertir la lista de comunidades a JSON
        // Suponiendo que tienes un método `toJson` o similar
        String jsonCommunities = toJson(comunidades);

        // Crear una entidad HTTP que contenga los encabezados y el cuerpo
        HttpEntity<String> request = new HttpEntity<>(jsonCommunities, headers);

        // Enviar la solicitud POST
        String apiResponse = restTemplate.postForObject("http://localhost:8000/comunidades/fusionables/", request, String.class);


    }

    public String toJson(List<Comunidad> comunidades) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = "";

        try {
            jsonStr = objectMapper.writeValueAsString(comunidades);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
    public void mostrarJson(String url1){
            try {
                URL url = new URL(url1);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
                }

                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }
                conn.disconnect();

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
    }

}

