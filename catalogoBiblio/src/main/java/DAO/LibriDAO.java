package DAO;

import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import entities.Libri;
public class LibriDAO {
    private EntityManagerFactory entityManagerFactory;


    public LibriDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    public void create(Libri libro) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(libro);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void save(Libri libro) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(libro);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Libri getById(UUID uuid) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Libri ele = entityManager.find(Libri.class, uuid);
        entityManager.close();
        return ele;
    }

    public void delete(UUID uuid) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Libri libro = entityManager.find(Libri.class, uuid);
        if (libro != null) {
            entityManager.remove(libro);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void refresh(Libri libro) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        libro = entityManager.merge(libro);
        entityManager.refresh(libro);
        entityManager.close();
    }

    public List<Libri> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Libri> query = entityManager.createQuery("SELECT l FROM Libro l", Libri.class);
        List<Libri> libri = query.getResultList();
        entityManager.close();
        return libri;
    }
}
