package university.message;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class QueueSender {

    private final RabbitTemplate template;
    private final Queue queue;

    @Autowired
    public QueueSender(RabbitTemplate template, Queue queue) {
        this.template = template;
        this.queue = queue;
    }

    public void send(String message) {
        this.template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
