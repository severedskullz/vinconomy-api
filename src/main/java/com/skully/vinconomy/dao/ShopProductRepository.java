package com.skully.vinconomy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.skully.vinconomy.model.ShopProduct;
import com.skully.vinconomy.model.ShopProductId;

public interface ShopProductRepository extends CrudRepository<ShopProduct, ShopProductId> {

	@Transactional
	@Modifying
	@Query("DELETE FROM ShopProduct p WHERE p.id.nodeId = :nodeId AND p.id.x = :X AND p.id.y = :Y AND p.id.z = :Z")
	void deleteByStall(@Param("nodeId") long nodeId, @Param("X") int x, @Param("Y") int y, @Param("Z") int z);

	@Transactional
	@Modifying
	@Query("UPDATE ShopProduct p SET p.id.shopId = :shopId WHERE p.id.nodeId = :nodeId AND p.id.x = :X AND p.id.y = :Y AND p.id.z = :Z")
	void updateStallShopId(@Param("nodeId") long nodeId, @Param("X") int x, @Param("Y") int y, @Param("Z") int z, @Param("shopId") long shopId);

	
	@Transactional
	@Modifying
	@Query("DELETE FROM ShopProduct p WHERE p.id.nodeId = :nodeId AND p.id.shopId = :shopId")
	void deleteByShopId(@Param("nodeId") long nodeId, @Param("shopId") long shopId);

	@Query("FROM ShopProduct WHERE id.nodeId = :nodeId AND id.x = :X AND id.y = :Y AND id.z = :Z ORDER BY id.stallSlot ASC")
	List<ShopProduct> getProductsForStall(@Param("nodeId") long nodeId, @Param("X") int x, @Param("Y") int y, @Param("Z") int z);

	@Query("FROM ShopProduct where id.nodeId = :nodeId AND id.shopId = :shopId")
	List<ShopProduct> findByNodeIdAndShopId(@Param("nodeId")long nodeId, @Param("shopId")int shopId);
}
