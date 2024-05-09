package org.example.webtoon.repository;

import java.util.List;
import java.util.Optional;

import org.example.webtoon.domain.Webtoon;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

@Repository
public class WebtoonRepository {

	EntityManager em;

	public WebtoonRepository(EntityManager em) {
		this.em = em;
	}

	public Optional<Webtoon> findByWebtoonID(String webtoonID) {

		List<Webtoon> webtoons = em.createQuery("select w from Webtoon w where w.webtoonID = :webtoonID", Webtoon.class)
			.setParameter("webtoonID", webtoonID)
			.getResultList();
		return webtoons.stream().findAny();
	}

	public List<Webtoon> findByTitle(String title) {

		return em.createQuery("select w from Webtoon w where w.title like concat('%', :title, '%')", Webtoon.class)
			.setParameter("title", title)
			.getResultList();

	}

	public List<Webtoon> findByAuthor(String author) {
		return em.createQuery("select w from Webtoon w where w.author = :author", Webtoon.class)
			.setParameter("author", author)
			.getResultList();
	}

	public List<Webtoon> findAll() {
		return em.createQuery("select w from Webtoon w", Webtoon.class)
			.getResultList();
	}
}
