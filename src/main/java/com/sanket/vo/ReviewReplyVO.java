package com.sanket.vo;

import java.time.LocalDateTime;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewReplyVO {
	
	
	private Integer reviewid;
	private Integer fk_userid;
	private Integer fk_itemid;
	private String fk_username;
	private Double rating;
	private String review;
	private LocalDateTime creationtime;
	private List<RepliesVO> comments;
}
