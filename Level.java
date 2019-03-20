import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;

public class Level{
//class that forms the members of the map arraylist
//simply just a rectangle

  private Point2D position;
  private Rectangle shape;

  public Level(Point2D pos, Rectangle shp){
    this.position = pos;
    this.shape = shp;
  }

  public Point2D getPos(){
    return position;
  }

  public void setPos(int x, int y){
    this.position = new Point2D(x, y);
  }

  public void movePos(int x){
    this.position.add(x, 0);
  }

  public Rectangle getShape(){
    return shape;
  }

  public void setShape(Rectangle newRect){
    this.shape = newRect;
  }
}
