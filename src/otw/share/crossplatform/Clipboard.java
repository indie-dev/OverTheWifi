package otw.share.crossplatform;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import otw.share.ShareData;

public class Clipboard extends CrossPlatformBase
{

	private Context context;
	
	public Clipboard()
	{
		context = null;
	}
	
	public Clipboard(Context context)
	{
		this.context = context;
	}
	
	public Context getContext()
	{
		return context;
	}
	
	public void copy(ShareData data)
	{
		if(isAndroid())
		{
			context = this.getContext();
			//Use clipboard manager
			ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
			ClipData clipData = ClipData.newPlainText("OverTheWifi", data.getShareData().toString());
			manager.setPrimaryClip(clipData);
		}else
		{
			//Use Clipboard class
			StringSelection selectedString = new StringSelection(data.getShareData().toString());
			java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selectedString, null);
		}
	}
}
