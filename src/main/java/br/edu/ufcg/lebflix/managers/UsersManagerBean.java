package br.edu.ufcg.lebflix.managers;

import br.edu.ufcg.lebflix.dao.UsersDAO;
import br.edu.ufcg.lebflix.entities.User;
import br.edu.ufcg.lebflix.exception.AccessDeniedException;
import br.edu.ufcg.lebflix.exception.UnsupportedOperationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static br.edu.ufcg.lebflix.exception.AccessDeniedMessage.INEXISTENT_USER;

/**
 * Created by estacio on 13/07/17.
 */
@Service
@Transactional
public class UsersManagerBean implements UsersManager {


    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private UsersDAO usersDAO;

    @Override
    public User getLoggedUser(String authorizationHeader) {
        User jwtUser = securityManager.getUserFromAuthorizationHeader(authorizationHeader);
        return usersDAO.getUserById(jwtUser.getId());
    }

    @Override
    public User registerUser(User user) {
        boolean existsUser = usersDAO.existsUser(user);
        if (existsUser) {
            throw new UnsupportedOperationException(
                    UnsupportedOperationMessage.EXISITING_USER.getMessage());
        }
        usersDAO.persistUser(user);
        return new User(user.getId(), user.getEmail(), user.getName());
    }

    @Override
    public String login(User user) {
        boolean existsUser = usersDAO.existsUser(user);
        if (existsUser) {
            User userDatabase = usersDAO.getUserByEmail(user.getEmail());
            return securityManager.generateToken(userDatabase);
        } else {
            throw new AccessDeniedException(INEXISTENT_USER);
        }
    }
}
