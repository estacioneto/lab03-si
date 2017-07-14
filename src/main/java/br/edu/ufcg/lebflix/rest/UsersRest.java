package br.edu.ufcg.lebflix.rest;

import br.edu.ufcg.lebflix.entities.User;
import br.edu.ufcg.lebflix.managers.SecurityManager;
import br.edu.ufcg.lebflix.managers.UsersManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by estacio on 13/07/17.
 */
@RestController
@RequestMapping(value = "/api/users")
public class UsersRest {

    @Autowired
    private UsersManager usersManager;

    @Autowired
    private SecurityManager securityManager;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) {
        return usersManager.registerUser(user);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public User getUser(@RequestHeader String Authorization) {
        return usersManager.getLoggedUser(Authorization);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        return usersManager.login(user);
    }
}
