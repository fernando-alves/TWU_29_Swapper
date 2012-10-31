package com.thoughtworks.twu.persistence;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.thoughtworks.twu.domain.User;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public User getUserByName(String userName) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where name='" + userName + "'");
        User user = (User) query.list().get(0);
        return user;
    }

}
