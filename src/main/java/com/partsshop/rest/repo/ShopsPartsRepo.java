package com.partsshop.rest.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.partsshop.rest.model.PartsCategory;
import com.partsshop.rest.model.ShopsParts;

public interface ShopsPartsRepo extends MongoRepository<ShopsParts,String>{
	
	
	@Query("{ $or: [ { 'partName': '?0' }, { 'partNameAr': '?0' } ] }")
	public List<ShopsParts> findByPartNameOrPartNameAr(String name);

}
