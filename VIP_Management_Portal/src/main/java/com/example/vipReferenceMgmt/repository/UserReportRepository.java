package com.example.vipReferenceMgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.vipReferenceMgmt.entity.UserReport;

public interface UserReportRepository extends JpaRepository<UserReport, Long> {
	@Query("""
		    SELECT u FROM UserReport u
		    WHERE LOWER(u.organization) = LOWER(:organization)
		      AND (COALESCE(:designation, '') = '' OR LOWER(u.designation) = LOWER(:designation))
		      AND (COALESCE(:office, '') = '' OR LOWER(u.office) = LOWER(:office))
		""")
		List<UserReport> findCaseInsensitive(
		    @Param("organization") String organization,
		    @Param("designation") String designation,
		    @Param("office") String office
		);

}
