package com.ms.account_transaction.api.controller;

import com.ms.account_transaction.api.controller.dto.*;
import com.ms.account_transaction.domain.model.entity.Account;
import com.ms.account_transaction.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.ms.account_transaction.constants.ProcessConstants;
import com.ms.account_transaction.dto.response.ApiResponse;
import com.ms.account_transaction.common.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

/**
 * @author : Freddy Torres
 * file :  AccountController
 * @since : 12/3/2025, mié
 **/

@PropertySource("classpath:messages.properties")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ProcessConstants.API_PATH_ACCOUNTS)
public class AccountController {
    @Value("${controller.ok}")
    public String CONTROLLER_OK;

    private final AccountService accountService;

    @GetMapping(ProcessConstants.API_PATH_ACCOUNTS_PING)
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("{\"status\":\"OK\"}");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountDto>>> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccount();
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(CONTROLLER_OK, accounts));
    }

    @GetMapping(ProcessConstants.API_PATH_ACCOUNTS_BY_ID)
    public ResponseEntity<ApiResponse<List<AccountDto>>> getAccountById(@PathVariable Long id) {
        List<AccountDto> accounts = accountService.getAccountById(id);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(CONTROLLER_OK, accounts));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Account>> createAccount(@Valid @RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(CONTROLLER_OK, createdAccount), HttpStatus.CREATED);
    }
    @DeleteMapping(ProcessConstants.API_PATH_ACCOUNTS_DELETE_ID)
    public ResponseEntity<ApiResponse<Void>> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(CONTROLLER_OK, null));
    }

}
