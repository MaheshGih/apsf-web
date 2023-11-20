/**
 * 
 */
package com.mithra.apsf.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.mithra.apsf.common.Constants;
import com.mithra.apsf.common.Constants.EnumUserRole;

/**
 * @author koti
 *
 */
public class Util {
	
	public static List<EnumUserRole> userRoleList() {
		List<EnumUserRole> enumValues = Arrays.asList(EnumUserRole.values());
		return enumValues;
	}
	
	public static String randomNo() {
		Random rand = new Random();
		String id = String.format("%04d", rand.nextInt(10000));
		return id;
	}
	
	public static String sixDigitRandom() {
		Random rand = new Random();
		int no = 100000 + rand.nextInt(900000);
		String id = Integer.toString(no);
		return id;
	}
	
}
