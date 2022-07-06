package com.confidencedev.notifications;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class MessageListener {

    private final File file = new File("./src/main/resources/messages.json");

    @KafkaListener(
            topics = "post",
            groupId = "groupId"
    )
    void listener(String data) throws IOException {
        System.out.println("Listener received: " + data);
        ObjectMapper mapper = new ObjectMapper();
        MessageRequest request = new MessageRequest();
        request.setMessage(data);

        mapper.writeValue(file, request);
    }
}
