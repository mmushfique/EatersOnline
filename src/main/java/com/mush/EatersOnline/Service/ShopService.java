package com.mush.EatersOnline.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mush.EatersOnline.Model.Shop;
import com.mush.EatersOnline.Repository.ShopRepo;

@Service
public class ShopService {
	
	@Autowired
	private ShopRepo repo;
	
	public List<Shop> listAll() {
        return repo.findAll();
    }
     
    public void save(Shop shop) {
        repo.save(shop);
    }
    
    public Shop get(long id) {
        return repo.findById(id).get();
    }
    
    public Shop get(String shopusername) {
        return repo.getShopByShopUsername(shopusername);
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}
