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
}