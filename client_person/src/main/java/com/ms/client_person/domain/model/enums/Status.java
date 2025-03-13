package com.ms.client_person.domain.model.enums;

import lombok.Getter;

/**
 * @author : Freddy Torres
 * file :  Estado
 * @since : 1/3/2025, sáb
 **/

@Getter
public enum Status {
    TRUE,
    FALSE;

    public static String getEstadoString(Status estado) {
        return TRUE.equals(estado) ? "True" : "False";
    }
}
