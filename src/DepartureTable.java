import entity.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public abstract class DepartureTable {
    private static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
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

    public static List<Train> getTrainsByDestination(String destination) {
        return runQuery(String.format("from Train where destination = '%s'", destination), false);
    }

    public static List<Train> getDepartureTable() {
        return runQuery("from Train", false);
    }
}
