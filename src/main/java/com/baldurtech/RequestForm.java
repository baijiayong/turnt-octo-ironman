package com.baldurtech;

import javax.servlet.http.HttpServletRequest;

public class RequestForm
{
    private final HttpServletRequest request;
    
    public RequestForm(HttpServletRequest request)
    {
        this.request = request;
    }
    public String getString(String param)
    {
        return request.getParameter(param);
    }
}