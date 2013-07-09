package com.blve.hibernate.pojo;

public class Application  
{
	private String applicationID;
	private String applicationName;
	private String applicationHostIP;
	private String applicationStatus;
	private String applicationAdditionalParam1;
	private String applicationAdditionalParam2;
	private String applicationAdditionalParam3;
	
	public String getApplicationAdditionalParam1() 
	{
		if(applicationAdditionalParam1 == null)
		{
			return null;
		}
		else
		{
			return applicationAdditionalParam1.trim();
		}
	}
	
	public void setApplicationAdditionalParam1(
			String applicationAdditionalParam1) 
	{
		this.applicationAdditionalParam1 = applicationAdditionalParam1 == null ? null : applicationAdditionalParam1.trim();
	}
	
	public String getApplicationAdditionalParam2() 
	{
		if(applicationAdditionalParam2 == null)
		{
			return null;
		}
		else
		{
			return applicationAdditionalParam2.trim();
		}
	}
	
	public void setApplicationAdditionalParam2(
			String applicationAdditionalParam2) 
	{
		this.applicationAdditionalParam2 = applicationAdditionalParam2 == null ? null : applicationAdditionalParam2.trim();
	}
	
	public String getApplicationAdditionalParam3() 
	{
		if(applicationAdditionalParam3 == null)
		{
			return null;
		}
		else
		{
			return applicationAdditionalParam3.trim();
		}
	}
	
	public void setApplicationAdditionalParam3(
			String applicationAdditionalParam3) 
	{
		this.applicationAdditionalParam3 = applicationAdditionalParam3 == null ? null : applicationAdditionalParam3.trim();
	}
	
	public String getApplicationHostIP() 
	{
		if(applicationHostIP == null)
		{
			return null;
		}
		else
		{
			return applicationHostIP.trim();
		}
	}
	
	public void setApplicationHostIP(String applicationHostIP) 
	{
		this.applicationHostIP = applicationHostIP == null ? null : applicationHostIP.trim();
	}
	
	public String getApplicationID() 
	{
		if(applicationID == null)
		{
			return null;
		}
		else
		{
			return applicationID.trim();
		}
	}
	
	public void setApplicationID(String applicationID) 
	{
		this.applicationID = applicationID == null ? null : applicationID.trim();
	}
	
	public String getApplicationName() 
	{
		if(applicationName == null)
		{
			return null;
		}
		else
		{
			return applicationName.trim();
		}
	}
	
	public void setApplicationName(String applicationName) 
	{
		this.applicationName = applicationName == null ? null : applicationName.trim();
	}
	
	public String getApplicationStatus() 
	{
		if(applicationStatus == null)
		{
			return null;
		}
		else
		{
			return applicationStatus.trim();
		}
	}
	
	public void setApplicationStatus(String applicationStatus) 
	{
		this.applicationStatus = applicationStatus == null ? null : applicationStatus.trim();
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if((obj instanceof Application))
		{
			return ((Application) obj).getApplicationID().equalsIgnoreCase(applicationID);
		}
		return false;
	}
	
}
