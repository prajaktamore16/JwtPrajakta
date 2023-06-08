//package com.tokenUdemy.Udmey.config;
//
//import com.tokenUdemy.Udmey.model.Customer;
//import com.tokenUdemy.Udmey.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class EazyBankUserDetails implements UserDetailsService {
//   @Autowired
//   private CustomerRepository customerRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//     String userName = null,password=null;
//        List<GrantedAuthority> authorities = null;
//        List<Customer> customers = customerRepository.findByEmail(username);
//        if(customers.size() == 0){
//            throw new UsernameNotFoundException("user details not found for the user :"+ userName);
//        }else{
//            userName = customers.get(0).getEmail();
//            password = customers.get(0).getPwd();
//            authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
//        }
//        return new User(userName,password,authorities);
//
//    }
//}
