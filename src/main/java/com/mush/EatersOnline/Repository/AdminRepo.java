package com.mush.EatersOnline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mush.EatersOnline.Model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Long>{

	@Query("SELECT a FROM Admin a WHERE a.username = :username")
	public Admin getAdminByUsername(@Param("username") String username);
}
