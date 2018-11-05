package com.example.brownshop.service;

import com.example.brownshop.entity.app.Member;
import com.example.brownshop.repository.app.MemberRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {
    private MemberService memberService;
    public Member member;

    @Mock
    MemberRepo memberRepo;

    @Before
    public void setup() {
        memberService = new MemberService(memberRepo);
        member = new Member().setFirstName("xxx").setLastName("yyy").setUsername("zzz").setPassword("1234");
    }

    @Test
    public void createMemberSuccessFully() {
        when(memberRepo.save(any(Member.class))).thenReturn(member);
        assertEquals(member, memberService.createMember(member));
    }

    @Test
    public void loadUserByUsernameSuccessFully() {
        when(memberRepo.findByUsername("zzz")).thenReturn(member);

        UserDetails user = memberService.loadUserByUsername("zzz");
        assertThat(user.getUsername()).isEqualTo("zzz");
        assertThat(user.getPassword()).isEqualTo("1234");
        verify(memberRepo, times(1)).findByUsername(any());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameIsNull() {
        when(memberRepo.findByUsername("zzz")).thenReturn(null);
        UserDetails user = memberService.loadUserByUsername("zzz");
    }
}