package org.example.webtoon.controller;

import java.util.List;

import org.example.webtoon.domain.Webtoon;
import org.example.webtoon.service.WebtoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebtoonController {

	private final WebtoonService webtoonService;

	@Autowired
	public WebtoonController(WebtoonService webtoonService) {
		this.webtoonService = webtoonService;
	}

	@GetMapping("/webtoon/search")
	public String search() {
		return "/webtoon/search";
	}

	@PostMapping("/webtoon/list")
	public String list(WebtoonForm form, Model model) {

		List<Webtoon> webtoons = findWebtoon(form);
		model.addAttribute("webtoons", webtoons);

		System.out.println(webtoons.get(0).getWebtoonID());

		return "/webtoon/list";
	}

	private List<Webtoon> findWebtoon(WebtoonForm form) {

		if (!form.getTitle().isBlank()) {
			return webtoonService.findByTitle(form.getTitle());
		}

		if (!form.getAuthor().isBlank()) {
			return webtoonService.findByAuthor(form.getAuthor());
		}

		return webtoonService.findAll();
	}

}
