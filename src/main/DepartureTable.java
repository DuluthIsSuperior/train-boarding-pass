package main;

import entity.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

public abstract class DepartureTable {
    private static final SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Train.class)
                .buildSessionFactory();

    /**
     * Allows the session to be initialized so that hibernate dumps its garbage away from our print statements
     */
    public static void init() {
        factory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    private static <T> List<T> runQuery(String query, boolean isNative) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<T> result = isNative ? session.createNativeQuery(query).getResultList() : session.createQuery(query).getResultList();
        session.getTransaction().rollback();
        return result;
    }

    public static List<String> getOrigins() {
        return runQuery("SELECT DISTINCT origin FROM schedule", true);
    }

    public static List<String> getDestinations() {
        return runQuery("SELECT DISTINCT destination FROM schedule", true);
    }

    public static List<String> getDateByDestination(String destination) {
        List<String> q = new ArrayList<String>();
        for (Object o : runQuery(String.format("SELECT departure FROM schedule WHERE destination = '%s'", destination), true)) {
            q.add(((String) o).split(" ")[0]);
        }
        return q;
    }

    public static List<String> getTimeByDateAndDestination(String date, String destination) {
        List<String> q = new ArrayList<String>();
        for (Object o : runQuery(String.format("SELECT departure FROM schedule WHERE destination = '%s' AND SUBSTRING(departure, 1, 10) = '%s'", destination, date), true)) {
            q.add(((String) o).split(" ")[1].substring(0, 5));
        }
        return q;
    }

    public static Train getTrain(String departure, String destination) {
        return (Train) runQuery(String.format("from Train where departure = '%s:00' and destination = '%s'",
                departure, destination), false).get(0);
    }
}
