package com.example.brownshop.Repository;

import com.example.brownshop.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
}
