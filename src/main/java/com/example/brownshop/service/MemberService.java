package com.example.brownshop.service;

import com.example.brownshop.entity.app.Member;
import com.example.brownshop.repository.app.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class MemberService implements UserDetailsService {
    private final StringRedisTemplate template;
    private final MemberRepo memberRepo;

    public MemberService(StringRedisTemplate template, MemberRepo memberRepo) {
        this.template = template;
        this.memberRepo = memberRepo;
    }

    public Member createMember(Member member) {
        return memberRepo.save(member);
    }

    public void saveToken(String token) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        if (!this.template.hasKey(token)) {
            ops.set(token, "foo");
        }
    }

    public boolean checkTokenWhiteList(String header) {
        if (header != null) {
            String[] token = header.split(" ");
            ValueOperations<String, String> ops = this.template.opsForValue();
            return ops.get(token[1]) == null;
        }
        return false;
    }

    public Boolean deleteToken(String token) {
//        ValueOperations<String, String> ops = this.template.opsForValue();
        return this.template.delete(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member applicationUser = memberRepo.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}
