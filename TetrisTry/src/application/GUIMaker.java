package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;



public class GUIMaker extends Application {

    @Override public void start(Stage stage) {
        
    	root = new Group();
        scene = new Scene(root, 800, 650);
        this.stage=stage;
        scene.setFill(Color.LIGHTGRAY);
        ta1=new TextArea("Hello, and welcome to Dan's Tetris Game. Spacebar always starts a new game. Use I, J, K, and L keys to play. \nYou get a point for each line you clear.");
        ta1.setPrefSize(800, 50);
        ta1.setEditable(false);
        //ta1.set
        //ta1.setBackground(new Background(Color.ALICEBLUE));//(Color.ALICEBLUE);
        //ta1.setBorder();
        //root.getChildren().add(ta1);
        
        setupPixels();
        
        for(Rectangle[] row: pixels)
	    	for(Rectangle pixel: row)
	    	{
	    		root.getChildren().add(pixel);
	    	}
        Timeline blockMoveTimer = new Timeline(new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(playing)
            		playGame();
            }
        }));
        blockMoveTimer.setCycleCount(Timeline.INDEFINITE);
        root.getChildren().add(ta1);
        ta1.setOnKeyTyped(new EventHandler<KeyEvent>(){
        	public void handle(KeyEvent ke)
        	{
        		if(ke.isAltDown())
        			System.out.println("alt");
        		move=0;
        		if(ke.getCharacter().matches("l"))
        			tetris.slideCurrentPiece(1);
        		else if(ke.getCharacter().matches("j"))
        			tetris.slideCurrentPiece(-1);
        		else if(ke.getCharacter().matches("i"))
        			tetris.rotateCurrentPiece();
        		else if(ke.getCharacter().matches("k"))
        			playGame();
        		//System.out.println(ke.getCharacter());
        		else if(ke.getCharacter().matches(" "))
        		{
        			tetris=new Tetris();
        			playing=true;
        		}
        		drawBoard(tetris.getBoard());
        	}
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
        	public void handle(KeyEvent ke)
        	{
        		move=0;
        	}
        	});
       
        stage.setScene(scene);
        stage.show();
        blockMoveTimer.play();
        //playGame();
        //System.out.println("here1");
        
        
        
       
    }
    static Tetris tetris= new Tetris();
    static Group root;
    static Scene scene;
    static Stage stage;
    static int move;
    public final static int WINDOW_HEIGHT=15;
    public final static int WINDOW_WIDTH=20;
    public final static int PIXEL_SIZE=40;
    static TextArea ta1;
    static Rectangle pixels[][] = new Rectangle[WINDOW_WIDTH][WINDOW_HEIGHT];
    boolean playing=false;
   
    private void setupPixels()
    {
    	for(int i=0; i<WINDOW_WIDTH; i++)
    		for(int j=0; j<WINDOW_HEIGHT; j++)
    		{
    			pixels[i][j]=new Rectangle(i*PIXEL_SIZE,WINDOW_HEIGHT*PIXEL_SIZE-(j+1)*PIXEL_SIZE+50,PIXEL_SIZE,PIXEL_SIZE);
    			pixels[i][j].setFill(Color.WHITE);
    			if((i+j)%2==0)
    				pixels[i][j].setFill(Color.BLACK);
    			//pixels[i][j].notifyAll();
    			
    		}
    }
    public static void drawBoard(boolean[][] values){
    	for(int i=0; i<WINDOW_WIDTH; i++)
    		for(int j=0; j<WINDOW_HEIGHT; j++)
    		{
    			//root.getChildren().remove(pixels[i][j]);
    			if(values[i][j])
    				pixels[i][j].setFill(Color.BLACK);
    			else
    				pixels[i][j].setFill(Color.WHITE);
    			//pixels[i][j];
    			//root.getChildren().add(pixels[i][j]);
    		}
    }
    public static void main(String[] args) {
        Application.launch(args);
       
    }
    public void playGame() 
    {
    	 //Tetris tetris= new Tetris();
    	 //System.out.println("here");
    	 boolean notLosing=tetris.checkLose();
    	 int count=0;
         if(notLosing)
         {
        	stage.setTitle("Dan's Tetris Game");
        	ta1.setText("Current Score:"+tetris.getScore());
        	count++;
         	notLosing=tetris.advance();
         	drawBoard(tetris.getBoard());
         	/*for(boolean[] row: tetris.getBoard())
    		{
    			for(boolean b:row)
    				if(b)
    					System.out.print("0");
    				else
    					System.out.print("_");
    			System.out.println();
    		}*/
         	/*try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
         	//root.notify();
         	//System.out.println("loop");
         }
         else
         {
        	 playing=false;
        	 ta1.setText("Game over. Final Score: "+tetris.getScore()+". Press spacebar to restart.");
         	
         }
         
        
    }
}
