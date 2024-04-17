package com.sanket.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
	
	
	private Integer fk_userid;
	private Integer fk_itemid;
	private String fk_username;
	private Double rating;
	private String review;
	
}
