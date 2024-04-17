package com.sanket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sanket.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
	
	
	@Query("from ItemEntity order by creationtime DESC")
	public List<ItemEntity> getAllItems();
	

	@Query("from ItemEntity e where e.itemname LIKE %:itemname%")
	public List<ItemEntity>  searchByItemName(@Param("itemname") String itemname);
	
	
	@Query("from ItemEntity e where e.itemcategory LIKE %:itemcategory%")
	public List<ItemEntity> searchByItemCategory(@Param("itemcategory") String itemcategory);

	@Query("from ItemEntity e where e.fk_userid=?1 order by creationtime DESC")
	public List<ItemEntity> findAllByFk_userid(@Param("fk_userid") Integer fk_userid);

}
