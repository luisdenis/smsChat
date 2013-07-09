package com.blve.smschat.util;

public interface MessageConstants {

	String GENERAL_ERROR = "errors";
	String LOAD_ERROR    = "errors.load";
	String SEARCH_ERROR  = "errors.search";
	String AUTH_ERROR    = "errors.auth";
	String AUTH_LOGIN_PASS_INVALID  = "errors.auth.invalid";
	//Key
	String USER_INSERT_SUCCESS  = "success.insert.user";
	String USER_INSERT_ERROR    = "errors.insert.user";
	String USER_UPDATE_SUCCESS  = "success.update.user";
	String USER_UPDATE_ERROR    = "errors.update.user";
	String USER_DELETE_SUCCESS  = "success.delete.user";
	String USER_DELETE_ERROR    = "errors.delete.user";
	String LOGIN_DUPLICATE_ERROR = "errors.duplicate.login";
	
	//  *********** ADMINISTRACION DE SUPERVISORES ********************
	String SUPERVISOR_INSERT_SUCCESS  = "success.insert.supervisor";
	String SUPERVISOR_INSERT_ERROR    = "errors.insert.supervisor";
	String SUPERVISOR_DELETE_SUCCESS= "success.delete.supervisor";
	String SEARCH_ERROR_REPORT  = "errors.searchSalesman";
	
	String UPLOAD_SIZE_ERROR = "errors.upload.image";
	
	String DEVICE_INSERT_SUCCESS = "success.insert.device";
	String DEVICE_INSERT_ERROR   = "errors.insert.device";
	String DEVICE_UPDATE_SUCCESS = "success.update.device";
	String DEVICE_UPDATE_ERROR   = "errors.update.device";
	String DEVICE_DELETE_SUCCESS = "success.delete.device";
	String DEVICE_DELETE_ERROR   = "errors.delete.device";
	
	String DEVICE_DUPLICATE_ERROR = "errors.device.duplicate";
	
	String ROLE_INSERT_SUCCESS = "success.insert.role";
	String ROLE_INSERT_ERROR   = "errors.insert.role";
	String ROLE_UPDATE_SUCCESS = "success.update.role";
	String ROLE_UPDATE_ERROR   = "errors.update.role";
	String ROLE_DELETE_SUCCESS = "success.delete.role";
	String ROLE_DELETE_ERROR   = "errors.delete.role";
	
	String NOTIFICATION_INSERT_SUCCESS = "success.insert.notification";
	String NOTIFICATION_INSERT_ERROR   = "errors.insert.notification";
	String NOTIFICATION_UPDATE_SUCCESS  = "success.update.notification";
	String NOTIFICATION_UPDATE_ERROR   = "errors.update.notification";
	String NOTIFICATION_DELETE_SUCCESS = "success.delete.notification";
	String NOTIFICATION_DELETE_ERROR   = "errors.delete.notification";
	
	String PASSWORD_RESET_SUCCESS    = "success.reset.password";
	String PASSWORD_RESET_ERROR      = "errors.reset.password";
	String EMAIL_NOT_MATCH = "errors.notmatch.email";
	String USER_NOT_EXIST = "errors.notexist.login";
	
	String PASSWORD_CHANGE_SUCCESS    = "success.update.password";
	String PASSWORD_CHANGE_ERROR      = "errors.update.password";
	String PASSWORDS_NOT_EQUAL        = "errors.notequal.password";
	String CURRENT_PASSWORD_NOT_MATCH = "errors.notmatch.password";
	String PASSWORD_CHANGE__MAIL_ERROR = "errors.update.password.mail";
	

	String REQUIRED_ALL_FIELDS   = "errors.allfields.required";
	String REQUIRED_FIELD   = "errors.required";
	String CODE_EXIST       = "errors.exist.code";
	String MAX_LENGHT_ERROR = "errors.maxlength";
	String MIN_LENGHT_ERROR = "errors.minlength";
	String INVALID_FIELD    = "errors.invalid";
	String INVALID_EMAIL    = "errors.email";
	String INVALID_DATE     = "errors.date";
	String INVALID_RANGE    = "errors.range";
	String INVALID_DATE_RANGE="errors.range.date";

