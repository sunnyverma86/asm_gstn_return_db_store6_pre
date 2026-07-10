package com.deloitte.tab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.deloitte.tab.bean.RegistrationReportRequestDTO;
import com.deloitte.tab.bean.RegistrationReportResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegistrationReportServiceImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<RegistrationReportResponseDTO> generateReport(RegistrationReportRequestDTO request) {

		log.info("Registration Report Request : FromDate={}, ToDate={}", request.getFromDate(), request.getToDate());

		long startTime = System.currentTimeMillis();

		String sql = """
				SELECT
				    A.start_tm::date AS startdt,

				    SUM(
				        CASE
				            WHEN A.alertcd IS NOT NULL
				            THEN 1
				            ELSE 0
				        END
				    ) AS totalfetcharn,

				    SUM(
				        CASE
				            WHEN A.arnupdatesuccess = TRUE
				            THEN 1
				            ELSE 0
				        END
				    ) AS totalarnsuccess,

				    SUM(
				        CASE
				            WHEN A.arnupdatesuccess = FALSE
				            THEN 1
				            ELSE 0
				        END
				    ) AS totalarnfailure,

				    SUM(
				        CASE
				            WHEN A.aplty IN
				            (
				                'APLRG',
				                'APLCR',
				                'CNREV',
				                'CNREG',
				                'APLAC',
				                'APLAN',
				                'APLOC',
				                'APLWC',
				                'APLCW'
				            )
				            AND A.gstin IS NOT NULL
				            THEN 1
				            ELSE 0
				        END
				    ) AS totalfetchentity,

				    SUM(
				        CASE
				            WHEN A.aplty IN
				            (
				                'APLRG',
				                'APLCR',
				                'CNREV',
				                'CNREG',
				                'APLAC',
				                'APLAN',
				                'APLOC',
				                'APLWC',
				                'APLCW'
				            )
				            AND A.gstin IS NOT NULL
				            AND A.isentitysuccess = TRUE
				            THEN 1
				            ELSE 0
				        END
				    ) AS totalfetchsuccessentity,

				    SUM(
				        CASE
				            WHEN A.aplty IN
				            (
				                'APLRG',
				                'APLCR',
				                'CNREV',
				                'CNREG',
				                'APLAC',
				                'APLAN',
				                'APLOC',
				                'APLWC',
				                'APLCW'
				            )
				            AND A.gstin IS NOT NULL
				            AND A.isentitysuccess = FALSE
				            THEN 1
				            ELSE 0
				        END
				    ) AS totalfetchfailureentity,

				    COALESCE(B.insertcount,0) AS insertcount

				FROM
				(
				    SELECT

				        A."ALERT_ID" AS alertid,

				        A.start_tm,

				        A.end_tm,

				        A.issuccess,

				        A.msg,

				        A.jsondata->>'dayCount' AS dayCount,

				        B.issuccess AS arnupdatesuccess,

				        B.alertcd,

				        B.jsondata->'rgdtls'->>'aplty' AS aplty,

				        B.jsondata->>'gstin' AS gstin,

				        B.isentitysuccess

				    FROM gst_api_registration."ALERT_REGISTRATION" A

				    LEFT JOIN gst_api_registration."ALERT_DETAILS_REGISTRATION" B

				        ON A."ALERT_ID" = B."ALERT_ID"

				    AND B.alertcd IN
				    (
				        SELECT alertcd
				        FROM gst_api_registration."ALERT_FOR_ARN_UPDATE"
				    )

				) A

				LEFT JOIN
				(
				    SELECT

				        A.start_tm::date AS startdt,

				        COUNT(B."ALERT_DETAIL_ID") AS insertcount

				    FROM gst_api_registration."ALERT_REGISTRATION" A

				    INNER JOIN gst_api_registration."ALERT_DETAILS_REGISTRATION" B

				        ON A."ALERT_ID" = B."ALERT_ID"

				    INNER JOIN gst_api_registration."ALERT_FOR_ARN_UPDATE" C

				        ON B.alertcd = C.alertcd

				    WHERE

				        A.start_tm::date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)

				        AND B.issuccess = TRUE

				        AND B.jsondata->'rgdtls'->>'aplty' IN
				        (
				            'APLRG',
				            'APLCR',
				            'CNREV',
				            'CNREG',
				            'APLAC',
				            'APLAN',
				            'APLOC',
				            'APLWC',
				            'APLCW'
				        )

				        AND B.jsondata->>'gstin' IS NOT NULL

				        AND isentitysuccess = TRUE

				        AND B."ALERT_DETAIL_ID" IN
				        (
				            SELECT DISTINCT arn_detail_id
				            FROM gst_api_registration."RegularTaxpayer"
				            WHERE arn_detail_id <> -1

				            UNION

				            SELECT DISTINCT arn_detail_id
				            FROM gst_api_registration."TDS_TCS_Taxpayer"
				            WHERE arn_detail_id <> -1
				        )

				    GROUP BY
				        A.start_tm::date

				) B

				ON A.start_tm::date = B.startdt

				WHERE

				    A.start_tm::date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)

				    AND issuccess = TRUE

				GROUP BY

				    A.start_tm::date,

				    B.insertcount

				ORDER BY

				    A.start_tm::date DESC
				""";
		String logQuery = sql.replaceFirst("\\?", "'" + request.getFromDate() + "'")
				.replaceFirst("\\?", "'" + request.getToDate() + "'")
				.replaceFirst("\\?", "'" + request.getFromDate() + "'")
				.replaceFirst("\\?", "'" + request.getToDate() + "'");

		log.info("Registration Report Query : \n{}", logQuery);

		List<RegistrationReportResponseDTO> response = jdbcTemplate.query(

				sql,

				new Object[] {

						request.getFromDate(), request.getToDate(),

						request.getFromDate(), request.getToDate()

				},

				(rs, rowNum) -> {

					RegistrationReportResponseDTO dto = new RegistrationReportResponseDTO();

					dto.setStartDate(rs.getDate("startdt") == null ? null : rs.getDate("startdt").toLocalDate());

					dto.setTotalFetchArn(rs.getLong("totalfetcharn"));

					dto.setTotalArnSuccess(rs.getLong("totalarnsuccess"));

					dto.setTotalArnFailure(rs.getLong("totalarnfailure"));

					dto.setTotalFetchEntity(rs.getLong("totalfetchentity"));

					dto.setTotalFetchSuccessEntity(rs.getLong("totalfetchsuccessentity"));

					dto.setTotalFetchFailureEntity(rs.getLong("totalfetchfailureentity"));

					dto.setInsertCount(rs.getLong("insertcount"));

					return dto;

				});

		log.info("Registration Report Completed. Records={} TimeTaken={} ms", response.size(),
				(System.currentTimeMillis() - startTime));

		return response;

	}

}
