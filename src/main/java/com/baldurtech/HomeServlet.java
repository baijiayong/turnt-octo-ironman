package com.baldurtech;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import com.baldurtech.RequestForm;

public class HomeServlet extends HttpServlet
{
    private final MemberService memberService;
    
    public HomeServlet()
    {
        this(new MemberService(new MemberDaoImpl()));
    }
    public HomeServlet(MemberService memberService)
    {
        super();
        this.memberService = memberService;
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {        
        Map dataModel = doAction(new RequestForm(request));
        response.getWriter().println(dataModel);
    }
    public Map doAction(RequestForm form)
    {
        Member member = new Member();
        member.setUsername(form.getString("username"));
        
        member = memberService.save(member);
        
        Map dataModel = new HashMap();
        
        if(member.getId() == null)
        {
            dataModel.put("forward","edit");
            dataModel.put("member",member);
        }else
        {
            dataModel.put("redirectTo","list");
        }
        return dataModel;
    }
}