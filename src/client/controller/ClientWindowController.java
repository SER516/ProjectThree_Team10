package client.controller;

import java.io.IOException;

import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

import java.net.URISyntaxException;

import client.model.Parameters;
import client.view.ClientWindowView;

public class ClientWindowController {
	private ClientWindowView view;
	public WebSocketContainer container;
	public ClientSocket clientSocket = null;
	
	/*public ClientWindowController() {
		view = new ClientWindowView();
	}*/
	
	public ClientWindowController(ClientWindowView view) {
		this.view = view;
	}
	
	/*public void createSocket(String address, int port, String context, String page) {
		CountDownLatch latch = new CountDownLatch(1);

        container = ContainerProvider.getWebSocketContainer();
        clientSocket = new ClientSocket(this);

        String uri = "ws://" + address + ":" + port + "/" + context + "/" + page;

        try {
            clientSocket.setSession(container.connectToServer(clientSocket, new URI(uri)));
            latch.await();
        } catch (DeploymentException | URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

	}*/
	
	public void createSocket(String address, int port, String context, String page) {
        clientSocket = new ClientSocket(this, address, port, context, page);
        clientSocket.start();
	}
	
	public void update(Parameters param) {
		// TODO: need to change to MVC
		view.update(param);
	}

	public ClientSocket getSocket() {
		return clientSocket;
	}
	
	public static void main(String args[]) throws URISyntaxException, IOException, DeploymentException {
		ClientWindowView view = new ClientWindowView();
		/*javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	view = new ClientWindowView();
	        }
	    });*/
		ClientWindowController ctrl = new ClientWindowController(view);
		view.bindController(ctrl);
	}
}