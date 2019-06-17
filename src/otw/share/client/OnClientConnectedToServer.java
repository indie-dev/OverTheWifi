package otw.share.client;

import otw.share.SharedData;

public interface OnClientConnectedToServer 
{
	public void onConnectionSuccess();
	public void onConnectionFail(Throwable cause);
	public void onDataRecieved(SharedData data);
	public void onFailedToRetrieveData(Throwable cause);
}
