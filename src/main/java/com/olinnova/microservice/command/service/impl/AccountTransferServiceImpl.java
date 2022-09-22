package com.olinnova.microservice.command.service.impl;

import com.olinnova.microservice.api.client.IAccountTransferSyncClient;
import com.olinnova.microservice.api.request.AccountTransferAccountRequest;
import com.olinnova.microservice.broker.message.MasterAccountTranferResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.olinnova.microservice.command.service.IAccountTransferService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccountTransferServiceImpl implements IAccountTransferService {
    private static final Logger LOG = LoggerFactory.getLogger(AccountTransferServiceImpl.class );
    @Autowired
    private IAccountTransferSyncClient iAccountTransferSyncClient ;

    @Override
    public boolean approveAccountTransfer(String accountId, LocalDate accountTransferDate) {
        LOG.debug("[Sync-publish] approval for account transfer : {}, account transfer date : {}", accountId, accountTransferDate );
        LOG.debug("[sync-publish] approval for account transfer  status PENDING: {}");

        System.out.println("ProcessingAccountAPIController00011 by : {}  account: {}" + accountId + "--"+ accountTransferDate );
        try{
            //publishing to message broker
            LOG.debug("[Sync-publish] approval Star for account tranfer ");
            var requesBody = new AccountTransferAccountRequest();
            requesBody.setRegisterDate(accountTransferDate);
          //  System.out.println("ProcessingAccountAPIController00012: " + accountId + "--"+ accountTransferDate );

            iAccountTransferSyncClient.disableTransferSync(accountId, requesBody);

            LOG.debug("[Async-publish] Finish publishing approval  for account transfer  status : APPROVED");

            return true;
        } catch (Exception e) {
            LOG.warn("[Async-publish] Client exception : {} ", e.getMessage());
            LOG.debug("[Async-publish] Updating data on account transfer microservice, termination status with : ERROR  ");
            return false;
        }


    }


    @Override
    public void finalizeEmployeeTerminationApproval(MasterAccountTranferResponseMessage responseMessage){
        LOG.debug("[Async-listen] Updating data on industrial relation microservice, termination status : APROVED, for employee {}",
                responseMessage.getAcountId());
    }



}
