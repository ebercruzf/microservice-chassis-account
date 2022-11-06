package com.olinnova.microservice.api.client;


import com.olinnova.microservice.api.request.AccountTransferAccountRequest;
import com.olinnova.microservice.api.response.AccountTransferResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="microservice-chassis-transfer", url = "${app.api.client.transfer.url}")
public interface IAccountTransferSyncClient {
    @DeleteMapping(value = "/api/transfer/disable/master/{account_id} ")
    public ResponseEntity<AccountTransferResponse> disableTransferSync(@PathVariable(name = "account_id") String accountId,
                                                                       @RequestBody AccountTransferAccountRequest requestBody);


}
