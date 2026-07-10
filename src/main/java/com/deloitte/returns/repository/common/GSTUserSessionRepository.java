package com.deloitte.returns.repository.common;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.common.entity.GSTUserSession;

@Repository
public interface GSTUserSessionRepository extends JpaRepository<GSTUserSession, Long> {

	List<GSTUserSession> findByUserNameOrderByCreateDateTimeDesc(String username);

	Optional<GSTUserSession> findTopByUserNameOrderByAuthDateTimeDesc(String userName);

}
