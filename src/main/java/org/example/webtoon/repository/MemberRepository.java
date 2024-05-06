package org.example.webtoon.repository;

import java.util.List;
import java.util.Optional;

import org.example.webtoon.domain.Member;

import jakarta.persistence.EntityManager;

public class MemberRepository {

	private final EntityManager em;

	public MemberRepository(EntityManager em) {
		this.em = em;
	}

	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	public Optional<Member> findById(String id) {
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
			.setParameter("name", name)
			.getResultList();

		return result.stream().findAny();
	}

}
