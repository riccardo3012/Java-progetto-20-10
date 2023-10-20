package entities;

import DAO.LibriDAO;
import DAO.PrestitoDAO;
import DAO.RivistaDAO;
import DAO.UserDAO;
import util.Util;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) { EntityManagerFactory entityManagerFactory = Util.getEntityManagerFactory();
        Catalogo catalogo = new Catalogo();
        // LIBRI
      Libri l1 = new Libri("11111", "Come si sbriciola un biscotto", 1891, 1278, "pippo","romanzo");
        Libri l3 = new Libri("48947", "La Sacra BIBBIA", 20, 1278, "?","romanzo");
        Libri l4 = new Libri("12565", "Alien", 48,751, "brambilla","horror");
        Libri l5 = new Libri("65468", "Salvate il soldato rayan", 34, 319, "maria","romanzo");
      Libri l6= new Libri("5555", "gj rayan", 34, 319, "maria","romanzo");
      LibriDAO libroDAO = new LibriDAO(entityManagerFactory);
        libroDAO.save(l3);
        libroDAO.save(l1);
        libroDAO.save(l4);
        libroDAO.save(l5);


        // RIVISTE
        Rivista r1 = new Rivista("1112", "Chess", 2021, 178,Periodicita.MENSILE);
        Rivista r3 = new Rivista("4232", "CodeIt", 2016, 150, Periodicita.SEMESTRALE);
        Rivista r4 = new Rivista("2341", "Fastnow", 1968, 30, Periodicita.MENSILE);
        Rivista r5 = new Rivista("1407", "Focus", 2001, 103, Periodicita.SETTIMANALE);
        RivistaDAO rivistaDAO = new RivistaDAO();
        rivistaDAO.save(r1);
        rivistaDAO.save(r3);
        rivistaDAO.save(r4);
        rivistaDAO.save(r5);


        // UTENTE
        User utente = new User("aldo", "baglio", "25/12/0", "1f3sde21");
        User utente2 = new User("luca", "merto", "20/01/1385", "1sad6de2");
        User utente3 = new User("marco", "verdi", "21/07/09", "1asdde23");
        UserDAO userDAO = new UserDAO();
        userDAO.save(utente);
        userDAO.save(utente2);
        userDAO.save(utente3);
libroDAO.create(l6);

        // PRESTITO
        Prestito pres = new Prestito(utente, l3, LocalDate.now(), LocalDate.now(), LocalDate.now());
        Prestito pres2 = new Prestito(utente3, l4, LocalDate.now(), LocalDate.now(), null);
        Prestito pres3 = new Prestito(utente2, r1, LocalDate.now(), LocalDate.now(), null);
        Prestito pres4 = new Prestito(utente, r3, LocalDate.now(), LocalDate.now(), null);
        PrestitoDAO prestitoDAO = new PrestitoDAO();
        pres.setDataRestituzioneEffettiva(LocalDate.now().plusDays(150));
//		pres2.setDataRestituzioneEffettiva(LocalDate.now().plusDays(28));
//		pres3.setDataRestituzioneEffettiva(LocalDate.now().plusDays(48));
//		pres4.setDataRestituzioneEffettiva(LocalDate.now().plusDays(15));
        prestitoDAO.save(pres);
        prestitoDAO.save(pres2);
        prestitoDAO.save(pres4);
        prestitoDAO.save(pres3);
        prestitoDAO.searchByLibId("1f3sde21");
        prestitoDAO.searchByLibId("1sad6de2");
        prestitoDAO.searchByLibId("1asdde23");
        prestitoDAO.searchByLibPrestito();
//		prestitoDAO.save(pres2);
//		prestitoDAO.save(pres3);
//		prestitoDAO.save(pres4);


        //userDAO.checkPrestitibyUser("1f3sde21");



//		Libro l2 = saveLibro();
//		Rivista r2 = saveRivista();
//		User utenteUser = saveUtente();
//		Prestito presPrestito = savePrestito();
      catalogo.aggiungi(r1);
     catalogo.aggiungi(l1);





        // TEST DELETE

        // libroDAO.delete(l3);
        // userDAO.delete(utente2);
        // rivistaDAO.delete(r3);

//		try {
//			catalogo.salvaCatalogo();
//
//			catalogo.caricaCatalogo();
//
//			List<Libro> ricercaPerAutore = catalogo.ricercaPerAutore("pippo");
//
//			ricercaPerAutore.forEach(elem -> System.out.println("Titolo: " + elem.getTitolo()));
//
//		} catch (IOException e) {
//			logger.error("Errore durante la lettura/scrittura", e);
//		}

    }

}
