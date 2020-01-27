package com.kdj.nShopping.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncodingUTF8 {
	
	public String encode(String str) throws UnsupportedEncodingException {
		
		return URLEncoder.encode(str,"UTF-8");
	}

}
