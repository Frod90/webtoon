package org.example.webtoon.repository;

import java.util.List;

import org.example.webtoon.domain.Review;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

@Repository
public class ReviewRepository {

	private EntityManager em;

	public ReviewRepository(EntityManager em) {
		this.em = em;
	}

	public Review save(Review review) {
		em.persist(review);
		return review;
	}

	public List<Review> findByUserId(String userId) {

		return em.createQuery("select r from Review r where r.userID = :userId", Review.class)
			.setParameter("userId", userId)
			.getResultList();
	}

	public List<Review> findByWebtoonID(String webtoonID) {

		return em.createQuery("select r from Review r where r.webtoonID = :webtoonID", Review.class)
			.setParameter("webtoonID", webtoonID)
			.getResultList();

	}
}
