package org.example.webtoon.service;

import java.util.List;

import org.example.webtoon.domain.Review;
import org.example.webtoon.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Transactional
public class ReviewService {

	private final ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	public void save(Review review) {

		reviewRepository.save(review);
	}

	public List<Review> findByUserId(String id) {
		return reviewRepository.findByUserId(id);
	}

	public List<Review> findByWebtoonID(String webtoonId) {
		return reviewRepository.findByWebtoonID(webtoonId);
	}
}
