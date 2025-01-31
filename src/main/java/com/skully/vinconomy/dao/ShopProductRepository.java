package com.skully.vinconomy.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.skully.vinconomy.model.ShopProduct;

public interface ShopProductRepository extends CrudRepository<ShopProduct, ShopProductId> {

	@Query("DELETE FROM ShopProduct p WHERE p.id.nodeId = :nodeId AND p.id.shopId = :shopId AND p.id.x = :X AND p.id.y = :Y AND p.id.z = :Z")
	void deleteByStall(@Param("nodeId") long nodeId, @Param("shopId") long shopId, @Param("x") int x, @Param("y") int y, @Param("z") int z);

	@Query("DELETE FROM ShopProduct p WHERE p.id.nodeId = :nodeId AND p.id.shopId = :shopId")
	void deleteByShopId(@Param("nodeId") long nodeId, @Param("shopId") long shopId);

}
