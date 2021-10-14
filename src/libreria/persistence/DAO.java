package libreria.persistence;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DAO {
    
    EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
    
}
