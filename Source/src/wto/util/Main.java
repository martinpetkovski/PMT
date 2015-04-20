
package wto.util;

import wto.repository.UserRepositoryImpl;

public class Main {
	
	public static void main(String[] args) {
		UserRepositoryImpl userRepository = new UserRepositoryImpl();
		System.out.println(userRepository.readByNameAndFetch("Martin").getUserRoles().size());
	}

}