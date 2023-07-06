package com.restfulservice.brewery.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfulservice.brewery.web.model.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, UUID>{

}
