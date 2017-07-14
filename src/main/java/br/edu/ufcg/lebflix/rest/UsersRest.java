package br.edu.ufcg.lebflix.rest;

import br.edu.ufcg.lebflix.entities.User;
import br.edu.ufcg.lebflix.managers.UsersManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by estacio on 13/07/17.
 */
@RestController
@RequestMapping(value = "/api/users")
public class UsersRest {

    @Autowired
    private UsersManager usersManager;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) {
        return usersManager.registerUser(user);
    }
}
