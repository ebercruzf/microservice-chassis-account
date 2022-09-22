package com.olinnova.microservice.command.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.olinnova.microservice.broker.message.MasterAccountTranferResponseMessage;
import com.olinnova.microservice.broker.message.MasterAccountTransferMessage;
import com.olinnova.microservice.broker.publisher.TransferApprovePublisher;
import com.olinnova.microservice.command.service.IMasterTransferAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MasterTransferAsyncServiceImpl implements IMasterTransferAsyncService {

    private static final Logger LOG = LoggerFactory.getLogger(MasterTransferAsyncServiceImpl.class);

    @Autowired
    TransferApprovePublisher transferApprovePublisher;

    @Override
    public boolean approveTransferFinish(String transferId, LocalDate dateApprove) {

        LOG.debug("[Async-publish] Simulating MasterTransferAsyncServiceImpl approval for transfer {} date : {} ",
                transferId, dateApprove);

        try{
            //publishing to message broker
            LOG.debug("[Async-publish] Simulating MasterTransferAsyncServiceImpl message to message broker");

            var messagePublish = new MasterAccountTransferMessage();
            messagePublish.setAcountId(transferId);
            messagePublish.setRegisterDate(dateApprove);
            transferApprovePublisher.publishMasterTransferMessage(messagePublish);
            LOG.debug("[Async-publish] Finish publishing master message ACCOUNT ");
            return  true;
        } catch (JsonProcessingException e) {
            LOG.warn("[Async-Publish] Client Exception : {} ", e.getMessage());
            LOG.debug("[Async-Publish] Client Exception status : ERROR");
           return false;
        }
    }

    public void finalizeTransferApprove(MasterAccountTranferResponseMessage responseMessage){
        LOG.debug("[Async-listen] Updating data on account microservices: ESTATUS : APROVED, for tranfer {}",
                responseMessage.getAcountId());

    }


}
