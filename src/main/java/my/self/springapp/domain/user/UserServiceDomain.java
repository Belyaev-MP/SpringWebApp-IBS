package my.self.springapp.domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import my.self.springapp.data.user.UserRepository;
import my.self.springapp.domain.model.Role;
import my.self.springapp.domain.model.User;
import my.self.springapp.web.form.user.UserForm;

@Service
public class UserServiceDomain implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepository userService;

    @Autowired
    @Lazy
    private PasswordEncoder passEncoder;

    @Override
    public List<User> getList() {
        List<User> users = new ArrayList<>();
        userService.findAll().forEach(users::add);

        return users;
    }

    @Override
    public boolean isUserWithEmailExist(String email) {
        return userService.countByEmail(email) != 0 ? true : false;
    }

    @Override
    public Optional<User> findByEmailAndEnabledTrue(String email) {
        return userService.findByEmailAndEnabledTrue(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userService.findByEmail(email);
    }

    @Override
    public void update(@Valid UserForm form) {
        User u = new User();
        BeanUtils.copyProperties(form, u, "password");
        u.setPassword(passEncoder.encode(form.getPassword()));
        u.setRoles(Role.USER.toString());

        userRepository.save(u);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).get();
    }

}