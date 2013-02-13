public class Percolation
{
  private boolean[][] grid;
  private int dim;
  private WeightedQuickUnionUF connections;

  
  public Percolation(int N)        // create N-by-N grid, with all sites blocked
   {
    grid = new boolean[N][N];
    dim = N;
    connections = new WeightedQuickUnionUF(N * N);
  
    for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            
                grid[i][j] = false;
             
             }
         }
    }
    
 public void open(int i, int j)        
    {
           grid[i - 1][j - 1] = true;
           if (j - 2 >= 0 && isOpen(i, j - 1))  // left
           {
                connections.union((i - 1) * dim + j - 1, (i - 1) * dim + j - 2);
           }
           if (j < dim && isOpen(i, j + 1)) // right
           {
               connections.union((i - 1) * dim + j - 1, (i - 1) * dim + j);
           }
           if (i - 2 >= 0 && isOpen(i - 1, j)) // up
           {
               connections.union((i - 2) * dim + j - 1, (i - 1) * dim + j - 1); 
           }
           if (i < dim && isOpen(i + 1, j)) // down
           {
               connections.union((i - 1) * dim + j- 1, i * dim + j - 1);
           }
    }
 

     
 public boolean isOpen(int i, int j)             // is site (row i, column j) open?
 {
     return (grid[i - 1][j - 1]);
 }

 public boolean isFull(int i, int j)           // is site (row i, column j) full?
  {
       if (i == 1 && isOpen(i, j))
          return true;  
      
       for (int k = 0; k < dim; k++) 
          {
                if (connections.connected((i - 1) * dim + j - 1, k) && isOpen(i, j))
                   return true;
                   continue;
           }
      
       return false;
  }
 
 public boolean percolates()        // does the system percolate?
  {
     for (int k = 0; k < dim; k++)
         for (int l = dim * (dim - 1); l < dim * dim; l++){
           if (connections.connected(k, l))
                   return true;
               }
     return false;
 }
  
}
