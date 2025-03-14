package com.ms.account_transaction.domain.repository;

import com.ms.account_transaction.domain.model.entity.AccountTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : Freddy Torres
 * file :  AccountTransactions
 * @since : 12/3/2025, mié
 **/
@Repository
public interface AccountTransactionsRepository extends JpaRepository<AccountTransactions, Long> {
    @Query(value = "CALL view_accounttransactions(:identification_param,:start_date,:end_date);", nativeQuery = true)
    List<Map<String, Object>> procedureName(@Param("identification_param") String identification_param,
                                            @Param("start_date") Date start_date,
                                            @Param("end_date") Date end_date);
}
