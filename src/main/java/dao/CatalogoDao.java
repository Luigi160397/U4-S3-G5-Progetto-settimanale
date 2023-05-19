package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import application.catalogo.Catalogo;
import application.catalogo.Libro;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatalogoDao {
	private final EntityManager em;

	public CatalogoDao(EntityManager em) {
		this.em = em;
	}

	public void save(Catalogo e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Catalogo salvato!");

	}

	public Catalogo getByISBN(UUID id) {

		Catalogo found = em.find(Catalogo.class, id);
		return found;
	}

	public void delete(UUID id) {
		Catalogo found = em.find(Catalogo.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Catalogo con id " + id + " eliminato!");
		} else {
			log.info("Catalogo con id " + id + " non trovato!");
		}
	}

	public List<Catalogo> getByYear(int year) {
		TypedQuery<Catalogo> query = em.createNamedQuery("findByYear", Catalogo.class);
		query.setParameter("anno", year);
		return query.getResultList();
	}

	public List<Libro> getByAuthor(String autore) {
		TypedQuery<Libro> query = em.createNamedQuery("findByAuthor", Libro.class);
		query.setParameter("autore", autore);
		return query.getResultList();
	}

	public List<Catalogo> getByTitle(String titolo) {
		TypedQuery<Catalogo> query = em.createNamedQuery("findByTitle", Catalogo.class);
		query.setParameter("titolo", titolo);
		return query.getResultList();
	}

}
