package com.ms.account_transaction.domain.model.entity;

import com.ms.account_transaction.domain.model.enums.AccountType;
import com.ms.account_transaction.domain.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

/**
 * @author : Freddy Torres
 * file :  Account
 * @since : 11/3/2025, mar
 **/

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"accountNumber"})
})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number", nullable = false)
    private Long accountNumber;

    @Column(name = "account_type", nullable = false)
    @NotNull(message = "{message.entity.account.accountType.NotNull}")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "initial_balance", nullable = false)
    @NotNull(message = "{message.entity.account.initial_balance.NotNull}")
    @DecimalMin(value = "0.01", inclusive = true, message = "{message.entity.account.initial_balance.DecimalMin}")
    @DecimalMax(value = "1000000.00", inclusive = true, message = "{message.entity.account.initial_balance.DecimalMax}")
    private Double initialBalance;

    @Column(name = "status")
    @NotNull(message = "{message.entity.account.status.NotNull}")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull(message = "{message.entity.account.clientId.NotNull}")
    @Column(name = "client_id", nullable = false)
    private int clientId;

}
