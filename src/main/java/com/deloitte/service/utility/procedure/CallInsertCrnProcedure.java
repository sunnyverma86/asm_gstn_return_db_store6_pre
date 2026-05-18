package com.deloitte.service.utility.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CallInsertCrnProcedure {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void callInsertCrnProcedure(Timestamp startDt, Timestamp endDt, Integer crnCnt, Boolean isSuccess,
			String msgJson, String crnDataJson) {

		String sql = "{ call filecounter.insert_crn(?, ?, ?, ?, ?::jsonb, ?::jsonb) }";

		log.info("▶️ Calling procedure insert_crn with params: startDt={}, endDt={}, crnCnt={}, isSuccess={}", startDt,
				endDt, crnCnt, isSuccess);

		jdbcTemplate.execute((Connection con) -> {

			CallableStatement cs = con.prepareCall(sql);

			cs.setTimestamp(1, startDt);
			cs.setTimestamp(2, endDt);
			cs.setInt(3, crnCnt);
			cs.setBoolean(4, isSuccess);

			cs.setString(5, msgJson); // jsonb
			cs.setString(6, crnDataJson); // jsonb

			return cs;
		});

		log.info("✅ Procedure insert_crn executed successfully");
	}

}
