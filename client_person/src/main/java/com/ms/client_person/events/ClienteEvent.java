package com.ms.client_person.events;

import com.ms.client_person.api.controller.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * @author : Freddy Torres
 * file :  ClienteEvent
 * @since : 8/3/2025, sáb
 **/

@Data
public class ClienteEvent {
    @Enumerated(EnumType.STRING)
    private Accion accion;
    private String idEvento = UUID.randomUUID().toString();
    private Date fechaEvento = new Date();
    private Long clienteId;
    private String nombre;
    private String apellido;
    private String identificacion;
    private AccountType accountType;
    private Double initialBalance;
    public ClienteEvent(Accion accion,Long clienteId,String nombre,String apellido,String identificacion, AccountType accountType,Double initialBalance){
        this.accion=accion;
        this.clienteId=clienteId;
        this.nombre=nombre;
        this.apellido=apellido;
        this.identificacion=identificacion;
        this.accountType=accountType;
        this.initialBalance=initialBalance;
    }
}