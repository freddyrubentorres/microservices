package com.ms.account_transaction.domain.model.entity;

import com.ms.account_transaction.domain.model.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author : Freddy Torres
 * file :  Person
 * @since : 12/3/2025, mié
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
    private String name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "age")
    private Long age;

    @Column(name = "identification", unique = true)
    private String identification;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;
}
