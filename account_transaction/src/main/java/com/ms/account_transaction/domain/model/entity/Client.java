package com.ms.account_transaction.domain.model.entity;

import com.ms.account_transaction.domain.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author : Freddy Torres
 * file :  Client
 * @since : 12/3/2025, mié
 **/

@Table(name = "client", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
@PrimaryKeyJoinColumn(name = "person_id")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Client extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", unique = true, nullable = false)
    private Long clientId;

    @Column(name = "email",unique=true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}