package com.sanket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sanket.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
	
	
	@Query("from ReviewEntity e where e.fk_itemid=:itemid order by creationtime DESC")
	public List<ReviewEntity> getReviewsByItemId(Integer itemid);
	
	@Query("from ReviewEntity e where e.fk_userid= :userid AND e.fk_itemid= :itemid")
	public Optional<ReviewEntity> isReviewExistForUser(@Param("userid") Integer userid,@Param("itemid") Integer itemid);
	
	@Query("from ReviewEntity e where e.fk_userid=:userid order by creationtime DESC")
	public List<ReviewEntity> getReviewsByUserId(@Param("userid") Integer userid);
	
	@Query("select AVG(rating) as rating from ReviewEntity e where e.fk_itemid=:itemid")
	public Double getAverageRatingByItemId(@Param("itemid") Integer itemid);
}
