package br.edu.ufcg.lebflix.managers;

import br.edu.ufcg.lebflix.entities.User;

/**
 * Created by estacio on 13/07/17.
 */
public interface SecurityManager {

    String generateToken(User user);

    User getUserFromToken(String token);

    User getUserFromAuthorizationHeader(String authorization);
}
