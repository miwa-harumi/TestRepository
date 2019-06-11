package com.esmile.model.dto;


public class ChatDto {
	private int	 	send;
	private int 		receive;
	private String 	data;
	
	//送る側のID
	public int getChat_Send() { return send; }
	public void setChat_Send(int chat_one) { this.send = chat_one; }

	//受け取る側のID
	public int getChat_Get() { return receive; }
	public void setChat_Get(int chat_two) { this.receive = chat_two; }
	
	//Chatの文章(JSON形式)
	public String getChat_Data() { return data; }
	public void setChat_Data(String chat_data) { this.data = chat_data; }
}