package com.sanket.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewHistoryVO {
	private Integer reviewid;
	private Integer fk_userid;
	private Integer fk_itemid;
	private String fk_username;
	private Double rating;
	private String review;
	private LocalDateTime creationtime;
	private String itemname;
	private String itemcategory;
	private String itemdescription;
}
