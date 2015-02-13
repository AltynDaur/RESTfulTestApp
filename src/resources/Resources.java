package resources;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {
    @SuppressWarnings("unused")
    @PersistenceContext(unitName = "RestfulApp")
    @Produces
    private EntityManager entityManager;
}
