package br.edu.ufcg.lebflix.managers;

import br.edu.ufcg.lebflix.entities.User;

/**
 * Created by estacio on 13/07/17.
 */
public interface SecurityManager {

    boolean verifyToken(String token);

    String generateToken(User user);

    User getUserFromToken(String token);
}
