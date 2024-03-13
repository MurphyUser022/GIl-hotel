package Hotel.GIL.Services;


import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Hotel.GIL.exceptions.NotFoundSourceException;
import Hotel.GIL.models.Role;
import Hotel.GIL.models.UserEntity;
import Hotel.GIL.Repositories.UserEntityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findByUserName(username);
        if (user == null) throw new NotFoundSourceException("user not found");
        else return new User(user.getUserName(),user.getPassword(),mapToGrantedAuthorities(user.getRoles()));
    }


    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRoleName().name()))
                .collect(Collectors.toList());


    }
}
