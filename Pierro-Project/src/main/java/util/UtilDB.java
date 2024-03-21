/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.Student;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<Student> listStudents() {
      List<Student> resultList = new ArrayList<Student>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> students = session.createQuery("FROM Student").list();
         for (Iterator<?> iterator = students.iterator(); iterator.hasNext();) {
            Student student = (Student) iterator.next();
            resultList.add(student);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<Student> listStudents(String keyword) {
      List<Student> resultList = new ArrayList<Student>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> students = session.createQuery("FROM Student").list();
         for (Iterator<?> iterator = students.iterator(); iterator.hasNext();) {
            Student student = (Student) iterator.next();
            if (student.getFullName().startsWith(keyword)) {
               resultList.add(student);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static void createStudents(String fullName, String semester, String college, String email) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new Student(fullName, Integer.valueOf(semester), college, email));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
}
