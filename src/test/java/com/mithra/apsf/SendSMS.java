/**
 * 
 */
package com.mithra.apsf;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

/**
 * @author koti
 *
 */
public class SendSMS {

	public static void main(String[] args) {
		try {
			String recipient = "+441234567890";
			String message = " Greetings from Mr. Gupta! Have a nice day!";
			String username = "admin";
			String password = "abc123";
			String originator = "+440987654321";
			String requestUrl = "http://127.0.0.1:9501/api?action=sendmessage&" + "username="
					+ URLEncoder.encode(username, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8")
					+ "&recipient=" + URLEncoder.encode(recipient, "UTF-8") + "&messagetype=SMS:TEXT" + "&messagedata="
					+ URLEncoder.encode(message, "UTF-8") + "&originator=" + URLEncoder.encode(originator, "UTF-8")
					+ "&serviceprovider=GSMModem1" + "&responseformat=html";
			
			String smsurl = "http://sms.astinsoft.com/API/sms.php?username="
					+ URLEncoder.encode("iccvijayawadapromo", "UTF-8")
					+"&password="+ URLEncoder.encode("Icc@123", "UTF-8")+
					"&from="+ URLEncoder.encode("ICCVJA", "UTF-8")+
					"&to="+ URLEncoder.encode("9703111903", "UTF-8")
					+"&msg="+ URLEncoder.encode("APSF Registartion Id: AP35NMR8598", "UTF-8")
					+"&type=1&dnd_check=0";
			URL url = new URL(smsurl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			System.out.println(uc.getResponseMessage());
			uc.disconnect();
			/*Random rand = new Random();
			String id = String.format("%04d", rand.nextInt(10000));
			System.out.println(id);*/
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
