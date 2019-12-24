package com.blog.dao;
import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class operation implements Operations
{
    public static Long updateId;
    @Override
    public List<Posts> fetchAllPosts()
    {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Posts.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query=session.createQuery("from Posts");
        List<Posts> posts = query.list();
        posts.forEach(post-> {
            if (post.getContent().length() >= 200) {
                post.setContent(post.getContent().substring(0, 200) + "....");
            }
        });
        System.out.println(query);
        session.close();
        return posts;
    }
    public static boolean insert(Posts post)
    {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Posts.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(post);
        session.getTransaction().commit();
        return true;
    }

   public Posts getPostById(Long id)
    {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Posts.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Posts post= session.get(Posts.class,id);
        return post;
    }
    public void update(Posts post)
    {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Posts.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query=session.createQuery("Update Posts set content=:content ,title=:title where id=:id");
        query.setParameter("content",post.getContent());
        query.setParameter("id",updateId);
        query.setParameter("title",post.getTitle());
        query.executeUpdate();
//        System.out.println(post.getTitle());
//        System.out.println("Id is:"+post.getId());
//            session.update(post);
        session.getTransaction().commit();
            session.close();
    }
    public void deletePostById(Long id)
    {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Posts.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Posts post= session.get(Posts.class,id);
        if(post!=null){
            session.delete(post);
            System.out.println("post is deleted");
        }
        transaction.commit();
    }
}
