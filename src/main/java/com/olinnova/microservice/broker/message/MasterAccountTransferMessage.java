package com.olinnova.microservice.broker.message;

import lombok.Data;

import java.time.LocalDate;
@Data
public class MasterAccountTransferMessage {

    private LocalDate registerDate;
    private String acountId;
    private String tranferID;
    private String clientID;
    private String message;

}
