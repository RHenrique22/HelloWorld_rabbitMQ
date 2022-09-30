import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumidor {
    public static void main(String[] args) throws Exception {

        final String NAME_QUEUE = "PROGDIST_2022";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("mqadmin");
        factory.setPassword("Admin123XX_");

        Connection connection = factory.newConnection();
        Channel    channel    = connection.createChannel();

        channel.queueDeclare(NAME_QUEUE, false, false, false, null);

        DeliverCallback callback = (consumerTag, deliver) -> {
            String mensagem = new String(deliver.getBody());
            System.out.printf("A mensagem recebida foi '%s'%n", mensagem);
        };

        channel.basicConsume(NAME_QUEUE, true, callback, consumerTag -> {});

    }
}
