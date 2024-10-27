//package app.repository.implemetation;
//
//import app.model.Order;
//import app.model.OrderStatus;
//import app.model.Product;
//import app.repository.OrderRepository;
//import app.configuration.HibernateConfiguration;
//import app.model.Client;
////import org.aspectj.weaver.ast.Or;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import javax.persistence.*;
//import javax.transaction.Transactional;
//import java.math.BigDecimal;
//import java.util.*;
//import org.springframework.stereotype.Repository;
//
//@Repository
//@Transactional
//public class OrderRepositoryImpl implements OrderRepository {
//    @PersistenceContext
//    private static EntityManager entityManager;
//
//    public static void setEntityManager(EntityManager entityManager) {
//        OrderRepositoryImpl.entityManager = entityManager;
//    }
//    @Override
//    public void updateOrderStatus(Integer id, OrderStatus newStatus) {
//        Order order = entityManager.find(Order.class, id);
//        if(order != null){
//            order.setOrderStatus(newStatus);
//        }
//    }
//    @Override
//    public List<Order> getOrdersByClient(Client client) {
//        // Example of a custom query using JPQL
//        String jpql = "SELECT o FROM Order o WHERE o.client = :client";
//        Query query = entityManager.createQuery(jpql, Order.class);
//        query.setParameter("client", client);
//        return query.getResultList();
//    }
//    @Override
//    public List<Order> getOrdersByDateRange(Date startDate, Date endDate) {
//        String jpql = "SELECT o FROM Order o WHERE o.orderDateTime BETWEEN :startDate AND :endDate";
//        Query query = entityManager.createQuery(jpql, Order.class);
//        query.setParameter("startDate", startDate);
//        query.setParameter("endDate", endDate);
//        return query.getResultList();
//    }
//    @Override
//    public List<Order> getOrdersHistory(Client client) {
//        String jpql = "SELECT o FROM Order o WHERE o.client = :client AND o.orderStatus = 'DELIVERED'";
//        Query query = entityManager.createQuery(jpql, Order.class);
//        query.setParameter("client", client);
//        return query.getResultList();
//    }
//    @Override
//    public Order findById(Integer id) {
//        return entityManager.find(Order.class, id);
//    }
//    @Override
//    public Double getTotalPrice(Order order) {
//        List<Product> products = order.getProducts();
//
//        // If the list is empty, return 0
//        if (products == null || products.isEmpty()) {
//            return 0.0;
//        }
//
//        // Calculate the total price by iterating over products
//        double totalPrice = products.stream()
//                .mapToDouble(product -> product.getProductPrice() * order.getQuantity())
//                .sum();
//
//        return totalPrice;
//    }
//    @Override
//    public boolean delete(Order entity) {
//        entityManager.remove(entity);
//        return entityManager.find(Order.class, entity.getId()) == null;
//    }
//
//    @Override
//    public Order save(Order entity) {
//        entityManager.persist(entity);
//        return entity;
//    }
//    @Override
//    public Order update(Order entity) {
//        return entityManager.merge(entity);
//    }
//    @Override
//    public List<Order> findAll() {
//        String jpql = "SELECT o FROM Order o";
//        Query query = entityManager.createQuery(jpql, Order.class);
//        return query.getResultList();
//    }
//}

package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Order;
import app.model.OrderStatus;
import app.model.Product;
import app.repository.OrderRepository;
import app.model.Client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private static SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

    public static void setSessionFactory(SessionFactory sessionFactory) {
        OrderRepositoryImpl.sessionFactory = sessionFactory;
    }

    @Override
    public void updateOrderStatus(Integer id, OrderStatus newStatus) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Order order = session.get(Order.class, id);
            if (order != null) {
                order.setOrderStatus(newStatus);
            }
            session.merge(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Order> findOrdersByClient(Client client) {
        Session session = sessionFactory.openSession();

        try {
            String jpql = "SELECT o FROM Order o WHERE o.client = :client";
            Query query = session.createQuery(jpql, Order.class);
            query.setParameter("client", client);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Order> findOrdersByDateRange(Date startDate, Date endDate) {
        Session session = sessionFactory.openSession();

        try {
            String jpql = "SELECT o FROM Order o WHERE o.orderDateTime BETWEEN :startDate AND :endDate";
            Query query = session.createQuery(jpql, Order.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Order> getOrdersHistory(Client client) {
        Session session = sessionFactory.openSession();

        try {
            String jpql = "SELECT o FROM Order o WHERE o.client = :client AND o.orderStatus = 'DELIVERED'";
            Query query = session.createQuery(jpql, Order.class);
            query.setParameter("client", client);
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public Order findById(Integer id) {
        Session session = sessionFactory.openSession();

        try {
            return session.find(Order.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public Double getTotalPrice(Order order) {
        Session session = sessionFactory.openSession();

        try {
            List<Product> products = order.getProducts();

            if (products == null || products.isEmpty()) {
                return 0.0;
            }

            double totalPrice = products.stream()
                    .mapToDouble(product -> product.getProductPrice() * order.getQuantity())
                    .sum();

            return totalPrice;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Order entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Integer id = entity.getId();
            session.delete(entity);
            transaction.commit();
            return findById(id) == null;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Order save(Order entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Integer idOnPersonSaved = (Integer) session.save(entity);
            transaction.commit();
            return findById(idOnPersonSaved);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public Order update(Order entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Integer id = entity.getId();
            session.saveOrUpdate(entity);
            transaction.commit();
            return findById(id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Order> findAll() {
        Session session = sessionFactory.openSession();

        try {
            String jpql = "SELECT o FROM Order o";
            Query query = session.createQuery(jpql, Order.class);
            return query.getResultList();
        } finally {
            session.close();
        }
    }
}

