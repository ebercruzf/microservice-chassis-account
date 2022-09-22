package com.olinnova.microservice.broker.message;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MasterAccountTranferResponseMessage {

    private LocalDate tranferDate;
    private String acountId;
    private String tranferID;
    private String clientID;
    private String message;

}
