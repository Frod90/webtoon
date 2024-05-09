package org.example.webtoon.controller;

import java.util.List;

import org.example.webtoon.domain.Member;
import org.example.webtoon.domain.Review;
import org.example.webtoon.domain.Webtoon;
import org.example.webtoon.service.ReviewService;
import org.example.webtoon.service.WebtoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewController {

	private ReviewService reviewService;

	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping("/review/inquiry")
	public String showInquiry() {
		return "/review/inquiry";
	}

	@PostMapping("/review/inquiry")
	public String inquiry(@RequestParam("webtoonID") String webtoonID, Model model) {

		List<Review> inquirys = reviewService.findByWebtoonID(webtoonID);

		for (Review review : inquirys) {
			System.out.println("inquiry - " + review.getID());
			System.out.println("inquiry - " + review.getTitle());
			System.out.println("inquiry - " + review.getContent());
			System.out.println("inquiry - " + review.getUserID());
			System.out.println("inquiry - " + review.getWebtoonID());
		}

		model.addAttribute("inquirys", inquirys);

		return "/review/inquiry";
	}

	@GetMapping("/review/enroll")
	public String showEnrollForm() {
		return "/review/enroll";
	}

	@PostMapping("/review/enroll")
	public String enroll(@RequestParam("webtoonID") String webtoonID, HttpSession session, Model model) {

		Member loginMember = (Member)session.getAttribute("login");

		if(loginMember == null || loginMember.getId().isBlank()) {
			return "/member/login";
		}

		System.out.println("login: " + loginMember.getId());
		System.out.println("webtoonID : " + webtoonID);

		Review enroll = new Review();
		enroll.setWebtoonID(webtoonID);
		enroll.setUserID(loginMember.getId());
		model.addAttribute("enroll", enroll);

		return "/review/enroll";
	}

	@PostMapping("/review/enroll/new")
	public String save(ReviewForm form) {

		Review review = new Review();
		review.setTitle(form.getTitle());
		review.setContent(form.getContent());
		review.setUserID(form.getUserID());
		review.setWebtoonID(form.getWebtoonID());

		reviewService.save(review);

		return "redirect:/webtoon/search";
	}
}
