package DAO;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import entities.Prestito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Util;

public class PrestitoDAO {

    private static final Logger logger = LoggerFactory.getLogger(PrestitoDAO.class);

    ArrayList<Prestito> prestatoMap = new ArrayList<>();

    public void save( Prestito object ) {


        prestatoMap.add(object);


        EntityManager em = Util.getEntityManagerFactory().createEntityManager();
        try {

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.persist(object);

            transaction.commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();

            logger.error("Error saving object: " + object.getClass().getSimpleName(), ex);
            throw ex;

        } finally {
            em.close();
        }
    }

    public void searchByLibId(String numT) {

        prestatoMap.stream()
                // filtro numero tessera dentro l'utente, se combacia con l'id dell'ogg. del parametro
                .filter(ele -> ele.getUtente().getNumeroditessera() == numT)
                // se  valore e' null, prendo elementi che non sono stati consegnati
                .filter( ele -> ele.getDataRestituzioneEffettiva() == null)
                .forEach(ele -> System.out.println("@@@@@@@@@@@ Prestiti utente " +
                        ele.getUtente().getNome() + " " + ele.getUtente().getCognome()
                        + ": " + ele.getElementoPrestato()));
    }

    public void searchByLibPrestito() {

        prestatoMap.stream()
                // filtro numero tessera dentro l'utente, se combacia con l'id dell'ogg. del parametro
                .filter( ele -> ele.getDataRestituzioneEffettiva() != null)
                .filter( ele -> ele.getDataRestituzioneEffettiva().isAfter(ele.getDataRestituzionePrevista()) == true)
                .forEach(ele -> System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Prestito Scaduto" + ele.getElementoPrestato()));
    }

}