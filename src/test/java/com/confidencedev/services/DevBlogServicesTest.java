package com.confidencedev.services;

import com.confidencedev.notifications.MessageRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.File;

@Slf4j
class DevBlogServicesTest {

    private final File file = new File("./src/test/resources/messages.json");

    @Value("classpath:data/messages.json")
    Resource resourceFile;

    @Test
    @Disabled
    void canReadData() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MessageRequest request = mapper.readValue(file, MessageRequest.class);

        System.out.println(request.getMessage());
    }

    @Test
    void canWriteData() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        MessageRequest request = new MessageRequest();
        request.setMessage("Me");

        //mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(file, request);
    }
}