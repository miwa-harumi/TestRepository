package com.esmile.model.dto;


public class CompanyDto {
	private int	 	comp_id;
	private String 		comp_name;
	
	//送る側のID
	public int getCompany_Id() { return comp_id; }
	public void setCompany_Id(int company_id) { this.comp_id = company_id; }

	//受け取る側のID
	public String getCompany_Name() { return comp_name; }
	public void setCompany_Name(String name) { this.comp_name = name; }
	

}