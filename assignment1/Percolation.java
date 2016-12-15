import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int gridsize;          // n
    private boolean[] opengrid;              // true means open, false means closed
    private WeightedQuickUnionUF fullgrid;   
    private WeightedQuickUnionUF auxgrid;
    private int virtualtop = 0;
    private int virtualbottom;
    public Percolation(int n)    // create n-by-n grid, with all sites blocked
    {
     if(n <= 0)
      throw new IllegalArgumentException("Invalid grid size");
     gridsize = n;
     virtualbottom = (n*n) + 1;
     fullgrid = new WeightedQuickUnionUF(n*n + 2);
     auxgrid = new WeightedQuickUnionUF(n*n + 1);
     opengrid = new boolean[n*n + 1];     // for convenience, aligned opengrid indices
     for(int i = 1; i < (n*n); i++){      // with indices of fullgrid
      opengrid[i] = false;      
     }
     opengrid[0] = true;
    }               
    private void validate(int row, int col)    // error check
    {
        if (row < 1 || col < 1 || row > gridsize || col > gridsize)
            throw new IndexOutOfBoundsException("Invalid coordinate: (" + row + "," + col + ")");  
    }
    private int xyto1D(int row, int col)
    {
     return (row-1)*gridsize + (col);    // maps 2D coordinates to values from 1 - N^2
    }  
    public void open(int row, int col)       // open site (row, col) if it is not open already
    {   
     validate(row, col);
     int p = xyto1D(row, col), q;
     if(!isOpen(row, col)){
       opengrid[p] = true;
       if(row == 1){
        fullgrid.union(virtualtop, p);
        auxgrid.union(virtualtop, p);
       }
       if(row == gridsize)
        fullgrid.union(virtualbottom, p);
       if(row != 1 && isOpen(row-1, col)){       // check neighboring sites
        q = xyto1D(row-1, col);
        fullgrid.union(p, q);
        auxgrid.union(p, q);
       }
       if(row != gridsize && isOpen(row+1, col)){
        q = xyto1D(row+1, col);
        fullgrid.union(p, q);
        auxgrid.union(p, q);
       }
       if(col != 1 && isOpen(row, col-1)){
        q = xyto1D(row, col-1);
        fullgrid.union(p, q);
        auxgrid.union(p, q);
       }
       if(col != gridsize && isOpen(row, col+1)){
        q = xyto1D(row, col+1);
        fullgrid.union(p, q);
        auxgrid.union(p, q);
       }
     }
    }
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
     validate(row, col);
     int p = xyto1D(row, col);
     return opengrid[p];
    }
    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
     validate(row, col);
     int p = xyto1D(row, col); 
     return auxgrid.connected(virtualtop, p) && fullgrid.connected(virtualtop, p);   
    }
    public boolean percolates()              // does the system percolate?
    {
     return fullgrid.connected(virtualtop, virtualbottom);
    }
    /*
    public static void main(String[] args)   // test client (optional)
    {
    
     Percolation test = new Percolation(3);
     test.open(1,1);
     test.open(1,2);
     if(test.fullgrid.connected(0, 1)){
      System.out.print("works!");
     }  
    }
    */
 }