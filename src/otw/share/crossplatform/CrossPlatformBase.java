package otw.share.crossplatform;

import android.content.Context;

public class CrossPlatformBase 
{
	public boolean isAndroid()
	{
		return System.getProperty("java.vm.name").toLowerCase().contains("dalvik");
	}
}
