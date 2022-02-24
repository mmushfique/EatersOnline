package com.mush.EatersOnline.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mush.EatersOnline.Model.Bill;


@Repository
public interface BillRepo extends JpaRepository<Bill,Long> {

	@Query("SELECT u FROM Bill u WHERE u.pending=0 AND u.shopusername = :shopusername")
	public List<Bill> getMyOrders(@Param("shopusername") String shopusername);
	
	
//	@Query(value="SELECT MAX(billno) FROM Bill")
//	public long max();
}
