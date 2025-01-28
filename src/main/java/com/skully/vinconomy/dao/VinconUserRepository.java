package com.skully.vinconomy.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skully.vinconomy.model.VinconUser;

@Repository
public interface VinconUserRepository extends CrudRepository<VinconUser, Long> {

}
