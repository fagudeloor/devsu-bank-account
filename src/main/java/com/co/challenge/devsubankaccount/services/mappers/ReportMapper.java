package com.co.challenge.devsubankaccount.services.mappers;

import com.co.challenge.devsubankaccount.repositories.domain.Report;
import com.co.challenge.devsubankaccount.web.model.ReportDto;
import org.mapstruct.Mapper;

@Mapper
public interface ReportMapper {

    Report reportDtoToReport(ReportDto reportDto);

    ReportDto reportToReportDto(Report report);
}
