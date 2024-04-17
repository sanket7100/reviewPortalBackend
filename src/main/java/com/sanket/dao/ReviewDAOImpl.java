package com.sanket.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanket.entity.ItemEntity;
import com.sanket.entity.ReplyEntity;
import com.sanket.entity.ReviewEntity;
import com.sanket.entity.UserEntity;
import com.sanket.repository.ItemRepository;
import com.sanket.repository.ReplyRepository;
import com.sanket.repository.ReviewRepository;
import com.sanket.repository.UserRepository;

@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	
	@Override
	public Double getAverageRatingByItemId(Integer itemid) {
		return reviewRepository.getAverageRatingByItemId(itemid);
	}
	
	@Override
	public List<ReplyEntity> getRepliesByItemIdAndReviewId(Integer itemid,Integer reviewid) {
		return replyRepository.getRepliesByItemIdAndReviewId(itemid, reviewid);
	}
	
	@Override
	public ReplyEntity addReply(ReplyEntity reply) {
		return replyRepository.save(reply);
	}
	
	@Override
	public List<ItemEntity> searchByItemCategory(String itemcategory) {
		return itemRepository.searchByItemCategory(itemcategory);
	}
	
	
	@Override
	public List<ItemEntity> searchByItemName(String itemname) {
		return itemRepository.searchByItemName(itemname);
	}
	
	@Override
	public UserEntity getUserIdByMail(String email){
		return userRepository.getUserIdByMail(email);
	}
	
	@Override
	public Optional<UserEntity> canUserLogin(String email) {
		return userRepository.canUserLogin(email);
	}
	
	@Override
	public UserEntity addUser(UserEntity user) {
		return userRepository.save(user);
	}
	
	@Override
	public List<ItemEntity> getAllItems() {
		return itemRepository.getAllItems();
	}
	
	@Override
	public ItemEntity addItem(ItemEntity item) {
		return itemRepository.save(item);
	}
	
	@Override
	public Optional<ReviewEntity> isReviewExistForUser(Integer userid,Integer itemid) {
		return reviewRepository.isReviewExistForUser(userid, itemid);
	}
	
	@Override
	public ReviewEntity addReview(ReviewEntity entity) {
		return reviewRepository.save(entity);
	}
	
	@Override
	public List<ReviewEntity> getReviewsByItemId(Integer itemid) {
		return reviewRepository.getReviewsByItemId(itemid);
	}
	
	@Override
	public List<ReviewEntity> getReviewsByUserId(Integer userid) {
		return reviewRepository.getReviewsByUserId(userid);
	}

	@Override
	public Integer getNumberOfReviews(Integer itemid) {
		return reviewRepository.getReviewsByItemId(itemid).size();
	}

	@Override
	public List<ItemEntity> getAllUserItem(Integer userid) {
		return itemRepository.findAllByFk_userid(userid);
	}

	@Override
	public Integer getNumberOfReviewsByUserid(Integer fk_userid) {
		return reviewRepository.getReviewsByUserId(fk_userid).size();
	}

	@Override
	public ItemEntity getItemByItemid(Integer fk_itemid) {
		return itemRepository.findById(fk_itemid).get();
		
	}
}
