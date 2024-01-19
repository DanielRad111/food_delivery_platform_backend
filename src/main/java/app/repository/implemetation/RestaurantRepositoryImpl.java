package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Restaurant;
import app.repository.RestaurantRepository;
import io.swagger.models.auth.In;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class RestaurantRepositoryImpl implements RestaurantRepository {
    @Override
    public Restaurant findByName(String name) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Restaurant> query = session.getNamedQuery("findRestaurantByName");
        query.setParameter("name", name);

        Restaurant restaurant;
        try{
            restaurant = (Restaurant) query.getSingleResult();
        }catch (NoResultException e){
            restaurant = null;
        }
        transaction.commit();
        session.close();

        return restaurant;
    }

    @Override
    public Restaurant save(Restaurant entity){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnRestaurantSaved = (Integer)session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnRestaurantSaved);
    }

    @Override
    public Restaurant findById(Integer id){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Restaurant> query = session.getNamedQuery("findRestaurantById");
        query.setParameter("id", id);

        Restaurant restaurant;
        try{
            restaurant = (Restaurant) query.getSingleResult();
        }catch (NoResultException e){
            restaurant = null;
        }
        transaction.commit();
        session.close();

        return restaurant;
    }

    @Override
    public Restaurant update(Restaurant entity){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getId();
        session.saveOrUpdate(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public List<Restaurant> findAll(){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Restaurant> query = session.getNamedQuery("findAllRestaurants");

        List<Restaurant> restaurants = query.getResultList();

        transaction.commit();
        session.close();

        return restaurants;
    }

    @Override
    public boolean delete(Restaurant entity){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getId();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }
}
