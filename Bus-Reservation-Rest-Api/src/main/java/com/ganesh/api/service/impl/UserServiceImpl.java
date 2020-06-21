package com.ganesh.api.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ganesh.api.components.Messages;
import com.ganesh.api.dto.UserDto;
import com.ganesh.api.entity.Role;
import com.ganesh.api.entity.User;
import com.ganesh.api.exceptions.EmailNotConfirmedException;
import com.ganesh.api.exceptions.UserAlreadyExistsException;
import com.ganesh.api.repository.UserRepository;
import com.ganesh.api.service.RoleService;
import com.ganesh.api.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Messages messages;

    @Autowired
    private RoleService roleService;

    @Override
    public User addUser(UserDto userDto) {
        User foundUser = userRepository.findOneByEmail(userDto.getEmail());
        if (foundUser != null) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setActivated(false);
        user.setActivationHash(UUID.randomUUID().toString());

        List<Role> roles = new ArrayList<>();
        roles.add(roleService.createOrGetRoleUser());
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
       // return userRepository.findOne(id);
    	return userRepository.getOne(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
       // userRepository.delete(id);
    	userRepository.getOne(id);
    }

    @Override
    public void activateEmail(String activationHash) {
        User user = userRepository.findOneByActivationHash(activationHash);
        if (user == null) {
            throw new EmailNotConfirmedException();
        }

        user.setActivated(true);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findOneByEmail(username);
        if (foundUser == null) {
            throw new UsernameNotFoundException("USER_NOT_FOUND");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : foundUser.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(username, foundUser.getPassword(),
                true, true, true, foundUser.isActivated(),
                grantedAuthorities);
    }

    @Override
    public void sendConfirmationEmailToUser(User user, String baseUrl) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setTo(user.getEmail());
           // helper.setFrom("noreplyconfirmemail@poczta.pl");
            helper.setFrom("ganeshkr04@gmail.com.pl");
            helper.setSubject(messages.getMessage("confirm_email.subject"));
            // TODO: Zmienić adres na frontend i wysłać zapytanie http z frontu
            helper.setText(
                    "<a href=\"" + baseUrl + "/activate-account?h=" + user.getActivationHash() + "\">" +
                            messages.getMessage("confirm_email.link_message") +
                            "</a>",
                    true);
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Cannot send confirmation e-mail: " + e.getMessage()); // TODO: Dodać logowanie
        }
    }
}

