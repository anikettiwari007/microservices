package com.restfulservice.brewery.services;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.restfulservice.brewery.repository.BeerRepository;
import com.restfulservice.brewery.web.model.Beer;
import com.restfulservice.brewery.web.model.BeerDto;

@Service
public class BeerServiceImpl implements BeerService{

	private final BeerRepository beerRepository;
	
	public BeerServiceImpl(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	@Override
	public BeerDto getBeerById(UUID beerId) {
		Optional<Beer> b = beerRepository.findById(beerId);
		if(b.isPresent()) {			
			BeerDto beer = new BeerDto();
			BeanUtils.copyProperties(b, beer);
			return beer;
		}
		else {
			return null;
		}
	}

	@Override
	public BeerDto saveBeer(BeerDto beerDto) {
			Beer beer = new Beer();
			BeanUtils.copyProperties(beerDto, beer);
			beerRepository.save(beer);
			return beerDto;
	}
	
	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		Optional<Beer> b = beerRepository.findById(beerId);
		if(b.isPresent()) {			
			Beer beer = new Beer();
			BeanUtils.copyProperties(beerDto, beer);
			beerRepository.save(beer);
			return beerDto;
		}
		else {			
			return null;
		}
	}

	@Override
	public void deleteBeerById(UUID beerId) {
		beerRepository.deleteById(beerId);		
	}

	@Override
	public Collection<BeerDto> listAllBeer() {
		return beerRepository.findAll().stream().map(beer -> new BeerDto(beer.getId(), beer.getBeerName(), beer.getBeerStyle(), beer.getUpc())).toList();
	}
	
	
}
