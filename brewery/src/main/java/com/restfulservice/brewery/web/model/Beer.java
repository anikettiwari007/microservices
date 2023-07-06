package com.restfulservice.brewery.web.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Beer {

	private UUID id;
	private String beerName;
	private String beerStyle;
	private Long upc;
}
