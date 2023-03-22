package com.kgc.chatbot.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="clientlog")
public class ClientLog {
	
	@Id
	@Column(name="LOG_NO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long logNo;
	
	@Column(name="PLUS_FRIEND_USER_KEY",nullable=false)
	private String plusfriendUserKey;
	
	@Column(name="BLOCK_ID",nullable=false)
	private String blockId;
	
	@Column(name="BLOCK_NAME",nullable=false)
	private String blockName;
	
	@Column(name="CLIENT_EXTRA",nullable=false)
	private String clientExtra;
	
	@Column(name="EXECUTE_TIME",nullable=false)
	private String executeTime;
	
	@Builder
	public ClientLog(Long logNo, String plusfriendUserKey, String blockId, String blockName, String clientExtra, String executeTime) {
		this.logNo = logNo;
		this.plusfriendUserKey = plusfriendUserKey;
		this.blockId = blockId;
		this.blockName = blockName;
		this.clientExtra = clientExtra;
		this.executeTime = executeTime;
	}
	
	
}
