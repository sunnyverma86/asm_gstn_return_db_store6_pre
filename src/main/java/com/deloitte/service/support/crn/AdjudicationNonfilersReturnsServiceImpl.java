package com.deloitte.service.support.crn;

import org.springframework.stereotype.Service;

import com.deloitte.common.constant.Constants;
import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.entity.registration.ReturnCountCrnJson;
import com.deloitte.returns.repository.common.CrnCaseHandler;
import com.deloitte.service.abs.AbstractCommonCrnServiceImpl;

@Service("ADJNF")
public class AdjudicationNonfilersReturnsServiceImpl extends AbstractCommonCrnServiceImpl implements CrnCaseHandler {

	@Override
	protected String getApiConstant() {
		return Constants.GET_RETURN_FILE_DETAIL_Adjudication_Determination_Tax_DATA;
	}

	@Override
	public String processJson(ReturnCountCrnJson json, MasterData masterData, String startDateTime, String endDateTime,
			GSTUserSession session, String caseType) {

		return processCommon(json, masterData, startDateTime, endDateTime, session, caseType);
	}
}