package com.example.brownshop.Controller;

import com.example.brownshop.Entity.Member;
import com.example.brownshop.Service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberController(MemberService memberService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberService = memberService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

//    @GetMapping
//    public List<Member> getAllMembers() {
//        return memberService.getAllMember();
//    }

    @PostMapping("/sign-up")
    public ResponseEntity<Member> signUp(@Valid @RequestBody Member member) {
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(member));
        //return new ResponseEntity<Member>(HttpStatus.OK);
    }
}
