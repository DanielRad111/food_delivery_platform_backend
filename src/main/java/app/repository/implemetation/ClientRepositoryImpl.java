package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Client;
import app.repository.ClientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {
    @Override
    public Client save(Client entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnPersonSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnPersonSaved);
    }

    @Override
    public Client update(Client entity) {
        // TO DO
        // Same logic - extract it somehow
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
    public List<Client> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Native SQL - not preferred
//         Query query = session.createSQLQuery("select * from user");

        TypedQuery<Client> query = session.getNamedQuery("findAllClients");
        List<Client> clients = query.getResultList();

        transaction.commit();
        session.close();

        return clients;
    }

    @Override
    public Client findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // HQL - Hibernate Query Language, but use named query instead to reuse them
         Query query = session.createQuery("from Client where id=:id");
         query.setParameter("id", id);

//        TypedQuery<User> query = session.getNamedQuery("findPersonById");
//        query.setParameter("id", id);

        Client client;
        try {
            client = (Client) query.getSingleResult();
        } catch (NoResultException e) {
            client = null;
        }

        transaction.commit();
        session.close();

        return client;
    }


    @Override
    public boolean delete(Client entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getId();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }

    @Override
    public Client findByName(String name) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Used a Named Query - best solution
        TypedQuery<Client> query = session.getNamedQuery("findClientByName");
        query.setParameter("name", name);
        Client client;
        try {
            client = query.getSingleResult();
        } catch (NoResultException e) {
            client = null;
        }


        transaction.commit();
        session.close();

        return client;
    }

    @Override
    public Client findByNameAndPassword(String name, String password) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // TO DO
        // Same logic - extract it somehow
        TypedQuery<Client> query = session.getNamedQuery("findClientByNameAndPassword");
        query.setParameter("name", name);
        query.setParameter("password", password);

        Client client;
        try {
            client = query.getSingleResult();
        } catch (NoResultException e) {
            client = null;
        }

        transaction.commit();
        session.close();

        return client;
    }
}
