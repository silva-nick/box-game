import java.util.Hashtable;
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

  private Hashtable<Point2D, Rectangle> map = new Hashtable<Point2D, Rectangle>();
  //should be a dictionary that maps pixles to a rectangle
  public Map(int x){
    map.put(new Point2D(0, 0), new Rectangle(0.0,   526.0, 600.0, 74.0));
    for(int i = 0; i<x; i++){
      map.put(new Point2D(0, 1), new Rectangle(0.0,   526.0, 600.0, 74.0));
    }
  }

  public Hashtable getMap(){
    System.out.println(map.keys());
    return map;
  }

  public Shape getLevel(){
    Rectangle floor = new Rectangle(0.0,   526.0, 600.0, 74.0);
    Rectangle nullPoint = new Rectangle(0.0,  0.0, 0.0, 0.0);
    Shape base = Path.union(floor, nullPoint);
    base.setFill(Paint.valueOf("#03a320"));
    return base;
  }

  public void printChangePoints(){
    Object x[] = map.keySet().toArray();
    System.out.println(Arrays.deepToString(x));
  }
}
