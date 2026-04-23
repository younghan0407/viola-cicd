package com.example.member.controller;

import com.example.member.model.Member;
import com.example.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MemberControllerTests {

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    @Mock
    private Model model;

    private List<Member> mockMemberList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock data
        mockMemberList = new ArrayList<>();
        Member member1 = new Member();
        member1.setId("1"); // String ID
        member1.setName("John Doe");
        member1.setEmail("john.doe@example.com");
        mockMemberList.add(member1);

        Member member2 = new Member();
        member2.setId("2"); // String ID
        member2.setName("Jane Doe");
        member2.setEmail("jane.doe@example.com");
        mockMemberList.add(member2);
    }

    @Test
    void testViewMembers() {
        when(memberService.getAllMembers()).thenReturn(mockMemberList);

        String viewName = memberController.viewMembers(model);

        verify(memberService, times(1)).getAllMembers();
        verify(model, times(1)).addAttribute("members", mockMemberList);

        assertEquals("memberList", viewName);
    }

    @Test
    void testAddMemberSuccess() {
        String id = "3";
        String name = "Alice";
        String email = "alice@example.com";

        Member newMember = new Member();
        newMember.setId(id); // String ID
        newMember.setName(name);
        newMember.setEmail(email);

        String viewName = memberController.addMember(id, name, email, model);

        verify(memberService, times(1)).registerMember(refEq(newMember));
        assertEquals("redirect:/members", viewName);
    }
}
