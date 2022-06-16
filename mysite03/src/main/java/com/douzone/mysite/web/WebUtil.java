package com.douzone.mysite.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WebUtil {
	public static String encodeURL(String url, String encode) {
		String urlEncode = null;
		try {
			urlEncode = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return urlEncode;
	}
	
}