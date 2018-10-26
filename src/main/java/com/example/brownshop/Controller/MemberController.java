package com.example.brownshop.Controller;

import com.example.brownshop.Entity.Member;
import com.example.brownshop.Service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMember();
    }

    @PostMapping
    public ResponseEntity<Member> creteMember(@RequestBody Member member) {
        System.out.println(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(member));
//        return new ResponseEntity<Member>(HttpStatus.OK);
    }
}
