package com.co.challenge.devsubankaccount.services;

import com.co.challenge.devsubankaccount.repositories.ReportRepository;
import com.co.challenge.devsubankaccount.repositories.domain.Report;
import com.co.challenge.devsubankaccount.services.mappers.ReportMapper;
import com.co.challenge.devsubankaccount.web.model.ReportDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.co.challenge.devsubankaccount.utils.Constants.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    @Override
    public List<ReportDto> getReportByDates(UUID userId, Date fromDate, Date toDate) {
        log.debug("Creating report for user: " + userId );

        List<Object[]> list = reportRepository.getReportByDates(userId, fromDate, toDate);
        log.debug("Report REaDy: " + list.size());
        List<Report> collectReport = list.stream().map(report -> Report
                .builder()
                .numberAccount((Integer) report[0])
                .accountType((String) report[1])
                .initialBalance((Double) report[2])
                .createdDate((Date) report[3])
                .amount((Double) report[4])
                .availableAmount((Double) report[5])
                .state((String) report[6])
                .build()).collect(Collectors.toList());

//        List<Report> collectReport = list.stream().map(e -> (Report) e[0]).collect(Collectors.toList());
        return collectReport.stream().map(reportMapper::reportToReportDto).collect(Collectors.toList());
//        return null;
    }

    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > CERO) {
            queryPageNumber = pageNumber - ONE;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > THOUSAND) {
                queryPageSize = THOUSAND;
            } else {
                queryPageSize = pageSize;
            }
        }

        Sort sort = Sort.by(Sort.Order.asc(NUMBER_ACCOUNT));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }
}
