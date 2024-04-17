package com.sanket.dao;

import java.util.List;
import java.util.Optional;

import com.sanket.entity.ItemEntity;
import com.sanket.entity.ReplyEntity;
import com.sanket.entity.ReviewEntity;
import com.sanket.entity.UserEntity;

public interface ReviewDAO {

	List<ReviewEntity> getReviewsByItemId(Integer itemid);

	ReviewEntity addReview(ReviewEntity entity);

	Optional<ReviewEntity> isReviewExistForUser(Integer userid, Integer itemid);

	ItemEntity addItem(ItemEntity item);

	List<ItemEntity> getAllItems();

	UserEntity addUser(UserEntity user);

	Optional<UserEntity> canUserLogin(String email);

	List<ReviewEntity> getReviewsByUserId(Integer userid);

	UserEntity getUserIdByMail(String email);

	List<ItemEntity> searchByItemName(String itemname);

	List<ItemEntity> searchByItemCategory(String itemcategory);

	ReplyEntity addReply(ReplyEntity reply);

	List<ReplyEntity> getRepliesByItemIdAndReviewId(Integer itemid, Integer reviewid);

	Double getAverageRatingByItemId(Integer itemid);

	Integer getNumberOfReviews(Integer itemid);

	List<ItemEntity> getAllUserItem(Integer userid);

	Integer getNumberOfReviewsByUserid(Integer userid);

	ItemEntity getItemByItemid(Integer fk_itemid);

}