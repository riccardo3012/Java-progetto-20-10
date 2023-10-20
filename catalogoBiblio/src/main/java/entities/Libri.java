package entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Libri extends Biblioteca {

    private String autore;
    private String genere;

    public Libri(String isbn, String titolo, Integer annoPubblicazione, Integer numeroPagine, String autore,
                 String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;

    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public static String toStringFile(Libri libro) {
        return Libri.class.getSimpleName() + "@" + libro.isbn + "@" + libro.titolo + "@" + libro.annoPubblicazione + "@"
                + libro.numeroPagine + "@" + libro.autore + "@" + libro.genere;
    }

    public static Libri fromStringFile(String stringFile) {
        String[] split = stringFile.split("@");

        return new Libri(split[1], split[2], Integer.valueOf(split[3]), Integer.valueOf(split[4]), split[5], split[6]);
    }

}
