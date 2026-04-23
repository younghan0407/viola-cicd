package com.example.member.service;

import com.example.member.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberServiceTests {

    private MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberService();
    }

    @Test
    void testRegisterMember() {
        Member member = new Member();
        member.setId("1"); // String ID
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");

        memberService.registerMember(member);

        List<Member> members = memberService.getAllMembers();
        assertEquals(1, members.size());
        assertEquals(member, members.get(0));
    }

    @Test
    void testGetAllMembers() {
        Member member1 = new Member();
        member1.setId("1"); // String ID
        member1.setName("John Doe");
        member1.setEmail("john.doe@example.com");

        Member member2 = new Member();
        member2.setId("2"); // String ID
        member2.setName("Jane Doe");
        member2.setEmail("jane.doe@example.com");

        memberService.registerMember(member1);
        memberService.registerMember(member2);

        List<Member> members = memberService.getAllMembers();
        assertEquals(2, members.size());
        assertEquals(member1, members.get(0));
        assertEquals(member2, members.get(1));
    }
}
