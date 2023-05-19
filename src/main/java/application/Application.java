package application;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import application.catalogo.Catalogo;
import application.catalogo.Libro;
import application.catalogo.Periodicita;
import application.catalogo.Rivista;
import application.prestito.Prestito;
import application.prestito.Utente;
import dao.CatalogoDao;
import dao.PrestitoDAO;
import dao.UtenteDAO;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class Application {

	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();

		CatalogoDao cd = new CatalogoDao(em);
		UtenteDAO ud = new UtenteDAO(em);
		PrestitoDAO pd = new PrestitoDAO(em);

		Catalogo libro1 = new Libro("Harry Potter", 2023, 50, "JK Rowling", "Fantasy");
		Catalogo libro2 = new Libro("Harry Potter 2", 2020, 150, "JK Rowling", "Fantasy");
		Catalogo libro3 = new Libro("Harry Potter 3", 2015, 156, "JK Rowling", "Fantasy");
		Catalogo rivista1 = new Rivista("Time", 2023, 20, Periodicita.SETTIMANALE);
		Catalogo rivista2 = new Rivista("Famiglia Cristiana", 2023, 20, Periodicita.MENSILE);
		Catalogo rivista3 = new Rivista("TV Sorrisi e Canzoni", 2015, 22, Periodicita.SEMESTRALE);

		Utente aldo = new Utente("Aldo", "Baglio", LocalDate.of(1983, 2, 10));
		Utente giacomo = new Utente("Giacomo", "Poretti", LocalDate.of(1965, 3, 13));
		Utente giovanni = new Utente("Giovanni", "Storti", LocalDate.of(1970, 10, 10));
		Utente marco = new Utente("Marco", "Aurelio", LocalDate.of(1945, 1, 10));

		Prestito prestito1 = new Prestito(aldo, libro1, LocalDate.of(2020, 10, 7), LocalDate.of(2021, 1, 10));
		Prestito prestito2 = new Prestito(giacomo, rivista2, LocalDate.of(2023, 5, 11), LocalDate.of(2023, 7, 16));
		Prestito prestito3 = new Prestito(giovanni, libro3, LocalDate.of(2023, 3, 14), LocalDate.of(2023, 6, 20));
		Prestito prestito4 = new Prestito(giovanni, libro3, LocalDate.of(2023, 3, 14));
		Prestito prestito5 = new Prestito(marco, rivista3, LocalDate.of(2023, 3, 16));

//		cd.save(libro1);
//		cd.save(libro2);
//		cd.save(libro3);
//		cd.save(rivista1);
//		cd.save(rivista2);
//		cd.save(rivista3);
//
//		ud.save(aldo);
//		ud.save(giacomo);
//		ud.save(giovanni);
//		ud.save(marco);
//
//		pd.save(prestito1);
//		pd.save(prestito2);
//		pd.save(prestito3);
//		pd.save(prestito4);
//		pd.save(prestito5);

//		cd.delete(UUID.fromString("b917939f-d99b-4184-a300-4a6dc9324908"));

		System.out.println();
		log.info("---------------------- Catalogo Trovato per id ----------------------");

		Catalogo trovatoPerISBN = cd.getByISBN(UUID.fromString("09078845-39bf-4553-b97d-a363775f7dbc"));

		if (trovatoPerISBN != null) {
			log.info(trovatoPerISBN.toString());
		} else {
			log.info("Nessun catalogo trovato per l'id inserito.");
		}

		List<Catalogo> trovatoPerAnno = cd.getByYear(2023);
		System.out.println();
		log.info("---------------------- Cataloghi Trovati per anno ----------------------");

		if (trovatoPerAnno.size() > 0) {
			trovatoPerAnno.stream().forEach(c -> log.info(c.toString()));
		} else {
			log.info("Nessun catalogo trovato per l'anno inserito.");
		}

		List<Libro> trovatoPerAutore = cd.getByAuthor("JK Rowling");
		System.out.println();
		log.info("---------------------- Cataloghi Trovati per autore ----------------------");

		if (trovatoPerAutore.size() > 0) {
			trovatoPerAutore.stream().forEach(c -> log.info(c.toString()));
		} else {
			log.info("Nessun catalogo trovato per l'autore inserito.");
		}

		List<Catalogo> trovatoPerTitolo = cd.getByTitle("Famiglia");
		System.out.println();
		log.info("---------------------- Cataloghi Trovati per titolo ----------------------");

		if (trovatoPerTitolo.size() > 0) {
			trovatoPerTitolo.stream().forEach(c -> log.info(c.toString()));
		} else {
			log.info("Nessun catalogo trovato per il titolo inserito.");
		}

		List<Prestito> trovatoPerNTessera = pd
				.getByNumeroDiTessera(UUID.fromString("8a3bad5c-d663-45c6-a18f-249f20ae3c0b"));
		System.out.println();
		log.info("---------------------- Prestiti attivi trovati per Numero di Tessera ----------------------");

		if (trovatoPerNTessera.size() > 0) {
			trovatoPerNTessera.stream().forEach(c -> log.info(c.toString()));
		} else {
			log.info("Nessun prestito attivo trovato per il Numero di Tessera inserito.");
		}

		List<Prestito> trovatoPrestitiScaduti = pd.getPrestitiScadutiENonRestituiti();
		System.out.println();
		log.info("---------------------- Prestiti scaduti e non restituiti trovati ----------------------");

		if (trovatoPrestitiScaduti.size() > 0) {
			trovatoPrestitiScaduti.stream().forEach(c -> log.info(c.toString()));
		} else {
			log.info("Nessun prestito scaduto o non restituito trovato.");
		}

		em.close();
		emf.close();

	}

}
