package com.blve.smschat.domain;

/**
 * Encapsula el resultado de una operacion realizada en capa de negocios.
 * @author ldenis
 */
public class OperationResult {

	/**
	 * Tipo de resultado: error, warning, confirmacion
	 */
	private int resultType;
	/**
	 * Codigo del resultado
	 */
	private String resultCode;
	/**
	 * Mensaje asoaciado al codigo
	 */
//	private String resultMessage;
	
	public final static int CONFIRM_MESSAGE = 0;
	public final static int ERROR_MESSAGE   = 1;
	public final static int WARNING_MESSAGE = 2;
	
	/**
	 * Asigna el codigo del mensaje a mostrar como resultado de la operacion 
	 * y establece el tipo de resultado como: ERROR.
	 * Se utiliza cuando la operacion no se completo correctamente o genero error, 
	 * y se quiere desplegar un mensaje de error al usuario.
	 * @param resultCode Codigo del mensaje
	 */	
	public void setErrorCode(String resultCode) {
		this.resultType = ERROR_MESSAGE;
		this.resultCode = resultCode;
	}
	
	/**
	 * Asigna el codigo del mensaje a mostrar como resultado de la operacion 
	 * y establece el tipo de resultado como: CONFIRMACION.
	 * Se utiliza cuando el resultado de la operacion es satisfactorio, y se quiere desplegar un 
	 * mensaje de confirmacion al usuario.
	 * @param resultCode Codigo del mensaje
	 */
	public void setConfirmCode(String resultCode) {
		this.resultType = CONFIRM_MESSAGE;
		this.resultCode = resultCode;
	}
	/**
	 * Asigna el codigo del mensaje a mostrar como resultado de la operacion 
	 * y establece el tipo de resultado como: WARNING.
	 * Se utiliza cuando la operacion se ejecuta pero genera warning, y se quiere desplegar un 
	 * mensaje de alerta al usuario.
	 * @param resultCode Codigo del mensaje
	 */	
	public void setWarningCode(String resultCode) {
		this.resultType = WARNING_MESSAGE;
		this.resultCode = resultCode;
	}
	
	
	public int getResultType() {
		return resultType;
	}
	public void setResultType(int resultType) {
		this.resultType = resultType;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
/*	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
*/
}