package com.sanket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sanket.dto.ItemDTO;
import com.sanket.dto.LoginDTO;
import com.sanket.dto.ReplyDTO;
import com.sanket.dto.ReviewDTO;
import com.sanket.dto.SearchReplyDTO;
import com.sanket.dto.UserDTO;
import com.sanket.entity.ItemEntity;
import com.sanket.service.ReviewService;
import com.sanket.vo.ItemVO;
import com.sanket.vo.RepliesVO;
import com.sanket.vo.ReviewHistoryVO;
import com.sanket.vo.ReviewReplyVO;
import com.sanket.vo.UseridVO;


@RestController
@CrossOrigin
@RequestMapping(value = "portal")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "getrepliesbyitemidandreviewid")
	public List<RepliesVO> getRepliesByItemIdAndReviewId(@RequestBody SearchReplyDTO dto) {
		return reviewService.getRepliesByItemIdAndReviewId(dto);
	}
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "addreply")
	public boolean addReply(@RequestBody ReplyDTO dto) {
		return reviewService.addReply(dto);
	}
	
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "searchbyitemcategory/{itemcategory}")
	public List<ItemVO> searchByItemCategory(@PathVariable("itemcategory") String itemcategory) {
		return reviewService.searchByItemCategory(itemcategory);
	}
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "searchbyitemname/{itemname}")
	public List<ItemVO> searchByItemName(@PathVariable("itemname") String itemname) {
		return reviewService.searchByItemName(itemname);
	}
	
	@GetMapping(value = "getuserbyemail/{email}")
	public UseridVO getUserIdByMail(@PathVariable("email") String email) {
		return reviewService.getUserIdByMail(email);
	}
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "login")
	public Integer canUserLogin(@RequestBody LoginDTO dto) {
		return reviewService.canUserLogin(dto);
	}
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "adduser")
	public boolean addUser(@RequestBody UserDTO dto) {
		return reviewService.addUser(dto);
	}
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "getallitems")
	public List<ItemVO> getAllItems() {
		return reviewService.getAllItems();
	}
	
	@GetMapping(value = "getitemshistory/{userid}")
	public List<ItemVO> getAllUserItems(@PathVariable Integer userid) {
		return reviewService.getAllUserItems(userid);
	}
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "additem")
	public ItemEntity addItem(@RequestBody ItemDTO dto) {
		return reviewService.addItem(dto);
	}
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "addreview")
	public Integer addReview(@RequestBody ReviewDTO review) {
		return reviewService.addReview(review);
	}
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "getreview/{itemid}")//
	public List<ReviewReplyVO> getAllReviewsByItemId(@PathVariable("itemid") Integer itemid) {
		return reviewService.getReviewsByItemId(itemid);
	}
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "getreviewshistory/{userid}")
	public List<ReviewHistoryVO> getAllReviewsByUserId(@PathVariable("userid") Integer userid) {
		return reviewService.getReviewsByUserId(userid);
	}
	
	@GetMapping("getnumberofreviews/{userid}")
	public Integer getNumberOfReviewByUserid(@PathVariable("userid") Integer userid) {
		return reviewService.getNumberOfReviewsByitemid(userid);
	}
	
	@GetMapping("getnumberofitems/{userid}")
	public Integer getMethodName(@PathVariable("userid") Integer userid) {
		return reviewService.getNumberOfItemsByUserid(userid);
	}
	
	
}
