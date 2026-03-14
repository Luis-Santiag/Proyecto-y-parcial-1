package producer;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProducerApp {

    public static void main(String[] args) {

        try {

            String json = ApiService.obtenerTransacciones();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

            JsonNode transacciones = root.get("transacciones");

            for (JsonNode t : transacciones) {

                String banco = t.get("bancoDestino").asText();

                RabbitPublisher.enviar(banco, t.toString());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}