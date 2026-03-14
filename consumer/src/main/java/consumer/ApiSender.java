package consumer;

import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ApiSender {

    public static boolean enviarPOST(String json) {

        try {

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = (ObjectNode) mapper.readTree(json);

    
            node.put("nombre", "Angel");
            node.put("carnet", "0905-24-9756");

            String nuevoJson = mapper.writeValueAsString(node);

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(
                    nuevoJson,
                    MediaType.parse("application/json"));

            Request request = new Request.Builder()
            		.url("https://7e0d9ogwzd.execute-api.us-east-1.amazonaws.com/default/guardarTransacciones")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            System.out.println("Respuesta POST: " + response.code());

            return response.isSuccessful();

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }
    }
}