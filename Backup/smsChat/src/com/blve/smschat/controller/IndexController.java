package com.blve.smschat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

public class IndexController implements Controller {

	private String successView;
	/** Nombre de la vista a la cual se debe redireccionar en caso de exito */
    private static final String VISTA_PANEL_INICIO = "home";
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		//session.invalidate();
		
		if (session.getAttribute("user") != null )
			return new ModelAndView(new RedirectView(VISTA_PANEL_INICIO+".htm"));
		
		return new ModelAndView(successView);
	
	}
	public String getSuccessView() {
		return successView;
	}
	public void setSuccessView(String successView) {
		this.successView = successView;
	}

}