package Game;
public class BoardBox {
  //ATTRIBUTES
  private  int value;
  private boolean isWestVisited = false;
  private boolean isEastVisited = false;
  private boolean isNorthVisited = false;
  private boolean isSouthVisited = false;
  //We keep track of the position of each BoardBox to allow for the iterative solution
  protected int pos_i;
  protected int pos_j;

  //CONTSTRUCTOR
  public BoardBox(int value, int pos_i, int pos_j){
    this.value=value;
    this.pos_i=pos_i;
    this.pos_j=pos_j;
  }

  //GETTERS AND SETTERS
  public void setValue(int value){
    this.value=value;
  }
  public int getValue() {
      return value;
  }
  public boolean getIsWestVisited(){
    return this.isWestVisited;
  }
  public boolean getIsEastVisited(){
    return this.isEastVisited;
  }
  public boolean getIsNorthVisited(){
    return this.isNorthVisited;
  }
  public boolean getIsSouthVisited(){
    return this.isSouthVisited;
  }
  public void setEastVisited(boolean isEastVisited) {
      this.isEastVisited = isEastVisited;
  }
  public void setNorthVisited(boolean isNorthVisited) {
      this.isNorthVisited = isNorthVisited;
  }
  public void setSouthVisited(boolean isSouthVisited) {
      this.isSouthVisited = isSouthVisited;
  }
  public void setWestVisited(boolean isWestVisited) {
      this.isWestVisited = isWestVisited;
  }

}
