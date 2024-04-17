package com.sanket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanket.dao.ReviewDAO;
import com.sanket.dto.ItemDTO;
import com.sanket.dto.LoginDTO;
import com.sanket.dto.ReplyDTO;
import com.sanket.dto.ReviewDTO;
import com.sanket.dto.SearchReplyDTO;
import com.sanket.dto.UserDTO;
import com.sanket.entity.ItemEntity;
import com.sanket.entity.ReplyEntity;
import com.sanket.entity.ReviewEntity;
import com.sanket.entity.UserEntity;
import com.sanket.vo.ItemVO;
import com.sanket.vo.RepliesVO;
import com.sanket.vo.ReviewHistoryVO;
import com.sanket.vo.ReviewReplyVO;
import com.sanket.vo.UseridVO;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDAO reviewDAO;

	@Override
	public Double getAverageRatingByItemId(Integer itemid) {
		return reviewDAO.getAverageRatingByItemId(itemid);
	}

	@Override
	public List<RepliesVO> getRepliesByItemIdAndReviewId(SearchReplyDTO dto) {
		var list = reviewDAO.getRepliesByItemIdAndReviewId(dto.getItemid(), dto.getReviewid());
		List<RepliesVO> replies = new ArrayList<>();

		for (var x : list) {
			RepliesVO reply = new RepliesVO(x.getFk_username(), x.getReply());
			replies.add(reply);
		}
		return replies;
	}

	@Override
	public boolean addReply(ReplyDTO dto) {
		ReplyEntity reply = new ReplyEntity();
		reply.setFk_username(dto.getFk_username());
		reply.setReply(dto.getReply());
		reply.setFk_itemid(dto.getFk_itemid());
		reply.setFk_reviewid(dto.getFk_reviewid());
		var response = reviewDAO.addReply(reply);
		return response != null ? true : false;
	}

	@Override
	public List<ItemVO> searchByItemCategory(String itemcategory) { 
		var list = reviewDAO.searchByItemCategory(itemcategory);
		List<ItemVO> items = new ArrayList<>();

		for (var x : list) {
			Double avgRating = reviewDAO.getAverageRatingByItemId(x.getItemid());
			String image = findImage(x.getItemcategory());
			Integer totalReviews = reviewDAO.getNumberOfReviews(x.getItemid());
			ItemVO item = new ItemVO(x.getItemid(), x.getFk_userid(), x.getItemname(), x.getItemcategory(),
					x.getItemdescription(), x.getCreationtime(), avgRating, image,totalReviews);
			items.add(item);
		}

		return items;
		
	}

	@Override
	public List<ItemVO> searchByItemName(String itemname) { 
		var list = reviewDAO.searchByItemName(itemname);
		List<ItemVO> items = new ArrayList<>();

		for (var x : list) {
			Double avgRating = reviewDAO.getAverageRatingByItemId(x.getItemid());
			String image = findImage(x.getItemcategory());
			Integer count = reviewDAO.getNumberOfReviews(x.getItemid());
			ItemVO item = new ItemVO(x.getItemid(), x.getFk_userid(), x.getItemname(), x.getItemcategory(),
					x.getItemdescription(), x.getCreationtime(), avgRating, image,count);
			items.add(item);
		}

		return items;
	}

	@Override
	public UseridVO getUserIdByMail(String email) {
		var userEntity = reviewDAO.getUserIdByMail(email);
		System.out.println("inside service");
		UseridVO useridVO = new UseridVO(userEntity.getUserid(),userEntity.getUsername(),userEntity.getEmail());
		return useridVO;
	}

	@Override
	public Integer canUserLogin(LoginDTO dto) {

		String email = dto.getEmail();
		String password = dto.getPassword();
		var optional = reviewDAO.canUserLogin(dto.getEmail());

		if (optional.isPresent()) {
			if (email.equals(optional.get().getEmail()) && password.equals(optional.get().getPassword()))
				return 1;
			else
				return 2;
		}
		return 3;

	}

	@Override
	public boolean addUser(UserDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setUsername(dto.getUsername());
		var DBentity = reviewDAO.addUser(entity);
		return DBentity != null ? true : false;
	}

	@Override
	public List<ItemVO> getAllItems() {
		var list = reviewDAO.getAllItems();
		List<ItemVO> items = new ArrayList<>();

		for (var x : list) {
			Double avgRating = reviewDAO.getAverageRatingByItemId(x.getItemid());
			String image = findImage(x.getItemcategory());
			Integer count = reviewDAO.getNumberOfReviews(x.getItemid());
			ItemVO item = new ItemVO(x.getItemid(), x.getFk_userid(), x.getItemname(), x.getItemcategory(),
					x.getItemdescription(), x.getCreationtime(), avgRating, image,count);
			items.add(item);
		}

		return items;
	}

	@Override
	public List<ItemVO> getAllUserItems(Integer userid) {
		var list = reviewDAO.getAllUserItem(userid);
		List<ItemVO> items = new ArrayList<>();
		
		for(var x : list) {
			Double avgRating = reviewDAO.getAverageRatingByItemId(x.getItemid());
			String image = findImage(x.getItemcategory());
			Integer count = reviewDAO.getNumberOfReviews(x.getItemid());
			ItemVO item = new ItemVO(x.getItemid(), x.getFk_userid(), x.getItemname(), x.getItemcategory(),
					x.getItemdescription(), x.getCreationtime(), avgRating, image,count);
			items.add(item);
		}
		
		return items;
	}
	
	public String findImage(String category) {
		category = category.toLowerCase();

		switch (category) {
		case "book":
			return "assets/images/book.png";
			
		case "place":
			return "assets/images/place.png";
			
		case "movie":
			return "assets/images/movie.png";
			
		default:
			return "assets/images/other.png";
		}
	}

	@Override
	public ItemEntity addItem(ItemDTO dto) {

		ItemEntity item = new ItemEntity();
		item.setFk_userid(dto.getFk_userid());
		item.setItemname(dto.getItemname());
		item.setItemcategory(dto.getItemcategory());
		item.setItemdescription(dto.getItemdescription());

		return reviewDAO.addItem(item);
	}

	@Override
	public boolean isReviewExistForUser(Integer userid, Integer itemid) {
		var res = reviewDAO.isReviewExistForUser(userid, itemid);
		if (res.isPresent())
			return true;// review exist for user
		return false;// review not exist
	}

	@Override
	public Integer addReview(ReviewDTO dto) {

		boolean isReviewExistForUser = isReviewExistForUser(dto.getFk_userid(), dto.getFk_itemid());
		if (isReviewExistForUser)
			return 2;// exist

		ReviewEntity entity = new ReviewEntity();
		entity.setFk_userid(dto.getFk_userid());
		entity.setFk_itemid(dto.getFk_itemid());
		entity.setFk_username(dto.getFk_username());
		entity.setRating(dto.getRating());
		entity.setReview(dto.getReview());
		var res = reviewDAO.addReview(entity);
		return res != null ? 1 : 3;// 1->added//3-NotAdded
	}

	@Override
	public List<ReviewReplyVO> getReviewsByItemId(Integer itemid){
		var totalReviews=reviewDAO.getReviewsByItemId(itemid);
		
		List<ReviewReplyVO> reviewReply=new ArrayList<>();
		
		for(var x:totalReviews) {
			SearchReplyDTO dto=new SearchReplyDTO(x.getFk_itemid(),x.getReviewid());
			List<RepliesVO> replies=getRepliesByItemIdAndReviewId(dto);//getreplies by reply and itemid
			reviewReply.add(new ReviewReplyVO(x.getReviewid(),x.getFk_userid(),x.getFk_itemid(),x.getFk_username(),x.getRating(),x.getReview(),x.getCreationtime(),replies));
		}
		return reviewReply;
		
	}

	@Override
	public List<ReviewHistoryVO> getReviewsByUserId(Integer userid) {
		var list = reviewDAO.getReviewsByUserId(userid);
		
		List<ReviewHistoryVO> reviewhistory = new ArrayList<>();
		
		for(var x:list) {
			ItemEntity entity = reviewDAO.getItemByItemid(x.getFk_itemid());
			ReviewHistoryVO vo = new ReviewHistoryVO(x.getReviewid(), x.getFk_userid(), x.getFk_itemid(), x.getFk_username(), x.getRating(), x.getReview(), x.getCreationtime(), entity.getItemname(), entity.getItemcategory(), entity.getItemdescription());
			reviewhistory.add(vo);
		}
		
		return reviewhistory;
	}

	@Override
	public Integer getNumberOfReviewsByitemid(Integer userid) {
		return reviewDAO.getNumberOfReviewsByUserid(userid);
	}

	@Override
	public Integer getNumberOfItemsByUserid(Integer userid) {
		return reviewDAO.getAllUserItem(userid).size();
	}

}
