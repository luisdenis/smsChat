package com.blve.hibernate.pojo;


public class Parameter 
{ 
   	private int parameterID;
   	private ParameterType parameterType;
   	private Application application;
   	private String parameterName;
   	private String parameterDescription;
   	private String parameterStatus;
   	private String parameterValue;
   	
   	public Application getApplication() 
   	{
		return application;
	}
   	
   	public void setApplication(Application application) 
   	{
		this.application = application;
	}
   	
   	
   	public String getParameterDescription() 
   	{
   		if(parameterDescription == null)
   		{
   			return null;
   		}
   		else
   		{
   			return parameterDescription.trim();
   		}
	}
   	
   	public void setParameterDescription(String parameterDescription) 
   	{
		this.parameterDescription = parameterDescription == null ? null : parameterDescription.trim();
	}
   	
   	public int getParameterID() 
   	{
		return parameterID;
	}
   	
   	public void setParameterID(int parameterID) 
   	{
		this.parameterID = parameterID;
	}
   	
   	public String getParameterName() 
   	{
   		if(parameterName == null)
   		{
   			return null;
   		}
   		else
   		{
   			return parameterName.trim();
   		}
	}
   	
   	public void setParameterName(String parameterName) 
   	{
		this.parameterName = parameterName == null ? null : parameterName.trim();
	}
   	
   	public String getParameterStatus() 
   	{
   		if(parameterStatus == null)
   		{
   			return null;
   		}
   		else
   		{
   			return parameterStatus.trim();
   		}
	}
   	
   	public void setParameterStatus(String parameterStatus) 
   	{
		this.parameterStatus = parameterStatus == null ? null : parameterStatus.trim();
	}
   	
   	public ParameterType getParameterType() 
   	{
		return parameterType;
	}
   	
   	public void setParameterType(ParameterType parameterType) 
   	{
		this.parameterType = parameterType;
	}
   	
   	public String getParameterValue() 
   	{
   		if(parameterValue == null)
   		{
   			return null;
   		}
   		else
   		{
   			return parameterValue;
   		}
	}
   	
   	public void setParameterValue(String parameterValue) 
   	{
		this.parameterValue = parameterValue == null ? parameterValue : parameterValue.trim();
	}
   	
	@Override
	public boolean equals(Object obj) 
	{
		if((obj instanceof Parameter))
		{
			return parameterID == ((Parameter) obj).getParameterID();
		}
		return false;
	}
   	
}
