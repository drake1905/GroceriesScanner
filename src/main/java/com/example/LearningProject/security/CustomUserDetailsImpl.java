package com.example.LearningProject.security;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.LearningProject.models.User;
import com.example.LearningProject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            throw new UsernameNotFoundException("User is not found");
        }
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.get().getRole().toString()));
        return mapCustomUserDetails(user.get(), authorities);
    }

    private CustomUserDetails mapCustomUserDetails(User user, List<SimpleGrantedAuthority> authorities){

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(user.getId());
        customUserDetails.setName(user.getName());
        customUserDetails.setUsername(user.getUsername());
        customUserDetails.setEmail(user.getEmail());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setAuthorities(authorities);
        return customUserDetails;

    }
}
