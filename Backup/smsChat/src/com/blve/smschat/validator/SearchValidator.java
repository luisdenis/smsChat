package com.blve.smschat.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.blve.smschat.domain.Search;

public class SearchValidator implements Validator {
		
	@SuppressWarnings("unchecked")
	public boolean supports(Class c) {
		return c.equals(Search.class);
	}
	public void validate(Object command, Errors errors) {
		
	
		Search search = (Search) command;
		if(search.getMsisdn() != null)
		if (!isIntegerRegex(search.getMsisdn().trim()) && search.getMsisdn().trim().length() != 0  ){
			 errors.rejectValue("msisdn", "Numero Invalido", new String[]{search.getMsisdn()}, "");
		}
		
	}
	
	public static boolean isIntegerRegex(String str) {
        return str.matches("^[0-9]+$");
    }
	
}