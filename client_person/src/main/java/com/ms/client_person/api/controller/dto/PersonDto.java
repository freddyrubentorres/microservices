package com.ms.client_person.api.controller.dto;

import com.ms.client_person.domain.model.enums.Gender;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : Freddy Torres
 * file :  PersonDto
 * @since : 9/3/2025, dom
 **/

@Setter
@Getter
@ToString
public class PersonDto {
    private Long personId;

    @NotBlank(message = "{message.persona.nombre.NotBlank}")
    @Size(max = 50, message = "{message.persona.nombre.Size}")
    private String name;

    @NotBlank(message = "{message.persona.apellido.NotBlank}")
    @Size(max = 50, message = "{message.persona.apellido.Size}")
    private String last_name;

    @Column(name = "gender")
    @NotNull(message = "{message.persona.genero.NotNull}")
    private Gender gender;

    @NotNull(message = "{message.persona.edad.NotNull}")
    @Positive(message = "{message.persona.edad.Positive}")
    @Min(value = 13, message = "{message.persona.edad.Min}")
    private Long age;

    @NotNull(message = "{message.persona.identificacion.NotNull}")
    @Pattern(regexp = "^[0-9]{10}$", message = "{message.persona.identificacion.Pattern}")
    private String identification;

    @NotBlank(message = "{message.persona.direccion.NotNull}")
    @Size(min = 10, max = 255, message = "{message.persona.direccion.Size}")
    private String address;

    @NotBlank(message = "{message.persona.telefono.NotNull}")
    @Pattern(regexp = "^[0-9]{7}$|^[0-9]{10}$", message = "{message.persona.telefono.Pattern}")
    private String phone;
}
