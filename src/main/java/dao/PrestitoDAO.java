package dao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import application.prestito.Prestito;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrestitoDAO {
	private final EntityManager em;

	public PrestitoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Prestito e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Prestito salvato!");

	}

	public Prestito getById(UUID id) {

		Prestito found = em.find(Prestito.class, id);
		return found;
	}

	public void delete(UUID id) {
		Prestito found = em.find(Prestito.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Prestito con id " + id + " eliminato!");
		} else {
			log.info("Prestito con id " + id + " non trovato!");
		}
	}

	public List<Prestito> getByNumeroDiTessera(UUID nTessera) {
		TypedQuery<Prestito> query = em.createNamedQuery("findByNumeroTesseraUtente", Prestito.class);
		query.setParameter("numeroTessera", nTessera);
		return query.getResultList();
	}

	public List<Prestito> getPrestitiScadutiENonRestituiti() {
		TypedQuery<Prestito> query = em.createNamedQuery("findPrestitiScadutiNonRestituiti", Prestito.class);
		query.setParameter("oggi", LocalDate.now());
		return query.getResultList();
	}

}
