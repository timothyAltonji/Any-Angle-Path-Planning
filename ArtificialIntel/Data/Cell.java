package ArtificialIntel.Data;

import java.util.ArrayList;
import java.util.Comparator;

public class Cell extends Vertex { 
    //Grid grid;
    
    protected boolean bFree = true;
    public Cell parent;
    public double cost = Integer.MAX_VALUE;
    public ArrayList<Cell> neighbors = new ArrayList<Cell>();
    public boolean IsFree(){
        return bFree;
    }
    protected Cell(int x, int y, int free){
        super(x, y);
        bFree = free == 0;
    }
    boolean getFree(){
        return bFree;
    }
    public void setCost(double c)
    {
        this.cost = c;
    }
}