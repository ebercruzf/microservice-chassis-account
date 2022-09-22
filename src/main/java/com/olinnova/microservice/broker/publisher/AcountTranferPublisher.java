package com.olinnova.microservice.broker.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.olinnova.microservice.broker.message.MasterAccountTransferMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AcountTranferPublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishMaterAccountTransferMessage(MasterAccountTransferMessage message) throws JsonProcessingException {
        kafkaTemplate.send("c.transfermessage", message);
    }

}
