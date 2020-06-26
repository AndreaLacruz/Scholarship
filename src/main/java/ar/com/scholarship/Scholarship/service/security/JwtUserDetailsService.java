package ar.com.scholarship.Scholarship.service.security;

import ar.com.scholarship.Scholarship.components.security.JwtAuthProvider;
import ar.com.scholarship.Scholarship.model.entity.security.User;
import ar.com.scholarship.Scholarship.model.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService{

    @Autowired @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired @Qualifier("jwtAuthProvider")
    private JwtAuthProvider jwtAuthProvider;

    @Override @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));
        return jwtAuthProvider.createJwtUserDetails(user);
    }
}
