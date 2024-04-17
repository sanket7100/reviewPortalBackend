package com.sanket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
	
	
	private Integer fk_userid;
	private String itemname;
	private String itemcategory;
	private String itemdescription;
}
