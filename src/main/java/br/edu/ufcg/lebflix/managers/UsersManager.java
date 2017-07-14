package br.edu.ufcg.lebflix.managers;

import br.edu.ufcg.lebflix.entities.User;

/**
 * Created by estacio on 13/07/17.
 */
public interface UsersManager {
    User getLoggedUser(String userToken);

    User registerUser(User user);

    String login(User user);
}
