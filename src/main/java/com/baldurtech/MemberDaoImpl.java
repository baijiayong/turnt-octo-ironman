package com.baldurtech;

public class MemberDaoImpl implements MemberDao
{
    public Member save(Member member)
    {
        System.out.println("MemberDao.save: " + member);
        member.setId(new java.util.Date().getTime());
        return member;
    }
    public void delete(Member member)
    {
        System.out.println("MemberDao.delete: " + member);
    }
    public Member getById(Long id)
    {
        return null;
    }
    
    public Member update(Member member)
    {
        System.out.println("MemberDao.update: " + member);
        return member;
    }
}