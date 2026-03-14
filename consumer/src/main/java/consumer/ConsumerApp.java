package consumer;

import com.rabbitmq.client.*;

public class ConsumerApp {

    private static final String HOST = "localhost";

    public static void main(String[] args) throws Exception {

        String[] bancos = {"BAC","BANRURAL","BI","GYT",};

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        for (String banco : bancos) {

            channel.queueDeclare(banco, true, false, false, null);

            System.out.println("Escuchando cola: " + banco);

            DeliverCallback callback = (tag, delivery) -> {

                String mensaje = new String(delivery.getBody());

                System.out.println("Mensaje recibido: " + mensaje);

                boolean exito = ApiSender.enviarPOST(mensaje);

                if (exito) {

                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    System.out.println("POST exitoso → ACK enviado");

                } else {

                    System.out.println("Error en POST → mensaje no confirmado");

                }

            };

            channel.basicConsume(banco, false, callback, tag -> {});
        }
    }
}