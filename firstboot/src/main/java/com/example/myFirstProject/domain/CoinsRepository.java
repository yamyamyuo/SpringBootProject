package com.example.myFirstProject.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by serena on 3/25/17.
 */
@Repository
public interface CoinsRepository extends CrudRepository<Coins, Integer>  {
    Coins getCoins();
}
