package com.skully.vinconomy.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skully.vinconomy.model.ApiUser;

@Repository
public interface ApiUserRepository extends CrudRepository<ApiUser, Long> {

	ApiUser findByUsername(String username);

}
