package com.scy.bean;

import java.io.Serializable;

public class StudentCourseReport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String scy_sno;
	private String scy_cno;
	private String scy_sname;
	private String scy_cname;
	private double scy_report;
	private String scy_cterm;
	private int scy_credit;
	public String getScy_sno() {
		return scy_sno;
	}
	public void setScy_sno(String scy_sno) {
		this.scy_sno = scy_sno;
	}
	public String getScy_cno() {
		return scy_cno;
	}
	public void setScy_cno(String scy_cno) {
		this.scy_cno = scy_cno;
	}
	public String getScy_sname() {
		return scy_sname;
	}
	public void setScy_sname(String scy_sname) {
		this.scy_sname = scy_sname;
	}
	public String getScy_cname() {
		return scy_cname;
	}
	public void setScy_cname(String scy_cname) {
		this.scy_cname = scy_cname;
	}

	public double getScy_report() {
		return scy_report;
	}
	public void setScy_report(double scy_report) {
		this.scy_report = scy_report;
	}
	public String getScy_cterm() {
		return scy_cterm;
	}
	public void setScy_cterm(String scy_cterm) {
		this.scy_cterm = scy_cterm;
	}

    public int getScy_credit() {
		return scy_credit;
	}
	public void setScy_credit(int scy_credit) {
		this.scy_credit = scy_credit;
	}
	@Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((scy_sno == null) ? 0 : scy_sno.hashCode());
        result = prime * result + ((scy_cno == null) ? 0 : scy_cno.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentCourseReport other = (StudentCourseReport) obj;
        if (scy_sno == null)
        {
            if (other.scy_sno!= null)
                return false;
        }
        else if (!scy_sno.equals(other.scy_sno))
            return false;
        if (scy_cno == null)
        {
            if (other.scy_cno != null)
                return false;
        }
        else if (!scy_cno.equals(other.scy_cno))
            return false;
        return true;
    }
}
