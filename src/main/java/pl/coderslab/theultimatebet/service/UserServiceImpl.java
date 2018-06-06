package pl.coderslab.theultimatebet.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.theultimatebet.entity.*;
import pl.coderslab.theultimatebet.repository.RoleRepository;
import pl.coderslab.theultimatebet.repository.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setUser(user);
        user.setWallet(wallet);
        Favourite favourite = new Favourite();
        List<Team> teams = new ArrayList<>();
        favourite.setTeams(teams);
        favourite.setUser(user);
        user.setFavourite(favourite);
        userRepository.save(user);
    }

    public void save (User user) {
        userRepository.save(user);
    }

    @Override
    public boolean checkEmail(User user) {
        String email = user.getEmail();
        User userEx = findByEmail(email);
        return userEx != null;
    }

    @Override
    public boolean checkUsername (User user) {
        String username = user.getUsername();
        User userEx = findByUserName(username);
        return userEx != null;
    }

    @Override
    public User findById (Long id) {
        return userRepository.findUserById(id);
    }



}
