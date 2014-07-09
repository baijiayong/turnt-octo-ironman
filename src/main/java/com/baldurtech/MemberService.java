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
} 