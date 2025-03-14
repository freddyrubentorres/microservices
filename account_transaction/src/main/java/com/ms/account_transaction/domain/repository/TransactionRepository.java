package com.ms.account_transaction.domain.repository;

import com.ms.account_transaction.domain.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ms.account_transaction.domain.model.entity.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * @author : Freddy Torres
 * file :  TransactionRepository
 * @since : 11/3/2025, mar
 **/

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findTopByAccountOrderByDateDesc(Account account);
    List<Transaction> findByAccountOrderByDateDesc(Account account);
    List<Transaction> findByAccount_ClientIdOrderByDateDesc(int clientId);
}
