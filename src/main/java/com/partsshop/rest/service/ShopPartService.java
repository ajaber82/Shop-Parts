package com.partsshop.rest.service;

import java.util.List;

import com.partsshop.rest.dto.ShopsPartsRest;
import com.partsshop.rest.model.PartsCategory;


public interface ShopPartService {
	
	ShopsPartsRest saveShopPart(ShopsPartsRest shopsPartsRest );
	ShopsPartsRest update(ShopsPartsRest shopsPartsRest);
	ShopsPartsRest getShopPartById(String string); // problem
	List<ShopsPartsRest> getShopsParts();
	List<ShopsPartsRest> getShopsPartsByName(String name);
	void delete(String id);

}
