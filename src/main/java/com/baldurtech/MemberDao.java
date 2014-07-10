package com.baldurtech;

public interface MemberDao
{
    public Member save(Member member);
    public void delete(Member member);
    public Member getById(Long id);
}