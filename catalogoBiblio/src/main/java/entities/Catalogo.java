package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Catalogo {
    @Id
    @SequenceGenerator(name = "evento4_seq", sequenceName = "evento4_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evento4_seq")
    private Long id;
    private static final Logger logger = LoggerFactory.getLogger(Catalogo.class);

    @ElementCollection
    @CollectionTable(name = "catalogo_archivio", joinColumns = @JoinColumn(name = "catalogo_id"))
    @MapKeyColumn(name = "chiave")
    @Column(name = "valore")

    private Map<String, Biblioteca> archivio;

    public Catalogo() {
        this.archivio = new HashMap<String, Biblioteca>();
    }

    public void aggiungi(Biblioteca nuovoElemento) {
        archivio.put(nuovoElemento.getIsbn(), nuovoElemento);

        logger.info("Elemento aggiunto in archivio. ISBN: {} - Anno Pubblicazione: {}", nuovoElemento.getIsbn(),
                nuovoElemento.getAnnoPubblicazione());
    }

    public void rimuovi(String isbn) {
        Biblioteca elementoRimosso = archivio.remove(isbn);
        if (elementoRimosso != null)
            logger.info("Elemento rimosso dall'archivio. ISBN: {} - Anno Pubblicazione: {}", elementoRimosso.getIsbn(),
                    elementoRimosso.getAnnoPubblicazione());

    }

    public Biblioteca ricercaPerIsbn(String isbn) {

        return archivio.get(isbn);

    }

    public List<Biblioteca> ricercaPerAnnoPubblicazione(Integer annoPubblicazione) {

        return archivio.values().stream().filter(elem -> annoPubblicazione.equals(elem.getAnnoPubblicazione()))
                .collect(Collectors.toList());

    }

    public List<Libri> ricercaPerAutore(String autore) {

        return archivio.values().stream().filter(elem -> elem instanceof Libri).map(elem -> (Libri) elem) // cast
                .filter(elem -> autore.equals(elem.getAutore()))
                // filtra Autore
                .collect(Collectors.toList());

    }




}


