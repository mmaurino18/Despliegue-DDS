package dominio.api;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Pruebas;
import dominio.comunidad.Comunidad;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


public class FusionComunidades {
    List<Comunidad> comunidades;

    Comunidad comunidad ;
    public static void main(String[] args) {
        FusionComunidades fusionComunidades = new FusionComunidades();
        Comunidad comunidad = new Comunidad(null);
        comunidad.setNombre("Matias");
        Comunidad comunidad2 = new Comunidad(null);
        comunidad2.setNombre("Fidel");
        Comunidad comunidad3 = new Comunidad(null);
        comunidad3.setNombre("Piero");
        List<Comunidad> comunidades = new ArrayList<>();
        comunidades.add(comunidad);
        comunidades.add(comunidad2);
        comunidades.add(comunidad3);
        fusionComunidades.sendCommunitiesToApi(comunidades);

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
        String apiResponse = restTemplate.postForObject("http://localhost:8000/suggest_fusion/", request, String.class);


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

}