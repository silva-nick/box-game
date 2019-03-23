import javafx.scene.shape.Rectangle;

public class Level{
//class that forms the members of the map arraylist
//simply just a rectangle

  private double xStart;
  private Rectangle shape;

  public Level(double x, Rectangle shp){
    this.xStart = x;
    this.shape = shp;
  }

  public double getPos(){
    return xStart;
  }

  public void setPos(int x){
    this.xStart = x;
  }

  public Rectangle getShape(){
    return shape;
  }

  public void setShape(Rectangle newRect){
    this.shape = newRect;
  }
}
