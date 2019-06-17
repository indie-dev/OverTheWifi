package otw.share.xplatform;

public class XPlatform 
{
	//Extended by most classes in OverTheWifi
	
	//isAndroid detects if the OS being used is Android or not
	public boolean isAndroid()
	{
		//Return if java.vm.name in lower case contains dalvik
		return System.getProperty("java.vm.name").toLowerCase().contains("dalvik");
	}
	
	public String getVMName()
	{
		//Return the VM name
		return System.getProperty("java.vm.name");
	}
}
