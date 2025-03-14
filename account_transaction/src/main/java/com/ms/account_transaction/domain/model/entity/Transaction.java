package com.ms.account_transaction.domain.model.entity;

import com.ms.account_transaction.domain.model.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

/**
 * @author : Freddy Torres
 * file :  Transaction
 * @since : 11/3/2025, mar
 **/

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "description")
    @NotNull(message = "{message.entity.transaction.description.NotNull}")
    private String description;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "transactionType")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @NotNull(message = "{message.entity.transaction.amount.NotNull}")
    @DecimalMin(value = "-1000000.00", inclusive = true, message = "{message.entity.transaction.amount.DecimalMin}")
    @DecimalMax(value = "1000000.00", inclusive = true, message = "{message.entity.transaction.amount.DecimalMax}")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @NotNull(message = "{message.entity.transaction.account.NotNull}")
    private Account account;

}

