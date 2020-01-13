package com.scy.bean;

//一开始数据库没有设计好，stu类对应数据库的学生表，student类对应数据库的一张由学生-班级-专业连接起来的视图
public class Stu {
	private String sno;
	private String sname;
	private String spassword;
	private String ssex;
	private int sage;
	private int syear;
	private String saddress;
	private double scredit;
	private String clno;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSpassword() {
		return spassword;
	}
	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public int getSage() {
		return sage;
	}
	public void setSage(int sage) {
		this.sage = sage;
	}

	public int getSyear() {
		return syear;
	}
	public void setSyear(int syear) {
		this.syear = syear;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	public double getScredit() {
		return scredit;
	}
	public void setScredit(double scredit) {
		this.scredit = scredit;
	}
	public String getClno() {
		return clno;
	}
	public void setClno(String clno) {
		this.clno = clno;
	}


}
