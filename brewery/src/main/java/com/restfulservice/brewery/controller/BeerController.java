package com.restfulservice.brewery.controller;

import java.util.Collection;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restfulservice.brewery.services.BeerService;
import com.restfulservice.brewery.web.model.BeerDto;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
	
	private final BeerService beerService;

	public BeerController(BeerService beerService) {
		this.beerService = beerService;
	}

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId){
		return new ResponseEntity<BeerDto>(beerService.getBeerById(beerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BeerDto> saveBeer(@RequestBody BeerDto beerDto){
		return new ResponseEntity<BeerDto>(beerService.saveBeer(beerDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDto> updateBeer(@PathVariable UUID beerId, @RequestBody BeerDto beerDto){
		if(beerService.updateBeer(beerId, beerDto)==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {			
			return new ResponseEntity<BeerDto>(beerService.updateBeer(beerId, beerDto), HttpStatus.ACCEPTED);
		}
	}
	
	@DeleteMapping("/{beerId}")
	public ResponseEntity deleteBeer(@PathVariable UUID beerId) {
		beerService.deleteBeerById(beerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping()
	public ResponseEntity<Collection<BeerDto>> findAll(){
		return new ResponseEntity<Collection<BeerDto>>(beerService.listAllBeer(), HttpStatus.OK);
	}
}
