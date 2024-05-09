package org.example.webtoon;

import javax.sql.DataSource;

import org.example.webtoon.repository.MemberRepository;
import org.example.webtoon.repository.ReviewRepository;
import org.example.webtoon.repository.WebtoonRepository;
import org.example.webtoon.service.MemberService;
import org.example.webtoon.service.ReviewService;
import org.example.webtoon.service.WebtoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;

@Configuration
public class SpringConfig {

	private final EntityManager em;

	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemberRepository(em);
	}

	@Bean
	public WebtoonService webtoonService() {
		return new WebtoonService(webtoonRepository());
	}

	@Bean
	public WebtoonRepository webtoonRepository() {
		return new WebtoonRepository(em);
	}


	@Bean
	public ReviewService reviewService() {
		return new ReviewService(reviewRepository());
	}

	@Bean
	public ReviewRepository reviewRepository() {
		return new ReviewRepository(em);
	}
}
