package com.rakuten.util;

import jp.co.yahoo.yconnect.YConnectExplicit;
import jp.co.yahoo.yconnect.core.oauth2.TokenException;

public class GetTokenFromYahoo {

	//private final static String clientId = "dj0zaiZpPTd3T2dxczU3b25mMCZzPWNvbnN1bWVyc2VjcmV0Jng9NDQ-";
	//private final static String clientId = "dj0zaiZpPVJoTEFYYzkxYWV5aCZzPWNvbnN1bWVyc2VjcmV0Jng9NWY-";
	//private final static String clientSecret = "kirakiraichiba";
	//private final static String refreshToken = "AKb8F1.zGpoIvVVw2mbFUAmlFQI_d10_7AF8EI0ekxN09sWZT88dzoU-";
	//private final static String refreshToken = "APd5Gl.NTnH_fq1OvnLiu7le6jwNlliDM9UXjdYNFRCopw95WmVqP88-";
	private final static YConnectExplicit yconnect = new YConnectExplicit();
	
	private GetTokenFromYahoo() {
		
	}
	
	public static String getToken(String clientId,String clientSecret,String refreshToken) {
		String token = "";
		try {
			yconnect.refreshToken(refreshToken, clientId, clientSecret);
			token = yconnect.getAccessToken();
		} catch (TokenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
	
	public static long getTokenExpiration() {
		return yconnect.getAccessTokenExpiration();
	}
}
