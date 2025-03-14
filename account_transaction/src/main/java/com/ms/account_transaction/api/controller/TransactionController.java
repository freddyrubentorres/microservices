package com.ms.account_transaction.api.controller;

import com.ms.account_transaction.api.controller.dto.TransactionDto;
import com.ms.account_transaction.common.ResponseUtil;
import com.ms.account_transaction.constants.ProcessConstants;
import com.ms.account_transaction.domain.model.entity.Transaction;
import com.ms.account_transaction.domain.service.TransactionService;
import com.ms.account_transaction.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author : Freddy Torres
 * file :  TransactionController
 * @since : 11/3/2025, mar
 **/

@PropertySource("classpath:messages.properties")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ProcessConstants.API_PATH_TRANSACTION)
public class TransactionController {

    @Value("${controller.ok}")
    public String CONTROLLER_OK;

    private final TransactionService transactionService;

    @GetMapping(ProcessConstants.API_PATH_TRANSACTION_PING)
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("{\"status\":\"OK\"}");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionDto>> createAccount(@Valid @RequestBody Transaction transaction) {
        TransactionDto createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(CONTROLLER_OK, createdTransaction), HttpStatus.CREATED);
    }

    @GetMapping(ProcessConstants.API_PATH_TRANSACTION_BY_ACCOUNT)
    public ResponseEntity<ApiResponse<List<TransactionDto>>> getTransactionsByAccount(@PathVariable Long accountId) {
        List<TransactionDto> transaction = transactionService.getTransactionsByAccount(accountId);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(CONTROLLER_OK, transaction));
    }
    @GetMapping(ProcessConstants.API_PATH_TRANSACTION_BY_CLIENT)
    public ResponseEntity<ApiResponse<List<TransactionDto>>> getTransactionsByClientId(@PathVariable Long clientId) {
        List<TransactionDto> transaction = transactionService.getTransactionsByClientId(clientId);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(CONTROLLER_OK, transaction));
    }
}
