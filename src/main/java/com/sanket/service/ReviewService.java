package com.sanket.service;

import java.util.List;

import com.sanket.dto.ItemDTO;
import com.sanket.dto.LoginDTO;
import com.sanket.dto.ReplyDTO;
import com.sanket.dto.ReviewDTO;
import com.sanket.dto.SearchReplyDTO;
import com.sanket.dto.UserDTO;
import com.sanket.entity.ItemEntity;
import com.sanket.vo.ItemVO;
import com.sanket.vo.RepliesVO;
import com.sanket.vo.ReviewHistoryVO;
import com.sanket.vo.ReviewReplyVO;
import com.sanket.vo.UseridVO;

public interface ReviewService {

	List<ReviewReplyVO> getReviewsByItemId(Integer itemid);

	Integer addReview(ReviewDTO dto);

	boolean isReviewExistForUser(Integer userid, Integer itemid);

	ItemEntity addItem(ItemDTO dto);

	List<ItemVO> getAllItems();

	boolean addUser(UserDTO dto);

	Integer canUserLogin(LoginDTO dto);

	List<ReviewHistoryVO> getReviewsByUserId(Integer userid);

	UseridVO getUserIdByMail(String email);

	List<ItemVO> searchByItemName(String itemname);

	List<ItemVO> searchByItemCategory(String itemcategory);

	boolean addReply(ReplyDTO dto);

	List<RepliesVO> getRepliesByItemIdAndReviewId(SearchReplyDTO dto);

	Double getAverageRatingByItemId(Integer itemid);

	List<ItemVO> getAllUserItems(Integer userid);

	Integer getNumberOfReviewsByitemid(Integer itemid);

	Integer getNumberOfItemsByUserid(Integer userid);

}