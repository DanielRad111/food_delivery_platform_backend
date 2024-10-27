package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Product;
import app.repository.ProductRepository;
import io.swagger.models.auth.In;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Product findByName(String name) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Product> query = session.getNamedQuery("findProductByName");
        query.setParameter("productName", name);

        Product product;
        try {
            product = (Product) query.getSingleResult();
        }catch (RuntimeException e){
            product = null;
        }
        transaction.commit();
        session.close();

        return product;
    }

    @Override
    public Product save(Product entity){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnProductSaved = (Integer)session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnProductSaved);
    }

    @Override
    public Product update(Product entity){
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
    public boolean delete(Product entity){
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
    public List<Product> findAll(){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Product> query = session.getNamedQuery("findAllProducts");

        List<Product> products = query.getResultList();

        transaction.commit();
        session.close();

        return products;
    }

    @Override
    public Product findById(Integer id){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Product> query = session.getNamedQuery("findProductById");
        query.setParameter("id", id);

        Product product;
        try{
            product = (Product) query.getSingleResult();
        }catch (RuntimeException e){
            product = null;
        }
        transaction.commit();
        session.close();

        return product;
    }
}
