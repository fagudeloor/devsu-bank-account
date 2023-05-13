package com.co.challenge.devsubankaccount.services;


import com.co.challenge.devsubankaccount.web.model.ReportDto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ReportService {

    List<ReportDto> getReportByDates(UUID userId, Date fromDate, Date toDate);
}
