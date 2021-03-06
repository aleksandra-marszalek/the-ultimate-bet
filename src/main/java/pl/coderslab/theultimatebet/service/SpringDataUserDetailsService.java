package pl.coderslab.theultimatebet.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.coderslab.theultimatebet.CurrentUser;
import pl.coderslab.theultimatebet.entity.Role;
import pl.coderslab.theultimatebet.entity.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Service implementing UserDetailsService, enabling using {@link org.springframework.security.core.annotation.AuthenticationPrincipal}
 */
public class SpringDataUserDetailsService implements UserDetailsService {
    private UserService userService;
    @Autowired
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new CurrentUser(user.getUsername(),user.getPassword(), grantedAuthorities, user);
    }
}
