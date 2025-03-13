package com.ms.client_person.api.controller.dto;

import com.ms.client_person.api.controller.enums.AccountType;
import com.ms.client_person.domain.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * @author : Freddy Torres
 * file :  ClientDto
 * @since : 9/3/2025, dom
 **/

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto extends PersonDto{
    private Long clientId;

    @NotBlank(message = "{message.entity.cliente.email.NotBlank}")
    @Email(message = "{message.entity.cliente.email.Email}")
    private String email;

    @NotBlank(message = "{message.entity.cliente.password.NotBlank}")
    private String password;

    @NotNull(message = "{message.entity.cliente.estado.NotNull}")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull(message = "{message.entity.account.tipo.NotNull}")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotNull(message = "{message.entity.account.initial_balance.NotNull}")
    @DecimalMin(value = "1.0", message = "{message.entity.account.initial_balance.Min}")
    @DecimalMax(value = "1000000.0", message = "{message.entity.account.initial_balance.Max}")
    private Double initialBalance;

}
