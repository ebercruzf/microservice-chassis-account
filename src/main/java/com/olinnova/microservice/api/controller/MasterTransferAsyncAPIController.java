package com.olinnova.microservice.api.controller;

import com.olinnova.microservice.api.request.AccountTransferAccountRequest;
import com.olinnova.microservice.api.response.AccountTransferResponse;
import com.olinnova.microservice.command.service.impl.MasterTransferAsyncServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/***
 * A Microservice example is called an account.
 * This microservice implements an example of communications ASYNC using Apache Kafka.
 * @author Eber Cruz  www.ebercruz.com
 */

@RestController
@RequestMapping("/api/cuenta/async")
public class MasterTransferAsyncAPIController {

    @Autowired
    private MasterTransferAsyncServiceImpl masterTransferAsyncService;

    @PostMapping(value = "/test/{account_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransferResponse> approveTransferAccount(
            @PathVariable(name = "account_id", required = true) String accountId,
            @RequestBody(required = true) AccountTransferAccountRequest request){

        masterTransferAsyncService.approveTransferFinish(accountId, request.getRegisterDate());

        // dummy reponse
        var responseBody = new AccountTransferResponse();
        responseBody.setAccountId(accountId);
        responseBody.setMessage("Done publishing termination ASYNC approval ");

        return ResponseEntity.ok().body(responseBody);

    }

}
