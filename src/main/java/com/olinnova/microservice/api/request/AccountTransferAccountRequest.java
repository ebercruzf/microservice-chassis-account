package com.olinnova.microservice.api.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class AccountTransferAccountRequest {

    private String reason;

    private LocalDate registerDate;
    private LocalDate createDate;
}
