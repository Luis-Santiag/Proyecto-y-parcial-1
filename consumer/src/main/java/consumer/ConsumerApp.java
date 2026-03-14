package consumer;

import com.rabbitmq.client.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Set;

public class ConsumerApp {

    private static final String HOST = "localhost";

    private static final Set<String> procesados = new HashSet<>();

    public static void main(String[] args) throws Exception {

        String[] bancos = {"BAC","BANRURAL","BI","GYT"};

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("cola_duplicados", true, false, false, null);
        channel.queueDeclare("cola_errores", true, false, false, null);

        ObjectMapper mapper = new ObjectMapper();

        for (String banco : bancos) {

            channel.queueDeclare(banco, true, false, false, null);

            System.out.println("Escuchando cola: " + banco);

            DeliverCallback callback = (tag, delivery) -> {

                String mensaje = new String(delivery.getBody());

                try {

                    JsonNode json = mapper.readTree(mensaje);
                    String id = json.get("idTransaccion").asText();

                    if (procesados.contains(id)) {

                        channel.basicPublish("", "cola_duplicados", null, mensaje.getBytes());

                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

                        System.out.println(id + " | DUPLICADA | cola_duplicados");

                        return;
                    }

                    boolean exito = ApiSender.enviarPOST(mensaje);

                    if (exito) {

                        procesados.add(id);

                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

                        System.out.println(id + " | PROCESADA | " + banco);

                    } else {

                        System.out.println(id + " | ERROR_POST | " + banco);

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            };

            channel.basicConsume(banco, false, callback, tag -> {});
        }
    }
}