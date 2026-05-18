package com.deloitte.service.support;

import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.entity.AlertJson;

public interface ArnUpdateHandler {

	String processJson(AlertJson returnCountCrnJson, MasterData masterData, String startDateTime, String endDateTime,
			GSTUserSession session, String strYear);

}
