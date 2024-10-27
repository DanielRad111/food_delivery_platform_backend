package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Menu;
import app.model.Restaurant;
import app.repository.MenuRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class MenuRepositoryImpl implements MenuRepository {
//    @Override
//    public Menu findMenuByRestaurant(Restaurant restaurant) {
//        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        TypedQuery<Menu> query = session.getNamedQuery("findMenuByRestaurant");
//        query.setParameter("restaurant", restaurant);
//
//        Menu menu;
//        try{
//            menu = (Menu) query.getSingleResult();
//        }catch(NoResultException e){
//            menu = null;
//        }
//
//        transaction.commit();
//        session.close();
//
//        return menu;
//    }

    @Override
    public Menu save(Menu entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idOnMenuSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idOnMenuSaved);
    }

    @Override
    public Menu update(Menu entity) {
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
    public Menu findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Menu> query = session.createQuery("select m from Menu m where m.id = :id");
        query.setParameter("id", id);

        Menu menu;
        try{
            menu = (Menu) query.getSingleResult();
        }catch(NoResultException e){
            menu = null;
        }

        transaction.commit();
        session.close();

        return menu;
    }

    @Override
    public boolean delete(Menu entity) {
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
    public List<Menu> findAll(){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Menu> query = session.getNamedQuery("findAllMenus");
        List<Menu> menus = query.getResultList();

        transaction.commit();
        session.close();

        return menus;
    }
}
