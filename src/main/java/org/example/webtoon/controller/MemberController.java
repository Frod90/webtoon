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
		member.setId(form.getId());
		member.setPwd(form.getPwd());
		member.setName(form.getName());

		try {
			memberService.join(member);
		} catch (IllegalArgumentException ill) {

			PrintWriter writer = response.getWriter();
			writer.println("<script> alert(\"" + ill.getMessage() + "\") </script>");
		}

		return "redirect:/";
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

		Optional<Member> login = Optional.empty();

		try {
			login = memberService.login(form.getId(), form.getPwd());
		} catch (IllegalArgumentException ill) {

			PrintWriter writer = response.getWriter();
			writer.println("<script> alert(\"" + ill.getMessage() + "\") </script>");
		}


		if (login.isEmpty()) {

			PrintWriter writer = response.getWriter();
			writer.println("<script> alert(\"아이디와 비밀번호를 확인해주세요.\") </script>");

			return "/member/login";
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

	@GetMapping("/member/doLogout")
	public String logout(@RequestParam("logout") String logout, HttpSession session) {

		if (logout.equals("logout")) {
			session.invalidate();
		}
		return "redirect:/";
	}
}
