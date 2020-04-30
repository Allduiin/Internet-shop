package internetshop.security;

import internetshop.exceptions.AuthenticationException;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.User;
import internetshop.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User userFromDb = userService.findByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect login or password"));
        if (userFromDb.getPassword().equals(password)) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect login or password");
    }
}