	String EMAIL_SENT_SUCCESS = "success.email.send";
	String EMAIL_SENT_ERROR = "errors.email.send";
	
	
	String INCIDENCE_TYPE_INSERT_ERROR="errors.insert.incidence_type";
	String INCIDENCE_TYPE_DELETE_ERROR="errors.delete.incidence_type";
	String INCIDENCE_TYPE_UPDATE_ERROR="errors.update.incidence_type";
	String INCIDENCE_TYPE_INSERT_NULL_ERROR="errors.insert.null.incidence_type";
	
	String INCIDENCE_MOTIVE_INSERT_ERROR="errors.insert.incidence_motive";
	String INCIDENCE_MOTIVE_DELETE_ERROR="errors.delete.incidence_motive";
	String INCIDENCE_MOTIVE_UPDATE_ERROR="errors.update.incidence_motive";
	String INCIDENCE_MOTIVE_INSERT_NULL_ERROR="errors.insert.null.incidence_motive";
	
	String RETURN_TYPE_INSERT_ERROR="errors.insert.return_type";
	String RETURN_TYPE_DELETE_ERROR="errors.delete.return_type";
	String RETURN_TYPE_UPDATE_ERROR="errors.update.return_type";
	String RETURN_TYPE_INSERT_NULL_ERROR="errors.insert.null.return_type";
	
	String RETURN_MOTIVE_INSERT_ERROR="errors.insert.return_motive";
	String RETURN_MOTIVE_DELETE_ERROR="errors.delete.return_motive";
	String RETURN_MOTIVE_UPDATE_ERROR="errors.update.return_motive";
	String RETURN_MOTIVE_INSERT_NULL_ERROR="errors.insert.null.return_motive";
	
	String INCIDENCE_TYPE_INSERT_SUCCESS="success.insert.incidence_type";
	String INCIDENCE_TYPE_UPDATE_SUCCESS="success.update.incidence_type";
	String INCIDENCE_TYPE_DELETE_SUCCESS="success.delete.incidence_type";
	
	String INCIDENCE_MOTIVE_INSERT_SUCCESS="success.insert.incidence_motive";
	String INCIDENCE_MOTIVE_UPDATE_SUCCESS="success.update.incidence_motive";
	String INCIDENCE_MOTIVE_DELETE_SUCCESS="success.delete.incidence_motive";
	
	String RETURN_MOTIVE_INSERT_SUCCESS="success.insert.return_motive";
	String RETURN_MOTIVE_UPDATE_SUCCESS="success.update.return_motive";
	String RETURN_MOTIVE_DELETE_SUCCESS="success.delete.return_motive";
	
	String RETURN_TYPE_INSERT_SUCCESS="success.insert.return_type";
	String RETURN_TYPE_UPDATE_SUCCESS="success.update.return_type";
	String RETURN_TYPE_DELETE_SUCCESS="success.delete.return_type";
	
	String VISIT_ITINERARY_INSERT_ERROR="errors.insert.itinerary";
	String VISIT_ITINERARY_EXIST_ERROR="errors.exist.itinerary";
	String VISIT_ITINERARY_INSERT_SUCCESS="success.insert.visit.itinerary";
	String VISIT_ITINERARY_ORDER_NOT_VALID="errors.order.not.valid";
	String VISIT_NO_VISIT_SAVED="errors.no.visit.saved";
	String VISIT_INSERT_SUCCESS="success.insert.visit";
	String VISIT_INSERT_ERROR="errors.insert.visit";
	String VISIT_DELETE_SUCCESS="success.delete.visit";
	String VISIT_DELETE_ERROR="errors.delete.visit";
	String VISIT_EXIST_CLIENT_ERROR="errors.exist.client.visit";
	String VISIT_EXIST_ORDER_ERROR="errors.exist.order.visit";
	
}
