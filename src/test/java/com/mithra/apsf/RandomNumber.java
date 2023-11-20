package com.mithra.apsf;

import java.util.Random;

public class RandomNumber {

	public static void main(String[] args) {
		try {
			
			Random rand = new Random();
			String id = String.format("%04d", rand.nextInt(900000));
			String b = String.format("%03d", 18883);
			System.out.println(id);
			System.out.println(b);
			System.out.println(100000 + rand.nextInt(900000));
			
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
