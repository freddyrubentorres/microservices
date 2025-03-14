package com.ms.account_transaction.mapper;
import com.ms.account_transaction.api.controller.dto.AccountDto;
import com.ms.account_transaction.domain.model.entity.Account;
import org.mapstruct.Mapper;

/**
 * @author : Freddy Torres
 * file :  AccountMapper
 * @since : 13/3/2025, jue
 **/

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toDto(Account account);
}

