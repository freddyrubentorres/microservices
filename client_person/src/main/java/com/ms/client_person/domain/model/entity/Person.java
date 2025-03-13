package com.ms.client_person.domain.model.entity;

import com.ms.client_person.domain.model.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

/**
 * @author : Freddy Torres
 * file :  Person
 * @since : 9/3/2025, dom
 **/

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "person", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"identification"})
})
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "name")
    @NotBlank(message = "{message.persona.nombre.NotBlank}")
    @Size(max = 50, message = "{message.persona.nombre.Size}")
    private String name;

    @Column(name = "last_name")
    @NotBlank(message = "{message.persona.apellido.NotBlank}")
    @Size(max = 50, message = "{message.persona.apellido.Size}")
    private String last_name;

    @Column(name = "gender")
    @NotNull(message = "{message.persona.genero.NotNull}")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "age")
    @NotNull(message = "{message.persona.edad.NotNull}")
    @Positive(message = "{message.persona.edad.Positive}")
    @Min(value = 13, message = "{message.persona.edad.Min}")
    private Long age;

    @Column(name = "identification",unique=true)
    @NotNull(message = "{message.persona.identificacion.NotNull}")
    @Pattern(regexp = "^[0-9]{10}$", message = "{message.persona.identificacion.Pattern}")
    private String identification;

    @Column(name = "address")
    @NotBlank(message = "{message.persona.direccion.NotNull}")
    @Size(min = 10, max = 255, message = "{message.persona.direccion.Size}")
    private String address;

    @Column(name = "phone")
    @NotBlank(message = "{message.persona.telefono.NotNull}")
    @Pattern(regexp = "^[0-9]{7}$|^[0-9]{10}$", message = "{message.persona.telefono.Pattern}")
    private String phone;

}
