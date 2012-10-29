package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    public User getUserByName(String userName) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where name='" + userName + "'");
        User user = (User) query.list().get(0);
        return user;
    }
}
