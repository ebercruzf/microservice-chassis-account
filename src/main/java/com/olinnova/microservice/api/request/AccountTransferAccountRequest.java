package com.olinnova.microservice.api.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class AccountTransferAccountRequest {

    private String reason;

    private LocalDate registerDate;
    private LocalDate createDate;
}
