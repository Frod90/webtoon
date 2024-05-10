package org.example.webtoon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import org.example.webtoon.domain.Member;
import org.example.webtoon.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
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
	public String create(MemberForm form, HttpServletResponse response) throws IOException {
		Member member = new Member();
		// setter getter 사용하는 것에 DTO 객체는 예외로 둬도 되는가?
		member.setId(form.getId());
		member.setPwd(form.getPwd());
		member.setName(form.getName());

		try {
			memberService.join(member);
		} catch (IllegalArgumentException | IllegalStateException ill) {

			PrintWriter writer = response.getWriter();
			// 여기에 뷰로직이 있는게 맞는 것인가??
			writer.println("<script> alert(\"" + ill.getMessage() + "\"); </script>");
			return "member/createMemberForm";
		}

		return "/home";
	}

	@GetMapping("/member/loginProcessor")
	public String loginForm(HttpSession session) {

		Optional<Member> member = Optional.ofNullable((Member)session.getAttribute("login"));

		if (member.isEmpty()) {
			return "member/login";
		}

		return "member/logout";

	}

	@GetMapping("/member/login")
	public String login() {
		return "/member/login";
	}

	@PostMapping("/member/login")
	public String login(MemberForm form, HttpSession session, Model model, HttpServletResponse response) throws
		IOException {

		try {
			Optional<Member> login = memberService.login(form.getId(), form.getPwd());
			Member loginMember = login.orElseThrow(() -> new IllegalArgumentException("아이디와 비밀번호를 확인해주세요."));
			session.setAttribute("login", loginMember);
			model.addAttribute("loginMember", loginMember);

		} catch (IllegalArgumentException ill) {
			PrintWriter writer = response.getWriter();
			writer.println("<script> alert(\"" + ill.getMessage() + "\"); </script>");
			return "/member/login";
		}

		return "/home";
	}

	@GetMapping("/member/logout")
	public String logoutForm() {
		return "member/logout";
	}

	@GetMapping("/member/doLogout")
	public String logout(@RequestParam("logout") String logout, HttpSession session) {

		memberService.logout(logout, session);
		return "redirect:/";
	}
}
