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

  static final int JUMPHEIGHT = 60;
  static final int MOVESPEED = 5;
  Scene scene;
  Piece piece;
  boolean[] inputStatus = new boolean[3];
  Map map;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception{
    primaryStage.setTitle("D3MO");

    AnchorPane layout = new AnchorPane();

    Rectangle background = new Rectangle(600,600,Paint.valueOf("#00BFFF"));

    piece = new Piece();
    System.out.print(piece.getX()+" "+piece.getY());

    piece.setX(0);
    piece.setY(0);
    piece.setTranslateX(0);
    piece.setTranslateY(0);

    map = new Map(1);

    layout.getChildren().addAll(background, map.getLevel(), piece);
    scene = new Scene(layout, 600, 600);

    Arrays.fill(inputStatus, false);

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
          System.out.println(piece.	getLayoutX()+" "+piece.	getLayoutY());
          System.out.println(piece.getX()+" "+piece.getY());
          System.out.println(piece.getDir().toString());
          break;
        case E:
          //layout.getChildren().remove(map.getMap().get(0).getLevel());
        //  layout.getChildren().add(map.getMap().get(1).getLevel());
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
          System.out.println(map.getLevel().getLayoutBounds().toString()+"/n");
          break;
        default: break;
      }
    });
    scene.setOnMouseClicked(e->{System.out.println(e.getSceneX() +" "+ e.getSceneY());});

    AnimationTimer timer = new Timer();
    timer.start();

    primaryStage.setScene(scene);
    primaryStage.fullScreenProperty();
    primaryStage.show();
  }
  public class Timer extends AnimationTimer{

    @Override
    public void handle(long now){
      if(inputStatus[0] && (piece.getDir().getY()==0 && piece.getY()>200)){piece.setDir(piece.getDir().add(0,-JUMPHEIGHT));};
      if(inputStatus[1]){piece.setDir(piece.getDir().add(-5,0));};
      if(inputStatus[2]){piece.setDir(piece.getDir().add(5,0));};
      checkPiece();
      checkBounds();
    }

    private void checkPiece(){
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
      if(piece.getX()<5){
        piece.setX(5);
        piece.setTranslateX(5);
        if(piece.getDir().getX()<0) piece.setDir(Math.abs(piece.getDir().getX()),piece.getDir().getY());
      }
      else if(piece.getX()>280){
        piece.setX(280);
        piece.setTranslateX(280);
        if(piece.getDir().getX()>0) piece.setDir(-1*Math.abs(piece.getDir().getX()),piece.getDir().getY());
      }
      if(piece.getY()<=274){
        piece.setDir(piece.getDir().add(0, 2));
      }
      if(piece.intersects(map.getLevel().getBoundsInLocal())){System.out.println("They intersect");}
      //if(piece.intersects(map.getMap().get(0).getLevel().getBoundsInLocal())){System.out.println("They intersect");}
    }
  }
}
