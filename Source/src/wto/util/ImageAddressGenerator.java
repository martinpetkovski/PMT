package wto.util;

import java.util.Random;

public class ImageAddressGenerator {
	
	public final static String generate() {
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		
	    while (salt.length() < 8) {
	        int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	        salt.append(SALTCHARS.charAt(index));
	    }
	    
	    String saltStr = salt.toString();
	    return saltStr;
	}
}
