package com.esmile.model.dto;


public class FriendDto {
	private int	 	user;
	private int 		friend;
	private int		state;
	
	//送る側のID
	public int getFriend_User() { return user; }
	public void setFriend_User(int user_id) { this.user = user_id; }

	//受け取る側のID
	public int getFriend_Friend() { return friend; }
	public void setFriend_Friend(int friend_id) { this.friend = friend_id; }
	
	//Chatの文章(JSON形式)
	public int getFriend_Status() { return state; }
	public void setFriend_Status(int status) { this.state = status; }
}