package com.baldurtech;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class HomeServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {        
        Member member = new Member();
        member.setUsername(request.getParameter("username"));
        
        MemberDao memberDao = new MemberDaoImpl();
        
        MemberService memberService = new MemberService(memberDao);
        memberService.save(member);
        
        response.getWriter().println("Member:" + member);
    }
}