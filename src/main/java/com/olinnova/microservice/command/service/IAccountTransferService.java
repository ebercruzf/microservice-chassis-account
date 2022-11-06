package com.olinnova.microservice.command.service;







import com.olinnova.microservice.broker.message.MasterAccountTranferResponseMessage;

import java.time.LocalDate;


public interface IAccountTransferService {


    public boolean approveAccountTransfer(String accountId, LocalDate response);

    public void finalizeEmployeeTerminationApproval(MasterAccountTranferResponseMessage message);



}
