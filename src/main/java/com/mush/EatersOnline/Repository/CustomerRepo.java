package com.mush.EatersOnline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mush.EatersOnline.Model.Customer;
import com.mush.EatersOnline.Model.Shop;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
	
	@Query("SELECT u FROM Customer u WHERE u.email = :email")
	public Customer getUserByEmail(@Param("email") String email);

	@Query("SELECT count(id) FROM Customer")
	public int getCus();
}
