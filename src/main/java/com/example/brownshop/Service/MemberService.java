package com.example.brownshop.Service;

import com.example.brownshop.Entity.Member;
import com.example.brownshop.Repository.MemberRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepo memberRepo;

    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    public List<Member> getAllMember() {
        return memberRepo.findAll();
    }

    public Member createMember(Member member) {
        return memberRepo.save(member);
    }
}
