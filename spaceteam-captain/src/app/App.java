package app;

public class App {

	public static void main(String[] args) {
		
		CaptainWindow captainWindow = new CaptainWindow();
		
		CaptainController captainController = new CaptainController();
		
		captainController.setCaptainWindow(captainWindow);
		
		captainWindow.setCaptainController(captainController);
		
		captainController.connectAndStart();
	
	}

}
