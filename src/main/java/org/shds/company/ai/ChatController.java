package org.shds.company.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class ChatController {
    private final ChatClient chatClient;

    @Autowired
    public ChatController(final ChatClient chatClient) {
        this.chatClient = chatClient;
    }
    @PostMapping("/call")
    public String call(@RequestBody String message) {
        System.out.println("call 접근 요청이 들어옴");
        return chatClient.prompt()
                .system("한국어로 대답해줘.")
                .user(message)
                .call()
                .content();
    }
    @GetMapping
    public String test( ) {
        System.out.println("get");
        return "열리긴함";
    }

}
