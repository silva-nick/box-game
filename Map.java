import java.util.ArrayList;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Path;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import javafx.geometry.Point2D;
import java.util.Set;
import java.util.Arrays;

public class Map{

  private ArrayList<Level> map = new ArrayList<Level>();
  //should be a dictionary that maps pixles to a rectangle
  public Map(int x){
    map.add(new Level(new Point2D(0, 0), new Rectangle(0.0, 526.0, 600.0, 74.0)));
    for(int i = 0; i<x; i++){
      map.add(new Level(new Point2D(0, 1), new Rectangle(0.0,   526.0, 600.0, 74.0)));
    }
  }

  public ArrayList getMap(){
    return map;
  }

  public Shape getLevel(){
    printChangePoints();
    Rectangle floor = new Rectangle(0.0,   526.0, 600.0, 74.0);
    Rectangle nullPoint = new Rectangle(0.0,  0.0, 0.0, 0.0);
    Shape base = Path.union(floor, nullPoint);
    base.setFill(Paint.valueOf("#03a320"));
    return base;
  }

  public void printChangePoints(){
    for(int x = 0; x < map.size() - 1; x++){
      System.out.println(map.get(x));
    }
  }
}
