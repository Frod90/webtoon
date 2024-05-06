package org.example.webtoon.service;

import java.util.Optional;

import org.example.webtoon.domain.Member;
import org.example.webtoon.repository.MemberRepository;

import jakarta.transaction.Transactional;

@Transactional
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/*
	 * 회원 가입
	 */

	public void join(Member member) {

		validateDuplicateMember(member);

		memberRepository.save(member);
	}

	private void validateDuplicateMember(Member member) {

		memberRepository.findById(member.getId())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 ID입니다.");
			});

		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 닉네임입니다.");
			});

	}

	public Optional<Member> login(String id, String pwd) {

		Optional<Member> member = memberRepository.findById(id);

		Member member1 = member.orElseThrow(() -> new IllegalArgumentException("ID가 존재하지 않습니다."));

		if (member1.getPwd().equals(pwd.trim())) {
			return Optional.of(member1);
		}

		return Optional.empty();
	}

}
