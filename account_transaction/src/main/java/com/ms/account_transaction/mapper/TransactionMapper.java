package com.ms.account_transaction.mapper;

import com.ms.account_transaction.api.controller.dto.TransactionDto;
import com.ms.account_transaction.domain.model.entity.Transaction;
import org.mapstruct.Mapper;

/**
 * @author : Freddy Torres
 * file :  TransactionMapper
 * @since : 11/3/2025, mar
 **/

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto toDto(Transaction account);
}
