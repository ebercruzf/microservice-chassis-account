package com.olinnova.microservice.api.controller;


import com.olinnova.microservice.api.client.IAccountTransferSyncClient;
import com.olinnova.microservice.api.request.AccountTransferAccountRequest;
import com.olinnova.microservice.api.response.AccountTransferResponse;
import com.olinnova.microservice.command.service.IAccountTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/***
 * A Microservice example is called an account.
 * This microservice implements an example of communications and
 * execution SYNC.
 * @author Eber Cruz  www.ebercruz.com
 */

@RestController
@RequestMapping("/api/account/transfer/sync")
public class AccountAPIController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountAPIController.class);
    @Autowired
    private IAccountTransferService iAccountTransferService;

    @PostMapping(value = "/{account_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransferResponse> accountTransfer(
            @PathVariable(name = "account_id", required = true) String account_id,
            @RequestBody(required = true) AccountTransferAccountRequest requestBody){

        LOG.debug("Processing AccountAPIController  : {} :: ", requestBody.toString());

        iAccountTransferService.approveAccountTransfer(account_id, requestBody.getRegisterDate());

        // dummy response
        var responseBody = new AccountTransferResponse();
        responseBody.setAccountId(account_id);
        responseBody.setMessage("Done simulating termination approval transfer");

        return ResponseEntity.ok().body(responseBody);

    }


}
