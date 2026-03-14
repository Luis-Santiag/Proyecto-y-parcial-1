package producer;

import com.rabbitmq.client.*;

public class RabbitPublisher {

    private static final String HOST = "localhost";

    public static void enviar(String cola, String mensaje) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(cola, true, false, false, null);

        channel.basicPublish("", cola, null, mensaje.getBytes());

        System.out.println("Enviado a cola " + cola);

        channel.close();
        connection.close();
    }
}