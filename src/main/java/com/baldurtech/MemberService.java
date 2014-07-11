package com.baldurtech;

public class MemberService
{
    private final MemberDao memberDao;
    public MemberService(MemberDao memberDao)
    {
        this.memberDao = memberDao;
    }
    public Member save(Member member)
    {
        if(assertValidMemberById(member)) 
        {
            return member;
        }
        if(member.getUsername() != null && member.getUsername().trim().length() > 0)
        {
            Member memberWillBeSaved = new Member();
            memberWillBeSaved.setUsername(member.getUsername().trim());
            return memberDao.save(memberWillBeSaved);
        }
        return member;
    }
    public void delete(Member member)
    {
        if(memberDao.getById(member.getId()) != null && member.getId() > 1L)
        {   
            memberDao.delete(member);
        }
    }
    public Member update(Member member)
    {
        if(memberDao.getById(member.getId()) != null)
        {
            Member originMember = memberDao.getById(member.getId());
            originMember.setUsername(member.getUsername());
            return memberDao.update(originMember);
        }
        return member;
    }
    private boolean assertValidMemberById(Member member)
    {
        return member.getId() != null;
    }
    
} 