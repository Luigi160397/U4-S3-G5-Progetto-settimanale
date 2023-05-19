package application.catalogo;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import application.prestito.Prestito;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cataloghi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = "findByYear", query = "SELECT c FROM Catalogo c WHERE c.annoPublicazione = :anno")
@NamedQuery(name = "findByTitle", query = "SELECT c FROM Catalogo c WHERE c.titolo LIKE CONCAT('%', :titolo, '%')")

@Getter
@Setter
@NoArgsConstructor
public abstract class Catalogo {

	@Id
	@GeneratedValue
	private UUID ISBN;
	private String titolo;
	private int annoPublicazione;
	private int nPagine;
	@OneToMany(mappedBy = "elementoPrestato", cascade = CascadeType.ALL)
	private Set<Prestito> cataloghiPrestati;

	public Catalogo(String titolo, int annoPubblicazione, int nPagine) {

		setTitolo(titolo);
		setAnnoPublicazione(annoPubblicazione);
		setNPagine(nPagine);
	}

	@Override
	public abstract String toString();
}
