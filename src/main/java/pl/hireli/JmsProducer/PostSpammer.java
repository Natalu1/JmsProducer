package pl.hireli.JmsProducer;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.hirely.springboot.blog.model.dto.NewBlogPostDto;
import pl.hirely.springboot.blog.model.dto.NewCommentDto;

import java.util.UUID;


@Component
public class PostSpammer {
    private final JmsTemplate jmsTemplate;

    public PostSpammer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void generatePost() {
        System.out.println("automated method triggered");
//        jmsTemplate.convertAndSend("post-queue", "siema!");
        jmsTemplate.convertAndSend("post-queue",
                new NewBlogPostDto(
                "title"+ UUID.randomUUID(),
                "content"
        ));
        jmsTemplate.convertAndSend("comment-queue",
                new NewCommentDto("content"+UUID.randomUUID()));
    }
}
