package com.rakuten.r1401.action;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String aa = "0";
		
		String bb = "afdasdfa-0-0";
		
		String cc = "";
		
		System.out.println(aa.replace("－", "-"));
		System.out.println(bb.replace("-0-0", ""));
		
		Boolean  bResult= aa.matches("^[-\\+]?[.\\d]*$");
		
		bResult = bb.matches("^[-\\+]?[.\\d]*$");
		
		bResult = cc.matches("^[-\\+]?[.\\d]*$");

	}

}
