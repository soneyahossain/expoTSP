/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expotsp;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author soneya
 */
public class ExpoTSP {

        int numOfpoints;
        int [][] coordinate;
        double[][] weight;
        int []parent;
        int permutedArray[][];
        int total_permutation;
        int []sequence;
        int counter;
        double cost_array[];


    public ExpoTSP(int N, Scanner input){
        numOfpoints=N;
        weight= new double [N][N];
        coordinate=new int [N][2];
        counter=0;
        parent=new int [N];
        sequence=new int [N-1];
        input(input);
        total_permutation= Factorial(N-1);
        permutedArray=new int[total_permutation][N+1];
        cost_array= new double[total_permutation];
        
    }
    
    
    public int Factorial(int n)
    {
	if (n == 0) {
            return 1;
        }
	else {
            return n * Factorial(n-1);
        }
     }
   private void input(Scanner input)
   {
       String in;
       
       for (int i=-1;i<numOfpoints+1;i++)
       {
            in = input.nextLine();
       
            StringTokenizer strToken = new StringTokenizer(in);
            int count = strToken.countTokens();
         
            for(int x = 0;x < count;x++){
                  coordinate[i][x] = Integer.parseInt((String)strToken.nextElement());
                  
            }
         
       }
   }
    void calculate_distance()
    {
        for (int i=0;i<numOfpoints;i++)
        {
            for(int j=0;j<numOfpoints;j++)
            {
                if(i==j) {
                    weight[i][j]=0;
                }
                else 
                {
                    int x=(coordinate[i][0]-coordinate[j][0]);
                    int y=(coordinate[i][1]-coordinate[j][1]);
                    int d= (x*x)+(y*y);
                    double c =Math.sqrt(d);
                    c=ExpoTSP.round(c, 2);
                    weight[i][j]=c;
                }
            }
        }
    }
    public static double round(double value, int places) {
    if (places < 0) {
            throw new IllegalArgumentException();
        }

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
}
    
    
    void print_parent()
    {
        for (int j = 0; j < numOfpoints; j++) 
       {
           System.out.println(parent[j]);
       }
    }
    void print_sequence()
    {
        for (int j = 0; j < numOfpoints-1; j++) 
       {
           System.out.println(sequence[j]);
       }
    }
    
    void print_cost()
    {
        for (int j = 0; j < total_permutation; j++) 
       {
           System.out.println(cost_array[j]);
       }
    }
  public void print_array()
 { 
     
     for (int i = 0; i < numOfpoints; i++) 
     {
            
            for (int j = 0; j < numOfpoints; j++) 
            {
                System.out.print(weight[i][j]+" ");

            }
            System.out.println(" ");
     }
                 
 }
public void print_array1()
 { 
     
     for (int i = 0; i < total_permutation; i++) 
     {
            
            for (int j = 0; j < numOfpoints+1; j++) 
            {
                System.out.print(permutedArray[i][j]+" ");

            }
            System.out.println();
     }
                 
 }
  void permutation(int l[],int length)
  {
      
      
        if (length == l.length) 
        {
            permutedArray[counter][0]=0;
            for (int i = 0; i < l.length; i++) 
            {
                
                permutedArray[counter][i+1]=l[i];
               
                
            }
            permutedArray[counter][numOfpoints]=0;
            counter++;
          
          
        } 
        else 
        {
            for (int i = length; i < l.length; i++) 
            {
                int temp = l[length];
                l[length] = l[i];
                l[i] = temp;
 
                permutation(l, length + 1);
 
                temp = l[length];
                l[length] = l[i];
                l[i] = temp;
            }
        }
      
      
  }
  void calculate_cost()
  {
    for(int j=0;j < total_permutation; j++)
    {
      double cost=0;
         
      for(int i=0;i < numOfpoints; i++){
          cost=cost+weight[permutedArray[j][i]][permutedArray[j][i+1]];
      }
      cost_array[j]=ExpoTSP.round(cost,2);
      
    }
  }
  
     void sort_cost()
     {
            double min_cost=1000000000;
            int index=-1;
            for(int i=0;i < cost_array.length; i++)
            {
                 if(cost_array[i]<min_cost)
                 {
                     min_cost=cost_array[i];
                     index=i;
                 }
            }
       System.out.println(index);
       System.out.println("min cost="+min_cost);
      
       System.out.println();
            for(int i=0;i < numOfpoints+1; i++)
            {
                
                     
                System.out.print(permutedArray[index][i]+" ");
                 
            }
             System.out.println();
      
    }
  
 
    public static void main(String[] args) throws FileNotFoundException, IOException {
        final int sta_Time =(int) System.currentTimeMillis();
        Scanner input = new Scanner(new File("input_10.txt"));
        int data_size = input.nextInt();
       
     
        
        ExpoTSP instance= new ExpoTSP(data_size,input);
        instance.calculate_distance();
        instance.print_array();
         
       for (int j = 0; j < instance.numOfpoints-1; j++) 
       {
           instance.sequence[j]=j+1;
          
       }
        //instance.print_sequence();
        instance.permutation(instance.sequence,0);
        //System.out.println(instance.total_permutation);
        instance.print_array1();
        instance.calculate_cost();
        instance.print_cost();
        instance.sort_cost();
        final int end_Time =(int) System.currentTimeMillis();
        int duration=end_Time-sta_Time;
        
        System.out.println("exectution time : "+duration+" millisecond");  
        File dir=new File(".");
        String location= dir.getCanonicalPath()+File.separator+ "Expo_1005105.txt";
        FileWriter f=new FileWriter(location,true);
        BufferedWriter b= new BufferedWriter(f);
        b.write(Integer.toString(data_size));
        b.write("  ");
        b.write(Integer.toString(duration));
        b.newLine();
        
        b.close();
        
        
        
        
  
       
       
    }
}
