package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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

	
	EventHandler<MouseEvent> circleEnteredEventHandler = new EventHandler<MouseEvent>() {
	        
		public void handle(MouseEvent t) {
	    
	        	CircleWithPos circle = (CircleWithPos) t.getSource();
	        	
	        	circle.setCursor(Cursor.HAND);
	        	circle.setStroke(Color.DARKMAGENTA);
	        	circle.setStrokeWidth(5);
	            System.out.println(circle.getPosition());
	            
	            
	            // else cir.setCursor(Cursor.WAIT);
	        }
	    };
	    
	    EventHandler<MouseEvent> circleLeftEventHandler = new EventHandler<MouseEvent>() {
		 
		        public void handle(MouseEvent t) {
		        	CircleWithPos circle = (CircleWithPos) t.getSource();
		        	circle.setStroke(Color.BLACK);
		        	circle.setStrokeWidth(2);
		        }
		    };

		    EventHandler<MouseEvent> houseEnteredEventHandler = new EventHandler<MouseEvent>() {
		        
				public void handle(MouseEvent t) {
			        	CircleWithPos circle = (CircleWithPos) t.getSource();
			        	circle.setCursor(Cursor.HAND);
			        	circle.setFill(Color.FLORALWHITE);
			        	
			        }
			    };
	    
			    
			    EventHandler<MouseEvent> houseLeftEventHandler = new EventHandler<MouseEvent>() {
					 
			        public void handle(MouseEvent t) {
			        	CircleWithPos circle = (CircleWithPos) t.getSource();
			        	
			        	circle.setStroke(Color.BLACK);
			        	
			        }
			    };
}
