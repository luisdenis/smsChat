package com.blve.smschat.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.blve.smschat.business.interfaces.ManagerInterface;
import com.blve.smschat.domain.OperationResult;
import com.blve.smschat.domain.Search;
import com.pranical.exceptions.LogicException;
import com.blve.persistence.ResultList;

public class SearchController extends SimpleFormController {
	
	private ManagerInterface manager;
	HttpSession session = null;
    private OperationResult result;
    
    private static final String VISTA_USER_SERVICE_AJAX = "serviceUserAjax";
	
    private static Logger logger = Logger.getLogger(SearchController.class);
    
	
	
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException {
		
		logger.info("Metodo onsubmit search controller");
		
		Search search = (Search)command; //Se obtienen los parametros de busqueda
		ModelAndView modelView = new ModelAndView(getSuccessView()); // se obtiene la viste desde la declaracion en el dispach-servlet
		
		System.out.println("Numero de Telefono Onsubmit: '"  + search.getMsisdn()+"'");
		
		session = request.getSession(); //Se obtiene la sesion
		result = new OperationResult(); //se incia el array de resultado
		
		
		//Se arreglan las variables
		if(search.getMsisdn() != null){
			search.setMsisdn(search.getMsisdn().trim()); 
			search.setEstado(search.getEstado().trim());
		}
		
		String flagAjjax = request.getParameter("flagAjax");
		String path = request.getParameter("path");
		
	
		ResultList results = null;
		try {
			results = manager.getResultList(search);
		} catch (LogicException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(request.getParameter("n") != null)
			session.setAttribute("menu", request.getParameter("n"));
		request.setAttribute("results", results);
		session.setAttribute("search",search);
    	modelView.addObject("search",search);
    	request.setAttribute("search", search);
		
    	
		if( flagAjjax != null && flagAjjax.equals("true")){
				if(path.equals("userService"))
				return new ModelAndView(VISTA_USER_SERVICE_AJAX);
				
		}
    	
		
		return modelView;
	}
	
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		
		logger.info("Metodo formBackingObject search controller");
		
		Search search = new Search();
		session = request.getSession(); //Se obtiene la sesion
		//int page = 1;
		
		/* Si el parametro page es null, se esta ingresando a la consulta a traves 
		 * del link del menu, por lo cual se procede a limpiar los parametros de 
		 * busqueda de la sesion. */
		if(request.getParameter("page")==null ){
    		if(session!=null && session.getAttribute("search")!=null) session.setAttribute("search", null);
    	}else if(session!=null && session.getAttribute("search")!=null){
			search = (Search)session.getAttribute("search");
		}
		
		if(request.getParameter("n") != null)
			session.setAttribute("menu",request.getParameter("n"));
		
		if(request.getParameter("page") != null) search.setPage(Integer.parseInt(request.getParameter("page")));
		else search.setPage(1);
		
		if(request.getParameter("msisdn") != null)
			search.setMsisdn(request.getParameter("msisdn").trim());
		
		
		if(request.getParameter("estado") != null)
			search.setEstado(request.getParameter("estado").trim());
		
		
		//si se entra por menu.. y no por la opcion de submit
		if(request.getParameter("SubmitButton") == null &&  request.getParameter("flagAjax") == null ){
			
			ResultList results = null;
			try {
				results = manager.getResultList(search);
			} catch (LogicException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("results", results);
			//System.out.println("Numero de Telefono: "  + request.getParameter("msisdn"));
			//search.setMsisdn(request.getParameter("msisdn"));
			//search.setNumRows(50);
	 		
	 		request.setAttribute("page", search.getPage());
			session.setAttribute("search", search);
		}
		
		return search;
		
	}
	
	public ManagerInterface getManager() {
		return manager;
	}

	public void setManager(ManagerInterface manager) {
		this.manager = manager;
	}
	
	
	
	
	
}
