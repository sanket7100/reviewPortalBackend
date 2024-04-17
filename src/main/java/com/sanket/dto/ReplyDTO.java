package com.sanket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
	
	
	private String fk_username;
	private String reply;
	private Integer fk_itemid;
	private Integer fk_reviewid;
}
