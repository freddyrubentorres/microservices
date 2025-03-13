package com.ms.client_person.domain.model.entity;

import com.ms.client_person.api.controller.enums.AccountType;
import com.ms.client_person.domain.model.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * @author : Freddy Torres
 * file :  Client
 * @since : 9/3/2025, dom
 **/

@Entity
@Table(name = "client", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
@PrimaryKeyJoinColumn(name = "person_id")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Client extends Person {

    @Column(name = "client_id", unique = true, nullable = false)
    private Long clientId;

    @Column(name = "email",unique=true)
    @NotBlank(message = "{message.entity.cliente.email.NotBlank}")
    @Email(message = "{message.entity.cliente.email.Email}")
    private String email;

    @NotBlank(message = "{message.entity.cliente.password.NotBlank}")
    private String password;

    @Column(name = "status")
    @NotNull(message = "{message.entity.cliente.estado.NotNull}")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Transient
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Transient
    private Double initialBalance;
}