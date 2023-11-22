package apiFusionComunidades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FusionComunidades {
    public ComunidadApi fusion(ComunidadApi comunidad1, ComunidadApi comunidad2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, ComunidadApi> comunidades = new HashMap<>();
        comunidades.put("comunidad_principal", comunidad1);
        comunidades.put("comunidad_fusionable", comunidad2);

        String jsonBody = objectMapper.writeValueAsString(comunidades);

        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, jsonBody);
        //System.out.println("JSON Body: " + jsonBody);
        Request request = new Request.Builder()
                .url("http://localhost:8000/fuse_communities/")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            ComunidadApi comunidadFusionada = objectMapper.readValue(response.body().string(), ComunidadApi.class);
            return comunidadFusionada;
        }
    }
    public List<ComunidadApi> propuestas(ComunidadApi comunidad, List<ComunidadApi> listaComunidades) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("comunidad", comunidad);
        requestBodyMap.put("lista_comunidades", listaComunidades);

        String jsonBody = objectMapper.writeValueAsString(requestBodyMap);

        MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, jsonBody);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8000/propose_fusion/")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return objectMapper.readValue(response.body().string(), objectMapper.getTypeFactory().constructCollectionType(List.class, ComunidadApi.class));
        }
    }
}
