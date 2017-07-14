package br.edu.ufcg.lebflix.dao;

import br.edu.ufcg.lebflix.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by estacio on 13/07/17.
 */
@Repository
public class UsersDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public User getUserById(Long id) {
        entityManager.find(User.class, id);
        return null;
    }

    public void persistUser(User user) {
        entityManager.persist(user);
    }
}
