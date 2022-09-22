package com.olinnova.microservice.broker.listener;

import com.olinnova.microservice.broker.message.MasterAccountTransferMessage;
import com.olinnova.microservice.broker.message.MasterAccountTranferResponseMessage;
import com.olinnova.microservice.command.service.IAccountTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

@KafkaListener(topics = "c.transfermessage")
public class MasterAccountTransferResponseListener {

    private static final Logger LOG = LoggerFactory.getLogger(MasterAccountTransferResponseListener.class);

    @Autowired
    private IAccountTransferService iAcountTranferService;

    @KafkaHandler void handlerMessageAccount(MasterAccountTransferMessage message){
        LOG.info(message.toString());
    }
    @KafkaListener(topics = "c.transfermessage.response")
    public void listenerMasterAccountTranferResponse(MasterAccountTranferResponseMessage message){
        LOG.debug("[Async-Listener] Start listening from message broker, response from disabling Account Transfer");

        iAcountTranferService.finalizeEmployeeTerminationApproval(message);

        LOG.debug("[Async-Listener] Finish listening from message broker, response from disabling Account Transfer");
    }


}
