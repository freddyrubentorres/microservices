package com.ms.account_transaction.api.controller;

import com.ms.account_transaction.common.ResponseUtil;
import com.ms.account_transaction.constants.ProcessConstants;
import com.ms.account_transaction.domain.service.ReportService;
import com.ms.account_transaction.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : Freddy Torres
 * file :  ReportController
 * @since : 11/3/2025, mar
 **/

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ProcessConstants.API_PATH_REPORTS)
public class ReportController {

    @Value("${controller.ok}")
    public String CONTROLLER_OK;

    private final ReportService reportService;

    @GetMapping(ProcessConstants.API_PATH_REPORTS_PING)
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("{\"status\":\"OK\"}");
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getTransactionsByDateAndPerson(
            @RequestParam("startDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam("identification") String identification
    ) throws ParseException {
        List<Map<String, Object>> report = reportService.getTransactionsByDateAndPerson(startDate, endDate, identification);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(CONTROLLER_OK, report));
    }
}
