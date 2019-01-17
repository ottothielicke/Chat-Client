package server.main.networking;

public class NetworkingReferenceObject {
	private ThreadPoolHandler threadPoolHandler;
	private ServerSocketHandler serverSocketHAndler;
	public ThreadPoolHandler getThreadPoolHandler() {
		return threadPoolHandler;
	}
	public void setThreadPoolHandler(ThreadPoolHandler threadPoolHandler) {
		this.threadPoolHandler = threadPoolHandler;
	}
	public ServerSocketHandler getServerSocketHAndler() {
		return serverSocketHAndler;
	}
	public void setServerSocketHandler(ServerSocketHandler serverSocketHAndler) {
		this.serverSocketHAndler = serverSocketHAndler;
	}
}
