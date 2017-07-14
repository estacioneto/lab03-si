package br.edu.ufcg.lebflix.managers;

import br.edu.ufcg.lebflix.dao.UsersDAO;
import br.edu.ufcg.lebflix.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public User getLoggedUser(String userToken) {
        User jwtUser = securityManager.getUserFromToken(userToken);
        return usersDAO.getUserById(jwtUser.getId());
    }

    @Override
    public User registerUser(User user) {
        usersDAO.persistUser(user);
        return user;
    }
}
