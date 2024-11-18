package web.dao;


import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(int id) throws NullPointerException {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        } else {
            throw new NullPointerException("User doesn't exist");
        }
    }

    @Override
    public void updateUser(int id, User user) throws NullPointerException {
        User localUser = entityManager.find(User.class, id);
        if (localUser != null) {
            localUser.setName(user.getName());
            localUser.setAge(user.getAge());
            localUser.setEmail(user.getEmail());
            entityManager.merge(localUser);
        } else {
            throw new NullPointerException("User doesn't exist");
        }
    }

    @Override
    public User getUserByID(int id) {
        return entityManager.find(User.class, id);
    }
}
