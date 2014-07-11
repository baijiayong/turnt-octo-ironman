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
        
        assertEquals("xiaobai",memberDao.savedMember.getUsername());
    }
    public void test_一个已经设置id的Member不能被保存()
    {
        memberService.save(expectedMember(3L, "Tom"));
        
        assertFalse(memberDao.saveHasInvoked);
    }
    public void test_username_should_be_trimed()
    {
        memberService.save(createMemberWithUsername("  tom  "));
        
        assertEquals("tom",memberDao.savedMember.getUsername());
    }
    public void test_id_equals_1_should_not_be_deleted()
    {
        memberDao.假如数据库中存在Member(1L, "root");
        
        memberService.delete(createMemberWithId(1L));
     
        assertFalse(memberDao.deleteHasInvoked);
    }
    public void test_删除一个有效的且id不为1的member()
    {
        memberDao.假如数据库中存在Member(2L, "benben");
        
        memberService.delete(createMemberWithId(2L));
        
        assertEquals(2,memberDao.getByIdParam.intValue());
        assertEquals(2,memberDao.expectedDeleteMember.getId().intValue());
    }
    public void test_给一个已存在的member修改为另一个有效的username()
    {
        memberDao.假如数据库中存在Member(5L, "Tom");
        
        memberService.update(expectedMember(5L,"Jack"));
        
        assertEquals(5, memberDao.getByIdParam.intValue());
        assertEquals("Jack", memberDao.updateParam.getUsername());
    }
    public void test_一个不存在的member不可以被删除()
    {
        memberService.delete(expectedMember(3L,"yufei"));
        
        assertFalse(memberDao.deleteHasInvoked);
    }
    public void test_一个不存在的Member不可以被更新()
    {
        memberService.update(expectedMember(4L,"xiaobai"));
        
        assertFalse(memberDao.updateHasInvoked);
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
    public Member expectedMember(Long id, String username)
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
    
    public Long getByIdParam;
    public Long getByIdParamExpected;
    public Member getByIdReturn;
    
    public Member updateParam;
    public Boolean updateHasInvoked = false;
    
    public Member expectedDeleteMember;
    
    public Member save(Member member)
    {
        savedMember = member;
        saveHasInvoked = true;
        return null;
    }
    public void delete(Member member)
    {
        deleteHasInvoked = true;
        expectedDeleteMember = member;     
    }
    public Member getById(Long id)
    {
        getByIdParam = id;
        if(id.equals(getByIdParamExpected))
            return getByIdReturn;
        return null;
    }
    
    public Member update(Member member)
    {
        updateHasInvoked = true;
        updateParam = member;
        return updateParam;
    }
    
    public void 假如数据库中存在Member(Long id, String username)
    {
        getByIdParamExpected = id;
        
        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        
        getByIdReturn = member;
    }
}