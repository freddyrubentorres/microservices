package com.ms.account_transaction.domain.repository;

import com.ms.account_transaction.domain.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Freddy Torres
 * file :  AccountRepository
 * @since : 13/3/2025, jue
 **/

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByAccountId(Long id);
    boolean existsByAccountId(Long id);
    void deleteByAccountId(Long id);
}
