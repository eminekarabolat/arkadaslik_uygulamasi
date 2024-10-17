package com.eminekarabolat.arkadaslik_uygulamasi.constants;

public class RestApis {
	private static final String VERSION = "/v1";
	private static final String API ="/api";
	private static final String DEVELOPER ="/dev";
	private static final String TEST ="/test";
	private static final String PROD="/prod";
	
	private static final String ROOT = VERSION + DEVELOPER;
	
	
	public static  final String FOLLOW = ROOT+ "/follow";
	public static  final String MESSAGE = ROOT+ "/message";
	public static  final String USER = ROOT+ "/user";
	
	public static  final String  ADD = "/add";
	public static  final String  FINDALL = "/find-all";
	public static  final String  FINDBYID = "/find-by-id";
	public static  final String  ADDUSER = "/add-user";
	public static  final String  GETALLUSERS = "/get-all-users";
	public static  final String  GETALLUSERSF = "/get-all-users-f";
	public static  final String  LOGIN = "/login";
	public static  final String  CREATEUSER = "/create-user";
	public static  final String  USERNAME = "/username";
	public static  final String  USERID = "/userid";
	public static  final String  REGISTER = "/register";
	public static  final String  ADDFOLLOW = "/add-follow";
	
	
	
}