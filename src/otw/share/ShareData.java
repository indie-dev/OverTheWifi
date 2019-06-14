package otw.share;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShareData extends Object implements Serializable
{
	public static int DEFAULT_PORT = 2077; //There's a cyberpunk reference in there somewhere
	public static enum ShareDataType
	{
		CLIPBOARD_DATA,
		FILE,
		OTHER
	}
	
	private List<Object> shareDataList;
	private List<ShareDataType> shareDataTypeList;
	private Object shareData;
	private ShareDataType dataType;
	public static ShareData setShareData(Object data, ShareDataType dataType)
	{
		ShareData shareData = new ShareData();
		shareData.shareData = data;
		shareData.shareDataList = new ArrayList<>();
		shareData.shareDataList.add(shareData.shareData);
		shareData.dataType = dataType;
		shareData.shareDataTypeList = new ArrayList<>();
		shareData.shareDataTypeList.add(shareData.dataType);
		return shareData;
	}
	
	public Object getShareData()
	{
		return shareData;
	}
	
	public ShareDataType getShareDataType()
	{
		return dataType;
	}
	
	public List<Object> getShareDataList()
	{
		return shareDataList;
	}
	
	public List<ShareDataType> getShareDataTypeList()
	{
		return this.shareDataTypeList;
	}
	
	public void add(Object object, ShareDataType dataType)
	{
		this.shareDataList.add(object);
		this.shareDataTypeList.add(dataType);
	}
	
	
}
