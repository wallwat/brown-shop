package com.example.brownshop.repository.app;

import com.example.brownshop.entity.app.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
}
