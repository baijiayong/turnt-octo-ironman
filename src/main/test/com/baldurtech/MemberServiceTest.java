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
    public void test_id_equals_1_should_not_be_deleted()
    {
        memberService.delete(createMemberWithId(1L));
     
        assertFalse(memberDao.deleteHasInvoked);
    }
    public Member createMemberWithUsername(String username)
    {
        Member member = new Member();
        member.setUsername(username);
        return member;
    }
    public Member createMemberWithId(Long id)
    {
        Member member = new Member();
        member.setId(id);
        return member;
    }
}
class MockMemberDao implements MemberDao
{
    public Boolean saveHasInvoked = false;
    public Member savedMember;
    public Boolean deleteHasInvoked = false;
    
    public Member save(Member member)
    {
        savedMember = member;
        saveHasInvoked = true;
        return null;
    }
    public void delete(Member member)
    {
        deleteHasInvoked = true;
        
    }
}