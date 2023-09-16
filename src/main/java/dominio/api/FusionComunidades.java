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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        comunidad.setMiembros(miembros2);
        comunidad2.setMiembros(miembros2);
        comunidad3.setMiembros(miembros);
        float gradoConfianza = 0.5f ;
        float gradoConfianza2 = 0.2f ;
        comunidad.setGradoDeConfianza(gradoConfianza);
        comunidad2.setGradoDeConfianza(gradoConfianza);
        comunidad3.setGradoDeConfianza(gradoConfianza2);
        List<Comunidad> comunidades = new ArrayList<>();
        comunidades.add(comunidad);
        comunidades.add(comunidad2);
        comunidades.add(comunidad3);
        fusionComunidades.sendCommunitiesToApi(comunidad, comunidades);
        fusionComunidades.mostrarJson("http://localhost:8000/comunidades/fusionables/");
    }
    public void sendCommunitiesToApi(Comunidad comunidad, List<Comunidad> comunidades) {
        RestTemplate restTemplate = new RestTemplate();

        // Crear encabezados HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Convertir la comunidad y la lista de comunidades a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> payload = new HashMap<>();
        try {
            payload.put("comunidad", comunidad);
            payload.put("lista_comunidades", comunidades);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar comunidad y lista de comunidades al payload");
            return;
        }

        String jsonPayload = "";
        try {
            jsonPayload = objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Error al convertir el payload a JSON");
            return;
        }

        // Crear una entidad HTTP que contenga los encabezados y el cuerpo
        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);

        // Enviar la solicitud POST
        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8000/propose_fusion/", request, String.class);

            if (response.getStatusCodeValue() == 200) {
                System.out.println("Solicitud POST exitosa: " + response.getBody());
            } else {
                System.out.println("Error en la solicitud POST: " + response.getStatusCodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Excepción al realizar la solicitud POST");
        }
    }


    public void mostrarJson(String url1){
        try {
            URL url = new URL(url1);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.out.println("Failed: HTTP Error code: " + conn.getResponseCode());
                return;
            }

            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    public JSONObject ObtenerJson(String url1){
        HttpURLConnection conn = null;
        try {
            // URL del endpoint
            URL url = new URL(url1);
            conn = (HttpURLConnection) url.openConnection();

            // Configuración de la solicitud HTTP
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");

            // Aquí deberías escribir el código para enviar el cuerpo de la solicitud
            // que incluye los detalles de las comunidades a fusionar
            // ...

            // Leer la respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Convertir la respuesta a JSON
            JSONObject jsonResponse = new JSONObject(response.toString());
            return  jsonResponse;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
    public void fusionarComunidades(){
       JSONObject comunidadJson = this.ObtenerJson("http://localhost:8000/fuse_communities/");
        Comunidad nuevaComunidad = new Comunidad();
        nuevaComunidad.setNombre(comunidadJson.getString("nombre"));
        nuevaComunidad.setGradoDeConfianza(comunidadJson.getFloat("gradoDeConfianza"));
        //eliminarComunidad();
    }
    public void eliminarComunidad(Comunidad comunidad){

    }
}