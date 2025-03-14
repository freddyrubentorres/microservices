package com.ms.account_transaction.domain.model.enums;

import lombok.Getter;

/**
 * @author : Freddy Torres
 * file :  Status
 * @since : 10/3/2025, lun
 **/

@Getter
public enum Status {
    TRUE,
    FALSE;

    public static String getEstadoString(Status estado) {
        return TRUE.equals(estado) ? "True" : "False";
    }
}
