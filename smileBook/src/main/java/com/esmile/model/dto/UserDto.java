package com.esmile.model.dto;


public class UserDto {
	
	private int    user_id;
	private String last_name;
	private String first_name;
	private String phone_number;
	private String mail;
	private int    sex;
	private String birthdate;
	private String birthplace;
	private String introduction;
	private String skill;
	private int    open_range;
	private String password;
	private String icon_image;
	private String header_image;
	private int    company_id;
	private String created_at;
	private String updated_at;
	
	// ユーザーID
	public int getId() { return user_id; }

	public void setId(int id) { this.user_id = id; }
	
	// 名前
	public String getLast_Name() { return last_name; }

	public void setLast_Name(String last_name) { this.last_name = last_name; }
	
	// 名字
	public String getFirst_Name() { return first_name; }

	public void setFirst_Name(String first_name) { this.first_name = first_name; }
	
	// 電話番号
	public String getPhone_Number() { return phone_number; }

	public void setPhone_Number(String phone_number) { this.phone_number = phone_number; }
	
	// メールアドレス
	public String getMail() { return mail; }

	public void setMail(String mail) { this.mail = mail; }
	
	// 性別
	public int getSex() { return sex; }

	public void setSex(int sex) { this.sex = sex; }
	
	// 生年月日
	public String getBirthdate() { return birthdate; }

	public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
	
	// 出生地
	public String getBirthplace() { return birthplace; }

	public void setBirthplace(String birthplace) { this.birthplace = birthplace; }
	
	// 自己紹介文
	public String getIntroduction() { return introduction; }

	public void setIntroduction(String introduction) { this.introduction = introduction; }
	
	// 保持スキル
	public String getSkill() { return skill; }

	public void setSkill(String skill) { this.skill = skill; }
	
	// 公開範囲
	public int getOpen_Range() { return open_range; }

	public void setOpen_Range(int open_range) { this.open_range = open_range; }
	
	// パスワード
	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }
	
	// アイコンイメージ
	public String getIcon_Image() { return icon_image; }

	public void setIcon_Image(String icon_image) { this.icon_image = icon_image; }
	
	// ヘッダーイメージ
	public String getHeader_Image() { return header_image; }

	public void setHeader_Image(String header_image) { this.header_image = header_image; }
	
	// 会社情報
	public int getCompany_Id() { return company_id; }

	public void setCompany_Id(int company_id) { this.company_id = company_id; }
	
	// 登録日時
	public String getCreated_at() { return created_at; }

	public void setCreated_at(String created_at) { this.created_at = created_at; }
	
	// 更新日時
	public String getUpdated_at() { return updated_at; }

	public void setUpdated_at(String updated_at) { this.updated_at = updated_at; }
}
