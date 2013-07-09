package com.blve.smschat.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class Utilities {

	static String regex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	
	
	public static boolean isValidEmail(String email){
		
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		
		if(matcher.matches()){
			return true;
		}else{
			return false;
		}
	}
    /**
     * Redondea el número flotante pasado como parámetro a los decimales 
     * especificados por el parámetro decimales 
     * @param flotante Número flotante que se desea redondear
     * @param decimales Número de decimales a los cuales se desea redondear el 
     *                  flotante
     * @return El flotante pasado como parámetro redondeado
     */
	  public static double Round(double Rval, int Rpl) {
		  double p = (double)Math.pow(10,Rpl);
		  Rval = Rval * p;
		  double tmp = Math.round(Rval);
		  
		  return (double)tmp/p;
	    }
    
	  public static boolean isValidDate(String date) {
		    try {
		     
		    	if(date!=null && date.length()==10){
		    		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				     df.setLenient(false);   // this is important!
				     df.parse(date);
		    	}else{
		    		return false;
		    	}
		    			
		    }
		    catch (ParseException e) {
		    	return false;
		    }
		    catch (IllegalArgumentException e) {
		    	return false;
		    }
		    return true;
	  }
	  
		private static String convertToHex(byte[] data) {
	        StringBuffer buf = new StringBuffer();
	        for (int i = 0; i < data.length; i++) {
	        	int halfbyte = (data[i] >>> 4) & 0x0F;
	        	int two_halfs = 0;
	        	do {
		        	if ((0 <= halfbyte) && (halfbyte <= 9))
		                buf.append((char) ('0' + halfbyte));
		            else
		            	buf.append((char) ('a' + (halfbyte - 10)));
		        	halfbyte = data[i] & 0x0F;
	        	} while(two_halfs++ < 1);
	        }
	        return buf.toString();
	    }
	 
		public static String MD5(String text) 
		throws NoSuchAlgorithmException, UnsupportedEncodingException  {
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			byte[] md5hash = new byte[32];
			md.update(text.getBytes("iso-8859-1"), 0, text.length());
			md5hash = md.digest();
			return convertToHex(md5hash);
		}

		public static String imageExtention(String name){
			String extention= null;
			
			StringTokenizer token = new StringTokenizer(name, ".");
			
			while(token.hasMoreTokens()){
				extention= token.nextToken();
			}
			
			if(extention.matches("jpg||png||gif||bmp||tiff")){
				
				return "."+extention;
			}else{
				
				return "jpg";
			}
			
		}
		
		public static String getActualDateFirstDay(){
			
			
				GregorianCalendar gc = new GregorianCalendar();
				
				int month = gc.get(GregorianCalendar.MONTH)+1;
				int year = gc.get(GregorianCalendar.YEAR);
				
				StringBuilder fromDate = new StringBuilder();
								
				fromDate.append(year + "-");
				if(month<10){
					fromDate.append("0");
				}
				fromDate.append(month+ "-01");
	
			
			return fromDate.toString();
			
		}
		
		public static String getActualDateLastDay() {
			
			GregorianCalendar gc = new GregorianCalendar();
			
			//TODO ARREGLAR ESTA HORROROSO
			int month = gc.get(GregorianCalendar.MONTH)+1;
			int year = gc.get(GregorianCalendar.YEAR);
			
			
			StringBuilder toDate = new StringBuilder();
			toDate.append(year + "-");
			
			if(month<10){
				toDate.append("0");
			}
			
			toDate.append(month+ "-"); 
			
			int lastDay = gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);//Ultimo dia del mes
			if(lastDay<10)
			 	toDate.append("0");
			
			toDate.append(lastDay);  
			
			
			return toDate.toString();
			
		}
		
		

		
		
		
		
}
