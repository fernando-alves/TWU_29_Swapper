package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

@Repository
public class UserDao {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Transactional
    public void saveUser(User user) {
        entityManagerFactory.createEntityManager().persist(user);
    }

    @Transactional
    public User getUserByName(String userName) {
        javax.persistence.Query query = entityManagerFactory.createEntityManager().createQuery("from User where name='" + userName + "'");

        User user = (User)query.getResultList().get(0);
        return user;
    }
}
