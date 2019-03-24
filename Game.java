import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import java.util.Arrays;
import javafx.scene.paint.Paint;

public class Game extends Application{

  static final int JUMPHEIGHT = 60; //set height a piece can jump
  static final int MOVESPEED = 5; //set speed a piece can move

  Piece piece; //main piece in the game
  Map map; //draws the rectangles that form the map
  AnchorPane layout;
  boolean[] inputStatus = new boolean[3]; //array that contains whether left, right, or up are being pressed

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception{
    primaryStage.setTitle("D3MO");

    layout = new AnchorPane();

    //creates the sky blue background
    Rectangle background = new Rectangle(600,600,Paint.valueOf("#00BFFF"));

    piece = new Piece();
    System.out.print(piece.getX()+" "+piece.getY());

    //sets the piece's default location
    piece.setX(0);
    piece.setY(0);
    piece.setTranslateX(0);
    piece.setTranslateY(0);

    map = new Map(20); //instantiates the map

    //adds the map to the layout and then the layout to the scene
    layout.getChildren().addAll(background, map.gameLevel, piece); //!!!
    Scene scene = new Scene(layout, 600, 600);

    //resets all key inputs
    Arrays.fill(inputStatus, false);

    //All of this handles inputs
    scene.setOnKeyPressed(e -> {
      switch(e.getCode()){
        case W:
          inputStatus[0] = true;
          break;
        case A:
          inputStatus[1] = true;
          break;
        case D:
          inputStatus[2] = true;
          break;
        case Q:
          //helpful print statements that tell where the piece is, and its movement vectors
          //System.out.println(piece.	getLayoutX()+" "+piece.	getLayoutY());
          //System.out.println(piece.getX()+" "+piece.getY());
          break;
        default: break;
      }
    });
    scene.setOnKeyReleased(e->{
      switch(e.getCode()){
        case W:
          inputStatus[0] = false;
          break;
        case A:
          inputStatus[1] = false;
          break;
        case D:
          inputStatus[2] = false;
          break;
        case Q:
          break;
        default: break;
      }
    });
    //Prints where the mouse is clicked for troubleshooting
    scene.setOnMouseClicked(e->{System.out.println(e.getSceneX() +" "+ e.getSceneY());});

    AnimationTimer timer = new Timer();
    timer.start();
    //sets up the game loop

    primaryStage.setScene(scene);
    primaryStage.fullScreenProperty();
    primaryStage.show();
  }
  public class Timer extends AnimationTimer{

    @Override
    public void handle(long now){
      //the main game loop, checks inputs then checks the piece
      if(inputStatus[0] && (piece.getDir().getY()==0 && piece.getY()>200)){piece.setDir(piece.getDir().add(0,-JUMPHEIGHT));};
      if(inputStatus[1]){piece.setDir(piece.getDir().add(-5,0));};
      if(inputStatus[2]){piece.setDir(piece.getDir().add(5,0));};
      checkPiece();
      checkBounds();
    }

    private void checkPiece(){
      //Handles movement, ie gravity, inertia
      if(piece.getDir().getY()<0){
        piece.setTranslateY(piece.getY()+piece.getDir().getY()/10);
        piece.setY(piece.getY()+piece.getDir().getY()/10);
      }
      if(piece.getDir().getY()>0){
        piece.setTranslateY(piece.getY()+2);
        piece.setY(piece.getY()+2);
        piece.setDir(piece.getDir().add(0,-2));
      }
      if(piece.getDir().getY()>-2 && piece.getDir().getY()<0){
        piece.setDir(piece.getDir().add(0,-piece.getDir().getY()));
      }
      if(piece.getDir().getX()>0){
        piece.setTranslateX(piece.getX()+MOVESPEED);
        piece.setX(piece.getX()+MOVESPEED);
        piece.setDir(piece.getDir().add(-MOVESPEED,0));
      }
      if(piece.getDir().getX()<0){
        piece.setTranslateX(piece.getX()-MOVESPEED);
        piece.setX(piece.getX()-MOVESPEED);
        piece.setDir(piece.getDir().add(MOVESPEED,0));
      }
    }

    private void checkBounds(){
      //collision detection
      if(piece.getX()<5){
        piece.setX(5);
        piece.setTranslateX(5);
        if(((Level)map.getMap().get(0)).getShape().getX() != 0) map.move(-10);
      }
      else if(piece.getX()>250){
        piece.setX(250);
        piece.setTranslateX(250);
        map.move(10);
      }
      if(piece.getY()<=274){
        piece.setDir(piece.getDir().add(0, 2));
      }
      Rectangle colRect = map.findRectangle(piece.getX());
    }
  }
}
