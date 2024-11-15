package java_on;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// just a Controller - you can do the rest of the Spring Application ;)
@RestController
@RequestMapping("joke")
public class Spring_6 {


    private final ChatClient chatClient;

    @Autowired
    public Spring_6(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/{subject}")
    public String getJoke(@PathVariable String subject) {

        String promptString =
                """
                        Tell a joke about {subject}
                        """;

        return chatClient.prompt()
                .user(u -> u.text(promptString).param("subject", subject))
                .call()
                .content();

//        Check more details here:
//        https://docs.spring.io/spring-ai/reference/api/chatclient.html
//        https://docs.spring.io/spring-ai/reference/index.html

//        Awesome repo with a lot of examples:
//        https://github.com/danvega - search for AI in repositories
    }
}
