package com.blve.hibernate.pojo;


public class ParameterType
{
	private int parameterTypeID;
	private String parameterType;
	private String parameterTypeDescription;
	
	public String getParameterType() 
	{
		if(parameterType == null)
		{
			return null;
		}
		else
		{
			return parameterType.trim();
		}
	}
	
	public void setParameterType(String parameterType) 
	{
		this.parameterType = parameterType == null ? null : parameterType.trim();
	}
	
	public String getParameterTypeDescription() 
	{
		if(parameterTypeDescription == null)
		{
			return null;
		}
		else
		{
			return parameterTypeDescription;
		}
	}
	
	public void setParameterTypeDescription(String parameterTypeDescription) 
	{
		this.parameterTypeDescription = parameterTypeDescription == null ? null : parameterTypeDescription.trim();
	}
	
	public int getParameterTypeID() 
	{
		return parameterTypeID;
	}
	
	public void setParameterTypeID(int parameterTypeID) 
	{
		this.parameterTypeID = parameterTypeID;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if((obj instanceof ParameterType))
		{
			return parameterTypeID == ((ParameterType) obj).getParameterTypeID();
		}
		return false;
	}
}
