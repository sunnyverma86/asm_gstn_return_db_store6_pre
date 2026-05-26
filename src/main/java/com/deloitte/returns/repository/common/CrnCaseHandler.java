package com.deloitte.returns.repository.common;

import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.entity.ReturnCountCrn;
import com.deloitte.returns.entity.filecounter.ReturnCountCrnJson;

public interface CrnCaseHandler {



	String processJson(ReturnCountCrnJson returnCountCrnJson, MasterData masterData, String startDateTime,
			String endDateTime, GSTUserSession gstUserSessions, String caseType);

}
