package com.daniil.comicshop.service.security;

import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ClientRepository clientRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> client = clientRepository.findByLogin(username);
        if (client.isPresent()){
            UserDetailsImpl userDetails = new UserDetailsImpl();
            userDetails.setClient(client.get());
            return userDetails;
        }
        throw new UsernameNotFoundException(username);
    }
}
