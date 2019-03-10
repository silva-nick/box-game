import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Path;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import javafx.geometry.Point2D;

public class Level{

  private Shape base;
  private Point2D startPoint = new Point2D(0.0, 0.0);

  public Level(MapType type){
    switch(type){
      case DEFAULT:
        buildDefault();
        break;
      case FLAT:
        buildFlat();
        break;
      default:
        buildDefault();
    }
  }

  private void buildDefault(){
    Rectangle floor1 = new Rectangle(0.0,   526.0, 300.0, 74.0);
    Rectangle floor2 = new Rectangle(300.0,   426.0, 300.0, 174.0);
    base = Path.union(floor1, floor2);
    base.setFill(Paint.valueOf("#03a320"));
    startPoint.add(10.0, 566.0);
  }

  private void buildFlat(){
    Rectangle floor = new Rectangle(0.0,   526.0, 600.0, 74.0);
    Rectangle nullPoint = new Rectangle(0.0,  0.0, 0.0, 0.0);
    base = Path.union(floor, nullPoint);
    base.setFill(Paint.valueOf("#03a320"));
  }

  public Shape getLevel(){
    return base;
  }
}
