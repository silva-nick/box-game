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

  public Map(int x){
    //constructs a map with points and stuff
    Random levelPos = new Ra
    ndom(); //random for defining height and width of new blocks

    map.add(new Level(new Point2D(0, 0), new Rectangle(0.0,  0.0, 0.0, 0.0))); //initializes the map with a blank level
    for(int i = 1; i<x; i++){
      Rectangle temp = map.get(i - 1).getShape();
      double lastPosition = temp.getX() + temp.getWidth(); //finds the ending position of the last Level
      map.add(new Level(new Point2D(0, 1), new Rectangle(lastPosition, 426.0 + levelPos.nextDouble() * 100 , levelPos.nextDouble() * 1000, 300)));
    }
  }

  public ArrayList getMap(){
    return map;
  }

  public Shape getLevel(){

    Shape base = new Rectangle(0.0,  0.0, 0.0, 0.0);
    for(int i = 0; i < map.size(); i++){
      base = Path.union(base, map.get(i).getShape());
    }
    base.setFill(Paint.valueOf("#03a320"));
    return base;
  }

  public void printChangePoints(){

    for(int x = 0; x < map.size(); x++){
      System.out.println(map.get(x).getPos());
    }
  }
}
