package com.example.member.service;

import com.example.member.model.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private List<Member> members = new ArrayList<>();

    public void registerMember(Member member) {
        members.add(member);
    }

    public List<Member> getAllMembers() {
        return members;
    }
}
