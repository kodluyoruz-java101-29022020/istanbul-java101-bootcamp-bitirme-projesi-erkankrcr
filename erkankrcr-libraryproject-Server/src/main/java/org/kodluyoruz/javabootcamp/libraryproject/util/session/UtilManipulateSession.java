package org.kodluyoruz.javabootcamp.libraryproject.util.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.PushBuilder;
import java.io.Serializable;

@Component
public class UtilManipulateSession<T> {

    public void create(SessionFactory factory, T classes){
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(classes);
        session.getTransaction().commit();
        session.close();
    }

    public void update(SessionFactory factory, T classes){
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(classes);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(SessionFactory factory, T classes){
        Session session = factory.openSession();
        session.persist(classes);
        session.remove(classes);
    }
}
