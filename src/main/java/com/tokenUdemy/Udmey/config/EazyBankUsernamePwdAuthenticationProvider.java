package com.tokenUdemy.Udmey.config;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.tokenUdemy.Udmey.model.Authority;
import com.tokenUdemy.Udmey.model.Customer;
import com.tokenUdemy.Udmey.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class EazyBankUsernamePwdAuthenticationProvider implements AuthenticationProvider {
   @Autowired
   private CustomerRepository customerRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd =  authentication.getCredentials().toString();
        List<Customer> customers = customerRepository.findByEmail(username);
        if(customers.size() == 0)  throw new BadCredentialsException("No User registered with this details");

        if(passwordEncoder.matches(pwd,customers.get(0).getPwd())){
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(username,pwd,getGrantedAuthorities(customers.get(0).getAuthorities()));
            }else{
                throw new BadCredentialsException("Invalid Password");
            }
    }
    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Authority authority: authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
