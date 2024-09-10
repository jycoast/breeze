package com.breeze.web.servlet;

import com.breeze.web.HandlerExecutionChain;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {

    HandlerExecutionChain getHandler(HttpServletRequest request);
}
