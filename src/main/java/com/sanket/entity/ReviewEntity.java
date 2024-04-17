package com.sanket.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reviewid;
	private Integer fk_userid;
	private Integer fk_itemid;
	private String fk_username;
	private Double rating;
	private String review;
	
	@CreationTimestamp
	private LocalDateTime creationtime;
}
