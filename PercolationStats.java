import java.util.Random;
public class PercolationStats {
   private double[] counts;
   private int time;
   private Random random; 
   public PercolationStats(int N, int T)  
   {
       counts = new double[T];
       time = T;
       random = new Random();
       for (int i = 0; i < T; i++) {
           Percolation p = new Percolation(N);
         
           int count;
           for (count = 1; !p.percolates(); count++) {
              int j = random.nextInt(N)+1;
              int k = random.nextInt(N)+1;
              if (!p.isOpen(j, k))  
                p.open(j, k);
              else
                  count--;
                }
           counts[i] = (double) count / (double) (N * N);
       }
   }
   
   public double mean()                   
   {
       double sum = 0;
       for (int i = 0; i < time; i++) {
           sum += counts[i];
       }
       return (double) (sum / time);
   }
   public double stddev()                  
   {
       double sum = 0;
       double mean = mean();
       for (int i = 0; i < time; i++) {
           sum += (counts[i] - mean) * (counts[i] - mean);           
       }
       return (double) Math.sqrt(sum / (time - 1));
   }
   public double confidenceLo()
   {   
       double mean = mean();
       double stddev = stddev();
       double a = mean - ((double) 1.96*stddev/Math.sqrt(time));
       return a;
   }
   public double confidenceHi()
   {
        double mean = mean();
       double stddev = stddev();
        double b = mean + ((double) 1.96*stddev/Math.sqrt(time));
        return b;
   }
       
       
   public static void main(String[] args)   // test client, described below
   {
       int N = Integer.parseInt(args[0]);
       int T = Integer.parseInt(args[1]);
       PercolationStats ps = new PercolationStats(N, T);
       double mean = ps.mean();
       double stddev = ps.stddev();
       double Hi = ps.confidenceHi();
       double Lo = ps.confidenceLo();
       System.out.println(Hi);
       System.out.format("mean\t\t\t= %f\n", mean);
       System.out.format("stddev\t\t\t= %f\n", stddev);
       System.out.format("95%% confidence interval\t= %f, %f\n", Lo, Hi);
   }
}


