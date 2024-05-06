package org.example.webtoon.controller;

import java.util.Optional;

import org.example.webtoon.domain.Member;
import org.example.webtoon.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/member/new")
	public String createForm() {
		return "member/createMemberForm";
	}

	@PostMapping("/member/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setId(form.getId());
		member.setPwd(form.getPwd());
		member.setName(form.getName());

		memberService.join(member);

		return "redirect:/";
	}

	@GetMapping("/member/login")
	public String loginForm(HttpSession session) {

		Optional<Member> member = Optional.ofNullable((Member)session.getAttribute("login"));

		if (member.isEmpty()) {
			return "member/login";
		}

		return "member/logout";

	}

	@PostMapping("/member/login")
	public String login(MemberForm form, HttpSession session, Model model) {

		Optional<Member> login = memberService.login(form.getId(), form.getPwd());

		if (login.isEmpty()) {
			return "redirect:/member/login";
		}

		Member loginMember = login.get();
		session.setAttribute("login", loginMember);
		model.addAttribute("loginMember", loginMember);

		return "redirect:/";
	}

	@GetMapping("/member/logout")
	public String logoutForm() {

		return "member/logout";
	}

}
