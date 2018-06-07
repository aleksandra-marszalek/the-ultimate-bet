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

/**
 *  Service responsible for main logic related to {@link User}. Has standard crud methods and multiple methods
 *  to find the user using different arguments.
 *
 */
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

    /**
     * Method to save {@link User} argument into the DB - used for adding new user.
     * Before saving, methods uses setters to add to user role "user", wallet with balance = 0, empty favourite list.
     * @param user
     */
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

    /**
     * Method used to check if user with the email given is already in the DB.
     * @param user
     * @return boolean true if the user with this email is already in the DB.
     */
    @Override
    public boolean checkEmail(User user) {
        String email = user.getEmail();
        User userEx = findByEmail(email);
        return userEx != null;
    }

    /**
     * Method used to check if user with the username given is already in the DB.
     * @param user
     * @return boolean true if the user with this username is already in the DB.
     */
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
