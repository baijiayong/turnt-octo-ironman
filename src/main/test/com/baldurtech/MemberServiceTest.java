package com.baldurtech;

import com.baldurtech.unit.MiniatureSpiceTestCase;

public class MemberServiceTest extends MiniatureSpiceTestCase
{  
    public void test_username_is_empty_should_not_save()
    {
        MockMemberDao memberDao = new MockMemberDao();
    
        Member member = new Member();
        member.setUsername("");
   
        MemberService memberService = new MemberService(memberDao);
        memberService.save(member);
        
        assertFalse(memberDao.saveHasInvoked);
    }    
}
class MockMemberDao implements MemberDao
{
    public Boolean saveHasInvoked = false;
    public Member save(Member member)
    {
        saveHasInvoked = true;
        return member;
    }
}