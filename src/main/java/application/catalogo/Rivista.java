package application.catalogo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rivista extends Catalogo {

	@Column(name = "periodicità")
	@Enumerated(EnumType.STRING)
	private Periodicita periodicita;

	public Rivista(String titolo, int annoPubblicazione, int nPagine, Periodicita period) {
		super(titolo, annoPubblicazione, nPagine);
		setPeriodicita(period);

	}

	@Override
	public String toString() {
		return "ISBN: " + getISBN() + ", Titolo: " + getTitolo() + ", Anno Pubblicazione " + getAnnoPublicazione()
				+ ", Numero pagine: " + getNPagine() + ", Periodicità: " + getPeriodicita() + System.lineSeparator();
	};

}
