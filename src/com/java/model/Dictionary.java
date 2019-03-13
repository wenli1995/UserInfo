package com.java.model;

public class Dictionary {
	private int itemid;
	private String itemname;
	private String itemcode;
	private String itemdesc;
	
	public Dictionary(String itemname, String itemcode) {
		this.itemname = itemname;
		this.itemcode = itemcode;
	}
	
	public Dictionary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getItemdesc() {
		return itemdesc;
	}
	public void setItemdesc(String itemdesc) {
		this.itemdesc = itemdesc;
	}
	
}
