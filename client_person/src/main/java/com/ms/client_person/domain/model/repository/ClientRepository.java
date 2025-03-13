package com.ms.client_person.domain.model.repository;

import com.ms.client_person.domain.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Freddy Torres
 * file :  ClientRepository
 * @since : 9/3/2025, dom
 **/

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByIdentification(String identification);
    @Query("SELECT COALESCE(MAX(c.clientId), 0) FROM Client c")
    Long findMaxClientId();
    Optional<Client> findByClientId(Long clientId);
    boolean existsByClientId(Long clientId);
    void deleteByClientId(Long clientId);
}
