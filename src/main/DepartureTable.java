package main;

import entity.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

    public static List<Calendar> getDateByDestination(String destination) {
        List<Timestamp> dateTime = runQuery(String.format("SELECT departure FROM schedule WHERE destination = '%s'", destination), true);
        List<Calendar> dates = new ArrayList<>();
        for (Timestamp ts : dateTime) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(ts.getTime());
            dates.add(c);
        }
        return dates;
    }

    public static List<Calendar> getTimeByDateAndDestination(Calendar date, String destination) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<Timestamp> dateTime = runQuery(String.format("SELECT departure FROM schedule WHERE destination = '%s' AND DATE(departure) = '%s'",
                destination, formatter.format(date.getTime())), true);
        List<Calendar> times = new ArrayList<>();
        for (Timestamp ts : dateTime) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(ts.getTime());
            times.add(c);
        }
        return times;
    }

    public static double getDistance(String destination) {
        List<Double> distances = runQuery(String.format("SELECT distance FROM schedule WHERE destination = '%s'", destination), true);
        double distance = distances.get(0);
        return distance;
    }

    public static List<Train> getDepartureTable() {
        return runQuery("from Train", false);
    }
}
