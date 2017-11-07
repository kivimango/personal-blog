package com.kivimango.blog.services;

/**
 * Anti-Brute Force Service for authentications.
 * 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public interface AntiBruteforceService {
	
	/**
	 * Increase the number of the stored attempts count, 
	 * and stores the new value with the associated IP address.
	 * 
	 * If the counter reaches the maximum login attempts limit,
	 * the actual IP address should be banned by the application 
	 * (i.e.: the login page should not be rendered for this IP address).
	 * 
	 * The application should restrict the IP address instead of the account, because
	 * this way the account can be DOS-able.
	 * @param ipAdress
	 */
	
	void loginFailed(String ipAdress);
	
	/**
	 * Invalidates the stored login attempts count with the associated IP address. 
	 * @param ipAdress
	 */
	
	void loginSucceeded(String ipAdress);
	
	/**
	 * Returns true if the login attempts count reached the maximum allowed.
	 * @param ipAdress
	 * @return
	 */
	
	boolean isBlocked(String ipAdress);
	
}
