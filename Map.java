import java.util.ArrayList;
import java.util.Set;
import java.util.Arrays;
import java.util.Random;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Path;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import javafx.geometry.Point2D;

public class Map{

  private ArrayList<Level> map = new ArrayList<Level>();
  //SHOULD MAKE THIS A QUEUE
  //an arraylist of all of the rectangles on the map

  public Shape gameLevel;

  public Map(int x){
    //constructs a map with points and stuff
    Random levelPos = new Random(); //random for defining height and width of new blocks

    map.add(new Level(0, new Rectangle(0.0,  0.0, 0.0, 0.0))); //initializes the map with a blank level
    for(int i = 1; i<x; i++){
      Rectangle temp = map.get(i - 1).getShape();
      double lastPosition = temp.getX() + temp.getWidth(); //finds the ending position of the last Level
      map.add(new Level(lastPosition, new Rectangle(lastPosition, 426.0 + levelPos.nextDouble() * 100 , levelPos.nextDouble() * 1000, 300)));
    }
    setLevel();
  }

  public ArrayList getMap(){
    return map;
  }

  public void setLevel(){

    Shape base = new Rectangle(0.0,  0.0, 0.0, 0.0);
    for(int i = 0; i < map.size(); i++){
      base = Path.union(base, map.get(i).getShape());
    }
    gameLevel = base;
    gameLevel.setFill(Paint.valueOf("#03a320"));
  }

  public void printChangePoints(){

    for(int x = 0; x < map.size(); x++){
      System.out.println(map.get(x).getPos());
    }
  }

  public void move(double xVelocity){

    for(Level m : map){
      m.getShape().setTranslateX(m.getShape().getX() - xVelocity);
      m.getShape().setX(m.getShape().getX() - xVelocity);
    }
    gameLevel.setLayoutX(gameLevel.getLayoutX() - xVelocity);
  }
}
