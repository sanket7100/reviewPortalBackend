package com.sanket.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVO {
	
	private Integer itemid;
	private Integer userid;
	private String itemname;
	private String itemcategory;
	private String itemdescription;
	private LocalDateTime creationtime;
	private Double averageRating;
	private String image;
	private Integer totalReviews;
}
