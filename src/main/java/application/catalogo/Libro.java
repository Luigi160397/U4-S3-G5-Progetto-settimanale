package application.catalogo;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NamedQuery(name = "findByAuthor", query = "SELECT l FROM Libro l WHERE l.autore = :autore")
@Getter
@Setter
@NoArgsConstructor
public class Libro extends Catalogo {

	private String autore;
	private String genere;

	public Libro(String titolo, int annoPubblicazione, int nPagine, String autore, String genere) {
		super(titolo, annoPubblicazione, nPagine);
		setAutore(autore);
		setGenere(genere);

	}

	@Override
	public String toString() {
		return "ISBN: " + getISBN() + ", Titolo: " + getTitolo() + ", Anno Pubblicazione " + getAnnoPublicazione()
				+ ", Numero pagine: " + getNPagine() + ", Autore: " + getAutore() + ", Genere: " + getGenere()
				+ System.lineSeparator();
	};

}
