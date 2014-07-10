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
        if(member.getId() > 1L )
        {
            memberDao.delete(null);
        }
    }
    public Member update(Member member)
    {
        Member originMember = memberDao.getById(1990L);
        originMember.setUsername(member.getUsername());
        return originMember;
    }
} 