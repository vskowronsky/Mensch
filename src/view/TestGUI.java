package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.RectangleWithPos;

public class TestGUI extends Application {

	public void start(Stage primaryStage) throws Exception {
		PlayerPane playerPane = new PlayerPane(this);
		InfoPane infoPane = new InfoPane(1, new MenuBar());
		DicePane dicePane = new DicePane();
		
		ScenePane root = new ScenePane(playerPane, infoPane, dicePane);
		//primaryStage.getChildren().add(stage);

		
		PlayerStage stage = new PlayerStage(root);
		stage.show();
	}
	
	public static void main (String[]args){
		launch(args);
		
		
	}

	
	EventHandler<MouseEvent> recEnteredEventHandler = 
	        new EventHandler<MouseEvent>() {
	 
	        @Override
	        public void handle(MouseEvent t) {
	    
	        	
	        	RectangleWithPos rec = (RectangleWithPos) t.getSource();
	            rec.setFill(Color.GREEN);
	            System.out.println(rec.getPosition());
	            
	            
	            
	        }
	    };
	    
	    EventHandler<MouseEvent> recLeftEventHandler = 
		        new EventHandler<MouseEvent>() {
		 
		        @Override
		        public void handle(MouseEvent t) {
		            Rectangle rec = (Rectangle) t.getSource();
		            rec.setFill(Color.FLORALWHITE);
		            
		        }
		    };


	    
	
}
