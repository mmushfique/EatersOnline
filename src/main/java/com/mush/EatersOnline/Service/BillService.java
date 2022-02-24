package com.mush.EatersOnline.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mush.EatersOnline.Model.Bill;
import com.mush.EatersOnline.Model.Food;
import com.mush.EatersOnline.Repository.BillRepo;

@Service
public class BillService {

	
	@Autowired
    private BillRepo repo;
	
	public List<Bill> listAll() {
        return repo.findAll();
    }
	
	public List<Bill> listAllMy(String shopusername) {
        return repo.getMyOrders(shopusername);
    }
     
    public void save(Bill  bill) {
        repo.save(bill);
    }
    
    public Bill get(long id) {
    	return repo.findById(id).get();
    }
}
