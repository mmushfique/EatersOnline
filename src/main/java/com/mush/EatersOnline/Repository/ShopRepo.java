package com.mush.EatersOnline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mush.EatersOnline.Model.Shop;

@Repository
public interface ShopRepo extends JpaRepository<Shop,Long>{
	
	@Query("SELECT s FROM Shop s WHERE s.shopusername = :shopusername")
	public Shop getShopByShopUsername(@Param("shopusername") String shopusername);
	
	@Query("SELECT count(id) FROM Shop")
	public int getShop();
	
	@Query(value = "SELECT count(id) FROM Food s WHERE s.shopusername = :shopusername")
	public int countFood(@Param("shopusername") String shopusername);
	
	@Query(value = "SELECT count(id) FROM Bill s WHERE s.shopusername = :shopusername")
	public int countOrder(@Param("shopusername") String shopusername);
	
	@Query(value = "SELECT count(id) FROM Bill s WHERE s.shopusername = :shopusername AND s.pending=0")
	public int countpreOrder(@Param("shopusername") String shopusername);
	
	@Query(value = "SELECT onlinestatus FROM Shop s WHERE s.shopusername = :shopusername")
	public boolean onlinestatus(@Param("shopusername") String shopusername);
}
