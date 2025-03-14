package com.ms.account_transaction.domain.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author : Freddy Torres
 * file :  AccountTransactions
 * @since : 12/3/2025, mié
 **/

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "accounttransactions",
                procedureName = "view_accounttransactions",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "identification_param", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "start_date", type = Date.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "end_date", type = Date.class)
                })
})
public class AccountTransactions implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    private Date date;
    private String name;
    private String last_name;
    private String account_type;
    private Double initial_balance;
    private Double amount;
    private Double balance;
}
