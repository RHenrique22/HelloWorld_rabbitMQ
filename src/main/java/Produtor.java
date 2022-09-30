import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Produtor {
    public static void main(String[] args) throws Exception {

        final String NAME_QUEUE   = "PROGDIST_2022";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("mqadmin");
        factory.setPassword("Admin123XX_");

        try(
                Connection connection = factory.newConnection();
                Channel    channel    = connection.createChannel();
        ) {
            channel.queueDeclare(NAME_QUEUE, false, false, false, null);
            String mensagem = "Hello World of QUEUES";
            channel.basicPublish("", NAME_QUEUE, MessageProperties.TEXT_PLAIN, mensagem.getBytes());
            System.out.printf("Enviei a mensagem: '%s'%n", mensagem);
        }

    }
}
