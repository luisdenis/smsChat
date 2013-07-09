/*
 * Versión 0.1
 * Autor Pranical Technologies
 * Fecha de creación 04/06/2008
 * Copyright
 */
package com.blve.smschat.controller;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.blve.smschat.domain.OperationResult;
import com.blve.smschat.domain.Search;
import com.blve.smschat.domain.UserData;
import com.pranical.basicmodeldomain.domain.RoleComponent;
import com.pranical.basicmodeldomain.domain.RoleUser;
import com.pranical.exceptions.LogicException;

/**
 * Clase SelectorDeRolController
 * 
 * Controlador destinado a gestionar la seleccion del rol
 * con el cual el usuario ingresara en el sistema,esta asociado
 * a la pagina /WEB-INF/jsp/seleccionDeRol.jsp, deriva de la clase 
 * <code>org.springframework.web.servlet.mvc.AbstractController</code>
 * en vista de que la accion de submit no implica mayores elementos
 * dentro del formulario asociado.
 * 
 * @author BLVE CA
 */
public class AuthController extends SimpleFormController {

	OperationResult result;
	/** Nombre de la vista a la cual se debe redireccionar en caso de exito */
    private static final String VISTA_PANEL_INICIO = "home";
    /** Nombre de la pagina que permite la seleccion de roles */ 
    private static final String VISTA_CAMBIO_CLAVE = "changePassword";
    /** Nombre de la pagina que permite la seleccion de roles */ 
    private static final String VISTA_SELECCION_ROL = "roleSelector";
  
 /** Objeto de negocio que gestiona el manejo de autenticacion del usuario */
   // private AuthManager authManager;
    /** Objeto de negocio que gestiona el manejo de usuarios. */
    //private UserManager userManager;

    /**
     * Crea una nueva instancia de tipo <code>SelectorDeRolController</code>
     */
    public AuthController() {}

    private HttpSession session;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
															throws ServletException, IOException {
		
		
    	Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();//Se toma el usuario autenticado
    	session = request.getSession(); //Se obtiene la sesion
    	
    	String username="";
    	ModelAndView outputMav = null;
    	
		try {
			session.setAttribute("search", new Search());
			
			/* Se ingreso a la Seleccion del rol al momento de autenticar */
        	if (obj instanceof UserDetails) {
        		username = ((UserDetails)obj).getUsername();
        	} else {
        		username = obj.toString();
        	}
        	
        	UserData user= new UserData();
        	user.setUserName(username);
        	
        	session.setAttribute("user",user);
        	session.setAttribute("menu", "0");
			outputMav = new ModelAndView(new RedirectView(VISTA_PANEL_INICIO+".htm"));
			//outputMav.addObject("menu", "0");
		} catch(Exception e){
			logger.error("error",e);
			//request.setAttribute("msg", MessageManager.getMensaje(getApplicationContext(),request,"errors.load"));
    	}
		
		return outputMav;
	}
	
 	
}
