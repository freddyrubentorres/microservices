package com.ms.account_transaction.domain.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : Freddy Torres
 * file :  ReportService
 * @since : 12/3/2025, mié
 **/

public interface ReportService {
    List<Map<String, Object>> getTransactionsByDateAndPerson(Date startDate, Date endDate, String identification) throws ParseException;
}
