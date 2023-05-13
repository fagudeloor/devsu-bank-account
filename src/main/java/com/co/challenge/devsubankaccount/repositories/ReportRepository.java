package com.co.challenge.devsubankaccount.repositories;

import com.co.challenge.devsubankaccount.repositories.domain.Report;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.co.challenge.devsubankaccount.utils.Constants.GET_REPORT_BY_DATES;

@Repository
public class  ReportRepository {
    private final EntityManagerFactory emf;

    public ReportRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Object[]> getReportByDates(UUID userId, Date fromDate, Date toDate) {
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager
                .createQuery(GET_REPORT_BY_DATES);
        query.setParameter("userId", userId);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);

        return query.getResultList();
    }
//    @Query(GET_REPORT_BY_DATES)
//    List<Report> getReportByDates(UUID userId, Date fromDate, Date toDate);
}
