package com.deloitte.service;

import java.util.List;

import com.deloitte.common.bean.ReportRequestDTO;
import com.deloitte.common.bean.ReportResponseDTO;

public interface ReportService {

	List<ReportResponseDTO> generateReport(ReportRequestDTO request);

}
