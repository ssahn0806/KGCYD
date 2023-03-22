package com.kgc.chatbot.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@Entity(name="clientinfo")
public class ClientInfo {
	
	@Id
	@Column(name="PLUS_FRIEND_USER_KEY")
	private String plusfriendUserKey;
	
	@Column(name="BOT_USER_KEY",nullable=false)
	private String botUserKey;
	
	@Column(name="APP_USER_ID",nullable=true)
	private String appUserId;
	
	@Column(name="IS_FRIEND",nullable=false)
	private boolean isFriend;
	
	@Column(name="NICKNAME",nullable=true)
	private String nickName;
	
	@Column(name="PHONE_NUMBER",nullable=true)
	private String phoneNumber;
	
	@Column(name="FIRST_JOIN",nullable=true)
	private String firstJoin;
	
	@Column(name="LAST_JOIN",nullable=true)
	private String lastJoin;
	
	@Builder
	public ClientInfo(String plusfriendUserKey,String botUserKey,String appUserId,boolean isFriend,String nickName,String phoneNumber,String firstJoin,String lastJoin) {
		this.plusfriendUserKey = plusfriendUserKey;
		this.botUserKey = botUserKey;
		this.appUserId = appUserId;
		this.isFriend = isFriend;
		this.nickName = nickName;
		this.phoneNumber = phoneNumber;
		this.firstJoin = firstJoin;
		this.lastJoin = lastJoin;
			
	}
}
