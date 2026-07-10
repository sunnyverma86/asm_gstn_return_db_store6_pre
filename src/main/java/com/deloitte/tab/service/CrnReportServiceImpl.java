package com.deloitte.tab.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.deloitte.tab.bean.CrnReportRequestDTO;
import com.deloitte.tab.bean.CrnReportResponseDTO;

@Service
public class CrnReportServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(CrnReportServiceImpl.class);

	private final JdbcTemplate jdbcTemplate;

	public CrnReportServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<CrnReportResponseDTO> generateCrnReport(CrnReportRequestDTO request) {

		long startTime = System.currentTimeMillis();

		String sql = """
				    SELECT
				        A.startdt,
				        COALESCE(B.casetyp,'No Data') AS casetyp,
				        COALESCE(B.downloadcnt,0) AS downloadcnt,
				        COALESCE(C.InsertCount,0) AS insertcount

				    FROM
				    (
				        SELECT DISTINCT
				            startdt::date AS startdt
				        FROM filecounter.crn_common
				        WHERE startdt::date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)
				    ) A

				    LEFT JOIN
				    (
				        SELECT
				            A.startdt,
				            A.casetyp,
				            COUNT(A.downloadcrn)::INT AS downloadcnt

				        FROM
				        (
				            SELECT
				                startdt::date AS startdt,
				                casetyp,
				                crndata->>'crncnt' AS crncnt,
				                B.crn AS downloadcrn

				            FROM filecounter.crn_common A

				            LEFT JOIN filecounter.crn_detail_common B
				                ON A."Id" = B.crn_id

				            WHERE
				                B.casetyp = ?
				                AND B.issuccess = TRUE
				                AND startdt::date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)

				        ) A

				        GROUP BY
				            A.startdt,
				            A.casetyp

				    ) B

				    ON A.startdt = B.startdt

				    LEFT JOIN
				    (
				        SELECT
				            A.startdt,
				            A.casetyp,
				            SUM(InsertCount)::INT AS InsertCount

				        FROM
				        (
				            SELECT

				                startdt::date AS startdt,

				                casetyp,

				                CASE

				                    WHEN Refund.log_id IS NOT NULL THEN 1

				                    WHEN ADJVP.log_id IS NOT NULL THEN 1

				                    WHEN ADJSR.log_id IS NOT NULL THEN 1

				                    WHEN ADJDT.log_id IS NOT NULL THEN 1

				                    WHEN APPEL.log_id IS NOT NULL THEN 1

				                    ELSE 0

				                END AS InsertCount

				            FROM filecounter.crn_common A

				            LEFT JOIN filecounter.crn_detail_common B

				                ON A."Id" = B.crn_id

				            LEFT JOIN
				            (
				                SELECT DISTINCT log_id
				                FROM gst_api_refund."GSTRFD_01_REFUND"

				            ) Refund

				            ON B.details_id = Refund.log_id
				            AND B.casetyp='RFUND'

				            LEFT JOIN
				            (
				                SELECT DISTINCT log_id
				                FROM gst_api_adjudication."VP_ADJVP"

				            ) ADJVP

				            ON B.details_id = ADJVP.log_id
				            AND B.casetyp='ADJVP'

				            LEFT JOIN
				            (
				                SELECT DISTINCT log_id
				                FROM gst_api_adjudication."SRNotice_ADJSR"

				            ) ADJSR

				            ON B.details_id = ADJSR.log_id
				            AND B.casetyp='ADJSR'

				            LEFT JOIN
				            (

				                SELECT log_id
				                FROM gst_api_adjudication."DTNoticeDRC01_ADJDT"

				                UNION

				                SELECT log_id
				                FROM gst_api_adjudication."DTNoticeDRC02_ADJDT"

				                UNION

				                SELECT log_id
				                FROM gst_api_adjudication."DTAddIntimationData_ADJDT"

				            ) ADJDT

				            ON B.details_id = ADJDT.log_id
				            AND B.casetyp='ADJDT'

				            LEFT JOIN
				            (

				                SELECT log_id
				                FROM appeal."REG_APPEAL"

				                UNION

				                SELECT log_id
				                FROM appeal.asmt_appeal_withdemand

				                UNION

				                SELECT log_id
				                FROM appeal.enfappeal_appeal

				                UNION

				                SELECT log_id
				                FROM appeal.othappeal_appeal

				                UNION

				                SELECT log_id
				                FROM appeal.refappeal_appeal

				            ) APPEL

				            ON B.details_id = APPEL.log_id
				            AND B.casetyp='APPEL'

				            WHERE
				                B.issuccess = TRUE
				                AND B.casetyp = ?
				                AND startdt::date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)

				    ) A

				    GROUP BY
				        A.startdt,
				        A.casetyp

				) C

				ON A.startdt = C.startdt
				AND B.casetyp = C.casetyp

				ORDER BY
				    A.startdt DESC,
				    B.casetyp
				""";

		log.info("CRN Report Request : FromDate={}, ToDate={}, CaseType={}", request.getFromDate(), request.getToDate(),
				request.getCaseType());

		List<CrnReportResponseDTO> result = jdbcTemplate.query(sql,
				new Object[] { request.getFromDate(), request.getToDate(),

						request.getCaseType(), request.getFromDate(), request.getToDate(),

						request.getCaseType(), request.getFromDate(), request.getToDate() },
				(rs, rowNum) -> {

					CrnReportResponseDTO dto = new CrnReportResponseDTO();

					dto.setStartDate(rs.getDate("startdt") != null ? rs.getDate("startdt").toLocalDate() : null);

					dto.setCaseType(rs.getString("casetyp"));

					dto.setDownloadCount(rs.getObject("downloadcnt", Integer.class));

					dto.setInsertCount(rs.getObject("insertcount", Integer.class));

					return dto;

				});

		log.info("CRN Report Generated Successfully. Records={}, Time={} ms", result.size(),
				(System.currentTimeMillis() - startTime));

		return result;

	}

}