package com.baldurtech;

import com.baldurtech.unit.MiniatureSpiceTestCase;

public class MemberServiceTest extends MiniatureSpiceTestCase
{  

    MockMemberDao memberDao = new MockMemberDao();
    MemberService memberService = new MemberService(memberDao);
    
    public void test_username_is_empty_should_not_save()
    {
        memberService.save(createMemberWithUsername(""));
        
        assertFalse(memberDao.saveHasInvoked);
    }    
    public void test_username_is_blank_should_not_save()
    {
        memberService.save(createMemberWithUsername("        "));
        
        assertFalse(memberDao.saveHasInvoked);
    }
    public void test_valid_member_should_be_saved()
    {
        memberService.save(createMemberWithUsername("xiaobai"));
        
        assertTrue(memberDao.saveHasInvoked);
    }
    public void test_username_should_be_trimed()
    {
        memberService.save(createMemberWithUsername("  tom  "));
        
        assertEquals("tom",memberDao.savedMember.getUsername());
    }
    public Member createMemberWithUsername(String username)
    {
        Member member = new Member();
        member.setUsername(username);
        return member;
    }
}
class MockMemberDao implements MemberDao
{
    public Boolean saveHasInvoked = false;
    public Member savedMember = new Member();
    
    public Member save(Member member)
    {
        savedMember = member;
        saveHasInvoked = true;
        return member;
    }
}