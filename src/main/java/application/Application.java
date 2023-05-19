package application;

import java.time.LocalDate;

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
import utils.JpaUtil;

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

		Utente aldo = new Utente("Aldo", "Baglio", LocalDate.of(1983, 2, 10));
		Utente giacomo = new Utente("Giacomo", "Poretti", LocalDate.of(1965, 3, 13));
		Utente giovanni = new Utente("Giovanni", "Storti", LocalDate.of(1970, 10, 10));

		Prestito prestito1 = new Prestito(aldo, libro1, LocalDate.of(2020, 10, 7), LocalDate.of(2021, 1, 10));
		Prestito prestito2 = new Prestito(giacomo, rivista2, LocalDate.of(2023, 5, 11), LocalDate.of(2023, 7, 16));
		Prestito prestito3 = new Prestito(giovanni, libro3, LocalDate.of(2023, 3, 14), LocalDate.of(2023, 6, 20));

//		cd.save(libro1);
//		cd.save(libro2);
//		cd.save(libro3);
//		cd.save(rivista1);
//		cd.save(rivista2);
//
//		ud.save(aldo);
//		ud.save(giacomo);
//		ud.save(giovanni);
//
//		pd.save(prestito1);
//		pd.save(prestito2);
//		pd.save(prestito3);

		em.close();
		emf.close();

	}

}
