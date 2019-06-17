package otw.share.server;

public interface OnServerHostingSharedData
{
	public void onServerSuccessfullyCreated();
	public void onFailedToCreateServer(Throwable cause);
	public void onHosted();
	public void onFailedToHostSharedData(Throwable cause);
	
	public void onShared();
	public void onFailedToShare(Throwable throwable);
}
