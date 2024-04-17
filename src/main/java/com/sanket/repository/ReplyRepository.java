package com.sanket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sanket.entity.ReplyEntity;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Integer>{
	
	
	@Query("from ReplyEntity e where e.fk_itemid=?1 AND e.fk_reviewid=?2")
	public List<ReplyEntity> getRepliesByItemIdAndReviewId(@Param("itemid") Integer itemid, @Param("reviewid") Integer reviewid);
}
