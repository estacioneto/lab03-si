package br.edu.ufcg.lebflix.dao;

import br.edu.ufcg.lebflix.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by estacio on 13/07/17.
 */
@Repository
public class UsersDAO {

    private static final Long EXISTS = 1L;

    private static final String QUERY_USER_EXISTS_BY_EMAIL = new StringBuilder()
            .append("SELECT COUNT(1) ")
            .append("FROM User u ")
            .append("WHERE u.email=:email ")
            .toString();

    private static final String QUERY_USER_BY_EMAIL = new StringBuilder()
            .append("SELECT new User(u.id, u.email, u.name) ")
            .append("FROM User u ")
            .append("WHERE u.email=:email ")
            .toString();

    // We want to hide the password, so we don't use the `entityManager.find()` method here.
    private static final String QUERY_USER_BY_ID = new StringBuilder()
            .append("SELECT new User(u.id, u.email, u.name) ")
            .append("FROM User u ")
            .append("WHERE u.id=:id ")
            .toString();

    @PersistenceContext
    private EntityManager entityManager;

    public User getUserById(Long id) {
        TypedQuery<User> getUserQuery = entityManager.createQuery(QUERY_USER_BY_ID, User.class);
        getUserQuery.setParameter("id", id);
        return getUserQuery.getSingleResult();
    }

    public void persistUser(User user) {
        entityManager.persist(user);
    }

    public boolean existsUser(User user) {
        TypedQuery<Long> getUserQuery = entityManager.createQuery(QUERY_USER_EXISTS_BY_EMAIL, Long.class);
        getUserQuery.setParameter("email", user.getEmail());
        return EXISTS.equals(getUserQuery.getSingleResult());
    }

    public User getUserByEmail(String email) {
        TypedQuery<User> getUserQuery = entityManager.createQuery(QUERY_USER_BY_EMAIL, User.class);
        getUserQuery.setParameter("email", email);
        return getUserQuery.getSingleResult();
    }
}
