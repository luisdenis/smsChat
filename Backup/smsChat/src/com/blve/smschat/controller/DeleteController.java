package com.blve.smschat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

import com.blve.smschat.business.interfaces.AdminManagerInterface;
import com.blve.smschat.domain.OperationResult;
import com.blve.smschat.domain.Search;
import com.blve.smschat.util.MessageConstants;


/**
 * Controlador de las peticiones de eliminacion
 * @author Mary
 */
public class DeleteController extends AbstractController {

    private AdminManagerInterface manager;
    private OperationResult result;
    
    private String successView; 

    private static Logger logger = Logger.getLogger(DeleteController.class);
    
    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
		result = new OperationResult();
    	try{
    		String id = request.getParameter("id");/* Se obtiene el id del elemento a eliminar */
    		String operation = request.getParameter("operation");
    		if(operation.equals("1"))
    		result = manager.deleteNumberA(id);
    		else result = manager.deleteNumberB(id);
    		
    		
    		/* Se invoca al metodo del negocio que gestiona 
														la eliminacion */ 
		}catch(Exception e){
			result.setErrorCode(MessageConstants.GENERAL_ERROR);
			logger.error("", e);
    	}
		
		//session.setAttribute("msg", result);
		//request.setAttribute("search", new Search());
    	request.setAttribute("result",result.getResultCode());
		session.setAttribute("menu", request.getParameter("menu")); 
        
		return new ModelAndView(successView);
    }
 	 
	public void setManager(AdminManagerInterface manager) {
		this.manager = manager;
	}
	public AdminManagerInterface getManager() {
		return manager;
	}
	public String getSuccessView() {
		return successView;
	}
	public void setSuccessView(String successView) {
		this.successView = successView;
	}
}