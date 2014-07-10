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
    public void test_给一个已存在的member修改为另一个有效的username()
    {
        
        Member member = memberService.update(createMember(1990L,"Jack"));
        
        assertEquals("Jack",member.getUsername());
        assertTrue(memberDao.getByIdHasInvoked);
        
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
    public Member createMember(Long id, String username)
    {
        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        
        return member;
    }
}
class MockMemberDao implements MemberDao
{
    public Boolean saveHasInvoked = false;
    public Member savedMember;
    public Boolean deleteHasInvoked = false;
    public Member originMember;
    public Boolean getByIdHasInvoked = false;
    
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
    public Member getById(Long id)
    {
        getByIdHasInvoked = true;
        Member member = new Member();
        member.setId(id);
        member.setUsername("Tom");
        return member;
    }
}