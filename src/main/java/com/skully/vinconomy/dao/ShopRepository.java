package com.skully.vinconomy.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skully.vinconomy.model.Shop;
import com.skully.vinconomy.model.ShopId;

@Repository
public interface ShopRepository extends CrudRepository<Shop, ShopId> {

}
