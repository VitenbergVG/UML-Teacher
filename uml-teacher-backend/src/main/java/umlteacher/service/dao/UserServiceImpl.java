package umlteacher.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import umlteacher.exceptions.AuthorizationException;
import umlteacher.exceptions.UserNotFoundException;
import umlteacher.model.dao.Role;
import umlteacher.model.dao.User;
import umlteacher.repo.dao.RoleRepository;
import umlteacher.repo.dao.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User not found by name " + username);
        }
        return user;
    }

    public User getUserByUsernameAndPassword(String authToken)
            throws UsernameNotFoundException, AuthorizationException {
        String decodedToken = new String(Base64.getDecoder()
                .decode(authToken.replace("Basic ", "")));
        String[] credentials = decodedToken.split(":");
        String username = credentials[0];
        String password = credentials[1];
        User user = (User) loadUserByUsername(username);
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new AuthorizationException("Incorrect password!");
    }

    public User findUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (Objects.isNull(user))
        	throw new UserNotFoundException(userId);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (Objects.nonNull(userFromDB)) {
            return false;
        }
        Role role = new Role();
        role.setName("ROLE_USER");
        user.setRole(role);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public String getRoleForUser(Long userId) throws AuthorizationException {
        User user = userRepository.findById(userId).orElse(null);
        if (Objects.nonNull(user)) {
            return user.getRole().getAuthority();
        }
        throw new AuthorizationException("Couldn't get user role. User doesn't exists!");
    }

    public void changeRoleForUser(Long userId, String newRoleName) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Couldn't get user. User doesn't exists!"));
        if (Objects.nonNull(user)) {
            user.setRole(roleRepository.findByName(newRoleName));
            userRepository.saveAndFlush(user);
        }
    }

    public User saveUser(String authToken, String fullname) throws AuthorizationException {
        String decodedToken = new String(Base64.getDecoder()
                .decode(authToken.replace("Basic ", "")));
        String[] credentials = decodedToken.split(":");
        String username = credentials[0];
        String password = credentials[1];

        User userFromDB = userRepository.findByUsername(username);
        if (Objects.nonNull(userFromDB)) {
            throw new AuthorizationException("Couldn't create user. User already exists!");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullname(fullname);
        Role role = roleRepository.findByName("USER");
        user.setRole(role);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.saveAndFlush(user);
        return user;
    }
}
