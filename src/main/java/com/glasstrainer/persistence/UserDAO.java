package com.glasstrainer.persistence;

import com.glasstrainer.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Serhat CAN on 21.03.2015.
 */

@Repository
@Transactional
public class UserDAO {

    private final static String USER_BY_USERNAME = "SELECT a FROM User a WHERE a.username = :username";
    private final static String ALL_Users = "SELECT a FROM User a";

    @PersistenceContext
    EntityManager em;

    public User getById(Long id) {
        return em.find(User.class, id);
    }

    public User create(User User) {
        try {
            em.persist(User);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return User;
    }

    public List<User> getAll() {
        return (List<User>) em.createQuery(ALL_Users).getResultList();
    }

    public User getByUsername(String username) {
        Query query = em.createQuery(USER_BY_USERNAME);
        query.setParameter("username", username);
        User user = (User) query.getSingleResult();
        return user;
    }
}
