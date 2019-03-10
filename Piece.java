import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;

public class Piece extends Rectangle{

  static final int XSIZE = 30;
  static final int YSIZE = 40;
  private Point2D dir = new Point2D(0,0);


  public Piece(){
    super.setWidth(XSIZE);
    super.setHeight(YSIZE);
  }

  public void setDir(Point2D x){
    dir = x;
  }

  public void setDir(double x, double y){
    Point2D z = new Point2D(x,y);
    dir = z;
  }

  public Point2D getDir(){
    return dir;
  }
}
