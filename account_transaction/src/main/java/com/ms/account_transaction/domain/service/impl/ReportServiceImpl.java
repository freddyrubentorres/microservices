package com.ms.account_transaction.domain.service.impl;

import com.ms.account_transaction.domain.repository.AccountTransactionsRepository;
import com.ms.account_transaction.domain.service.ReportService;
import com.ms.account_transaction.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : Freddy Torres
 * file :  ReportServiceImpl
 * @since : 12/3/2025, mié
 **/

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:messages.properties")
public class ReportServiceImpl implements ReportService {

    @Value("${message.entity.no.found}")
    public String ENTITY_NO_FOUND;

    private final AccountTransactionsRepository accountTransactionsRepository;

    @Override
    @Transactional
    public List<Map<String, Object>> getTransactionsByDateAndPerson(Date startDate, Date endDate, String identification) throws ParseException {
        List<Map<String, Object>> reportList=accountTransactionsRepository.procedureName(identification,startDate,endDate);
        if (reportList.isEmpty()) {
            throw new GeneralException(ENTITY_NO_FOUND);
        }
        return reportList;
    }
}
