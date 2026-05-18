package com.deloitte.service.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogbackConfig {

    @Value("${LOG_DIR:/logs}")
    private String logDir;

  //  @Value("${FILE_DIR:/downloads}")
    @Value("${file.dir}")
    private String fileDir;
    
    
    @Value("${file.dir.enforcement}")
    private String fileDirEnforcement;
    
    @Value("${file.dir.adjudication}")
    private String fileDirAdjudication;

    public String getLogDir() {
        return logDir;
    }

    public String getFileDir() {
        return fileDir;
    }

	public String getFileDirEnforcement() {
		return fileDirEnforcement;
	}

	public String getFileDirAdjudication() {
		return fileDirAdjudication;
	}
	
	
    
    
}