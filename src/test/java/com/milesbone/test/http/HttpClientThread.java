package com.milesbone.test.http;

public class HttpClientThread implements Runnable{

	private static final String REQUEST_URL = "http://127.0.0.1:8080/COM.FOXCONN.API.SCORE/";
	
	private String mac;
	
	private String mvid;
	
	private String score;
	
	private String userid;
	
	private int site;
	
	private String channel;
	
	
	public HttpClientThread(String mac, String mvid, String score, String userid) {
		super();
		this.mac = mac;
		this.mvid = mvid;
		this.score = score;
		this.userid = userid;
	}


	
	
	public void run() {
		
	}


	public String getMac() {
		return mac;
	}


	public void setMac(String mac) {
		this.mac = mac;
	}


	public String getMvid() {
		return mvid;
	}


	public void setMvid(String mvid) {
		this.mvid = mvid;
	}


	public String getScore() {
		return score;
	}


	public void setScore(String score) {
		this.score = score;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}

}
