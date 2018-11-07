package com.example.brownshop.controller;

import com.example.brownshop.entity.app.Member;
import com.example.brownshop.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.example.brownshop.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberController(MemberService memberService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberService = memberService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Member> signUp(@Valid @RequestBody Member member) {
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(member));
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(HttpServletRequest req) {
        String header = req.getHeader(HEADER_STRING);
        String[] token = header.split(" ");
        return ResponseEntity.status(HttpStatus.OK).body(memberService.deleteToken(token[1]));
    }
}
