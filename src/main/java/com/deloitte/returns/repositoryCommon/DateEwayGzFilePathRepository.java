package com.deloitte.returns.repositoryCommon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.DateEwayGzFilePath;

@Repository
public interface DateEwayGzFilePathRepository extends JpaRepository<DateEwayGzFilePath, Long> {

}
