package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Driver;
import app.model.DriverStatus;
import app.repository.DriverRepository;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DriverRepositoryImpl implements DriverRepository {
    @Override
    public Driver save(Driver entity){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnPersonSaved = (Integer)session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnPersonSaved);
    }

    @Override
    public Driver update(Driver entity){

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
    public Driver findById(Integer id){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Driver> query = session.getNamedQuery("findDriverById");
        query.setParameter("id", id);

        Driver driver;
        try{
            driver = (Driver) query.getSingleResult();
        }catch (NoResultException e){
            driver = null;
        }

        transaction.commit();
        session.close();

        return driver;
    }

    @Override
    public List<Driver> findAll(){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Driver> query = session.getNamedQuery("findAllDrivers");
        List<Driver> drivers = query.getResultList();

        transaction.commit();
        session.close();

        return drivers;
    }

    @Override
    public List<Driver> findFreeDrivers(){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Driver> query = session.getNamedQuery("findFreeDriver");

        List<Driver> drivers = new ArrayList<>();
        try{
            drivers = query.getResultList();
        }catch (NoResultException e){
            drivers = null;
        }

        transaction.commit();
        session.close();

        return drivers;
    }

    @Override
    public void updateDriverStatus(Integer id, DriverStatus newStatus){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Driver driver = findById(id);
        driver.setDriverStatus(newStatus);
        session.saveOrUpdate(driver);

        transaction.commit();
        session.close();
    }

    @Override
    public Driver findByName(String name){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select d from Driver d where d.name = :name");
        query.setParameter("name", name);

        Driver driver;
        try{
            driver = (Driver) query.getSingleResult();
        }catch (NoResultException e){
            driver = null;
        }

        transaction.commit();
        session.close();

        return driver;
    }

    @Override
    public boolean delete(Driver entity){
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
