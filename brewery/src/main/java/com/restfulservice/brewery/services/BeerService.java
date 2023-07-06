package com.restfulservice.brewery.services;

import java.util.Collection;
import java.util.UUID;

import com.restfulservice.brewery.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID beerId);
	BeerDto saveBeer(BeerDto beer);
	BeerDto updateBeer(UUID beerId, BeerDto beerDto);
	void deleteBeerById(UUID beerId);
	Collection<BeerDto> listAllBeer();
}
