package com.co.challenge.devsubankaccount.web.controllers;

import com.co.challenge.devsubankaccount.services.ReportService;
import com.co.challenge.devsubankaccount.web.model.ReportDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.co.challenge.devsubankaccount.utils.Constants.REPORT_PATH;


@Slf4j
@RequiredArgsConstructor
@RestController
public class ReportsController {

    private final ReportService reportService;

    @GetMapping(REPORT_PATH)
    public List<ReportDto> getReportByDates(
            @RequestParam(value = "userId", required = true)UUID userId,
            @RequestParam(value = "from", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,
            @RequestParam(value = "to", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {


        return reportService.getReportByDates(userId, fromDate, toDate);
    }
}
