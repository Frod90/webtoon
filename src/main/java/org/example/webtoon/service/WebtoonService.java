package org.example.webtoon.service;

import java.util.List;
import java.util.Optional;

import org.example.webtoon.domain.Webtoon;
import org.example.webtoon.repository.WebtoonRepository;

import jakarta.transaction.Transactional;

@Transactional
public class WebtoonService {

	private final WebtoonRepository webtoonRepository;

	public WebtoonService(WebtoonRepository webtoonRepository) {
		this.webtoonRepository = webtoonRepository;
	}

	public List<Webtoon> findAll() {
		return webtoonRepository.findAll();
	}

	public Optional<Webtoon> findByWebtoonID(String webtoonID) {
		return webtoonRepository.findByWebtoonID(webtoonID);
	}

	public List<Webtoon> findByTitle(String title) {
		return webtoonRepository.findByTitle(title);
	}

	public List<Webtoon> findByAuthor(String author) {
		return webtoonRepository.findByAuthor(author);
	}
}
