package com.partsshop.rest.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.partsshop.rest.model.Car;
import com.partsshop.rest.model.PartsCategory;
import com.partsshop.rest.model.Shops;
import com.partsshop.rest.model.ShopsParts;

public interface ShopsPartsRepo extends MongoRepository<ShopsParts,String>{
	
	
	//@Query("{ $or: [ { 'partName': '?0' }, { 'partNameAr': '?0' } ] }")
	@Query ("{ $and: ["
						+ "{ $or : [ { 'Car.make' : '?0' }, { 'Car.makeAr' : '?0' } ] },"
						+ "{ $or : [ { 'Car.model' : '?0' },{ 'Car.modelAr': '?0' } ] },"
						+ "{ $or : [ { 'Part.name' : '?0' },{ 'part.nameAr': '?0' } ] }, "
						+ "{ 'year' : '?0' }"
					+"]"
						
			+"}")	
	
	
	

	public List<ShopsParts> findCarsByCriteria( PartsCategory partName, Car carMake, Car carModel,Integer year);

}
