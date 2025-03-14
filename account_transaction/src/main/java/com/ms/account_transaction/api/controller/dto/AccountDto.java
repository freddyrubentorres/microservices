package com.ms.account_transaction.api.controller.dto;

import lombok.*;
import com.ms.account_transaction.domain.model.enums.*;

/**
 * @author : Freddy Torres
 * file :  AccountDto
 * @since : 13/3/2025, jue
 **/

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long accountId;
    private Long accountNumber;
    private AccountType accountType;
    private Double initialBalance;
    private Status status;
    private int clientId;
}
