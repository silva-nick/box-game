import java.util.ArrayList;
import javafx.scene.shape.Polygon;

public class Map{

  private ArrayList<Level> map = new ArrayList<Level>();
  //should be a dictionary that maps pixles to a rectangle
  public Map(int x){
    map.add(new Level(MapType.FLAT));
    for(int i = 0; i<x; i++){
      map.add(new Level(MapType.DEFAULT));
    }
  }

  public ArrayList<Level> getMap(){
    return map;
  }
}
