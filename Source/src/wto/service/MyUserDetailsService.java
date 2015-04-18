package wto.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
 
	private UserServiceImpl userService;
 
	@Transactional()
	@Override
	public UserDetails loadUserByUsername(final String username) 
		throws UsernameNotFoundException {
 
		wto.model.User user = userService.getUserByName(username);
		List<GrantedAuthority> authorities = 
                                      buildUserAuthority();
 
		return buildUserForAuthentication(user, authorities);
 
	}
 
	private User buildUserForAuthentication(wto.model.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), 
				true, true, true, true, authorities);
	}
 
	private List<GrantedAuthority> buildUserAuthority() {
 
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
 
		// Build user's authorities
		setAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
 
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
 
		return Result;
	}
 
}
