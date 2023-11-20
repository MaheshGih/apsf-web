/**
 * 
 */
package com.mithra.apsf.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author koti
 *
 */
public class SMSClient {

	private static final String amp="&";
	
	/**
	 * Send sms using smsurl
	 * @param smsUrl
	 */
	public String sendSms(String smsUrl) {
		String res = null;
		try {
			URL url = new URL(smsUrl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			System.out.println(uc.getResponseMessage());
			res = uc.getResponseMessage();
			uc.disconnect();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}
	
	public String sendSms(String to, String msg) {
		try {
			String sendUrl = buildSmsUrl(to,msg);
			return sendSms(sendUrl);
			 
		}catch (Exception e) {
			return e.getMessage();
		}
	}
	
	private String buildSmsUrl(String to, String msg)throws Exception {
		
		String baseUrl = "http://sms.astinsoft.com/API/sms.php?username="
				+ URLEncoder.encode("iccvijayawadapromo", "UTF-8")
				+"&password="+ URLEncoder.encode("Icc@123", "UTF-8")+
				"&from="+ URLEncoder.encode("ICCVJA", "UTF-8")/*+
				"&to="+ URLEncoder.encode("9703111903", "UTF-8")
				+"&msg="+ URLEncoder.encode("APSF Registartion Id: AP35NMR8598", "UTF-8")
				+"&type=1&dnd_check=0"*/;
				
		
		//String baseUrl = "http://sms.astinsoft.com/API/sms.php?username=iccvijayawadapromo&password=Icc@123&from=ICCVJA";
		String typeUrl = "&type=1&dnd_check=0";
		
		StringBuilder urlBuilder = new StringBuilder(baseUrl);
		urlBuilder.append(amp);
		urlBuilder.append("to=");
		urlBuilder.append( URLEncoder.encode(to, "UTF-8") );
				
		urlBuilder.append(amp);
		urlBuilder.append("msg=");
		urlBuilder.append( URLEncoder.encode(msg, "UTF-8") );
		
		urlBuilder.append(typeUrl);
		
		//String smsurl = "http://sms.astinsoft.com/API/sms.php?username=iccvijayawadapromo&password=Icc@123&from=ICCVJA&to=9703111903&msg=Hello&type=1&dnd_check=0";
		return urlBuilder.toString();
	}
	
	
}
