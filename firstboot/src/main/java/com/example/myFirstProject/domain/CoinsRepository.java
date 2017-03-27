package com.example.myFirstProject.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by serena on 3/25/17.
 */
public interface CoinsRepository extends PagingAndSortingRepository<Coins, Integer>  {
}
