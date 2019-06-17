package otw.share.xplatform;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import otw.share.SharedData;

public class Clipboard extends XPlatform
{
	private Context context;
	public Clipboard()
	{
		//Do nothing
	}
	
	public Clipboard(Context context)
	{
		//Set the context variable
		this.context = context;
	}
	
	public void copy(SharedData sharedData)
	{
		//Check if the OS being used is Android
		if(this.isAndroid() || this.context != null)
		{
			//Copy the text inside
			
			//Use the clipboard manager class for copying
			ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
			//Use clip data class for setting the primary clip, for copying
			ClipData clipData = ClipData.newPlainText("OverTheWifi", sharedData.getSharedData().toString());
			//Set the primary clip
			clipboardManager.setPrimaryClip(clipData);
		}
		else
		{
			//Since we are on PC / Mac / Linux, use the default copying method
			
			//Create an instance of java.awt.datatransfer.Clipboard for copying
			java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			//Use StringSelection for setting the copied text
			StringSelection selectedText = new StringSelection(sharedData.getSharedData().toString());
			//Set the copied text
			clipboard.setContents(selectedText, null);
		}
	}
}
