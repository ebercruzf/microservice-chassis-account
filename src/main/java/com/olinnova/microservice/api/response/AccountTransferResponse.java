package com.olinnova.microservice.api.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountTransferResponse {
    private String accountId;
    private String transferID;
    private String clientID;
    private String message;
    private LocalDate createDate;

}
