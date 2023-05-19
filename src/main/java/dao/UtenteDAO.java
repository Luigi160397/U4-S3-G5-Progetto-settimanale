package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import application.prestito.Utente;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtenteDAO {
	private final EntityManager em;

	public UtenteDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Utente e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Utente salvato!");

	}

	public Utente getByNumeroTessera(UUID id) {

		Utente found = em.find(Utente.class, id);
		return found;
	}

	public void delete(UUID id) {
		Utente found = em.find(Utente.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Utente con id " + id + " eliminato!");
		} else {
			log.info("Utente con id " + id + " non trovato!");
		}
	}
}
