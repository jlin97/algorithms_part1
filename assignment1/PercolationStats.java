import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private int n_trials;
  private double[] siteFractions;
  public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
  {
   if(n <= 0)
       throw new IllegalArgumentException("Invalid grid size");
      if (trials <= 0)
          throw new IllegalArgumentException("Invalid number of trials");
      n_trials = trials;
   siteFractions = new double[trials];
   for(int i = 0; i < trials; i++){
    Percolation experiment = new Percolation(n);
    double opensites = 0;
    while(!experiment.percolates()){
     int row = StdRandom.uniform(n)+1;
     int col = StdRandom.uniform(n)+1;
     if(!experiment.isOpen(row, col)){
      opensites++;
      experiment.open(row,  col);
     }
    }
    siteFractions[i] = opensites/(n*n);
   }
  }
  public double mean()                          // sample mean of percolation threshold
  {
   return StdStats.mean(siteFractions);
  }
  public double stddev()                        // sample standard deviation of percolation threshold
  {
   if(n_trials == 1)
    return Double.NaN;
   return StdStats.stddev(siteFractions);
  }
  public double confidenceLo()                  // low  endpoint of 95% confidence interval
  {
   return mean() - ((1.96 * stddev()) / Math.sqrt(n_trials));
  }
  public double confidenceHi()                  // high endpoint of 95% confidence interval
  {
   return mean() + ((1.96 * stddev()) / Math.sqrt(n_trials));
  }
  public static void main(String[] args)    // test client (described below)
  {
   if (args.length != 2) {
    return;
      }
   try {
          int gridsize = Integer.parseInt(args[0]);
          int n_trials = Integer.parseInt(args[1]);
             PercolationStats percolationStats = new PercolationStats(gridsize, n_trials);
             System.out.println("mean                    = " + percolationStats.mean());
          System.out.println("stddev                  = " + percolationStats.stddev());
          System.out.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " 
            + percolationStats.confidenceHi());
      } catch (NumberFormatException e) {
          System.out.println("Argument needs to be an integer");
          return;
      } 
  }
}