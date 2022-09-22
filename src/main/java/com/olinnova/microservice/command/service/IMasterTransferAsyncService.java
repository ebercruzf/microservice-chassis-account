package com.olinnova.microservice.command.service;

import java.time.LocalDate;

public interface IMasterTransferAsyncService {

    public boolean approveTransferFinish(String tranferId, LocalDate fechaApprove);
}
