package main;

import entity.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class DepartureTable {
    private static final SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Train.class)
                .buildSessionFactory();

    @SuppressWarnings("unchecked")
    private static <T> List<T> runQuery(String query, boolean isNative) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<T> result = isNative ? session.createNativeQuery(query).getResultList() : session.createQuery(query).getResultList();
        session.getTransaction().rollback();
        return result;
    }

    public static List<String> getDestinations() {
        return runQuery("SELECT DISTINCT destination FROM schedule", true);
    }

    public static List<Date> getDateByDestination(String destination) {
        return runQuery(String.format("SELECT departure FROM schedule WHERE destination = '%s'", destination), true);
    }

//    public static ArrayList<String> getDateByDestination(String destination) {
//        List<Date> dates = runQuery(String.format("SELECT departure FROM schedule WHERE destination = '%s'", destination), true);
//        ArrayList<String> datesOnly = new ArrayList<String>();
//        for (Date date : dates) {
//            datesOnly.add(date.toString().split(" ")[0]);
//        }
//        return datesOnly;
//    }

    public static ArrayList<String> getTimeByDateAndDestination(String date, String destination) {
        List<Date> dates = runQuery(String.format("SELECT departure FROM schedule WHERE destination = '%s' AND DATE(departure) = '%s'",
                destination, date), true);
        ArrayList<String> timesOnly = new ArrayList<>();
        for (Date dateObj : dates) {
            timesOnly.add(dateObj.toString().split(" ")[1]);
        }
        return timesOnly;
    }

    public static List<Train> getDepartureTable() {
        return runQuery("from Train", false);
    }
}
