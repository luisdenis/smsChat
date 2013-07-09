package com.blve.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
 
@FacesValidator("IpValidator")
public class ApplicationHostIPValidator implements Validator 
{
 
    private static final String IP_ADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
 
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) 
    {
        if (!((String) value).matches(IP_ADDRESS_PATTERN)) 
        {
            throw new ValidatorException(new FacesMessage("Host Ip does not valid!"));
        }
    }
}
