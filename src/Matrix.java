package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Matrix {

    public void jjin(int num){
        System.out.println(num%2);
        if(num>0){
            jjin(num/2);
        }
    }

    public void jin(int num){
        System.out.println();
    }

    public int[][] rotateGrid(int[][] grid, int k) {

        int m= grid.length;
        int n= grid[0].length;

        int layers = Math.min(m,n)/2;

        for(int layer = 0;layer< layers;layer++){
            int top= layer;
            int left = layer;
            int bottom= m - 1 - layer;
            int right =n-1 -layer;

            List<Integer> list = new ArrayList<>();

            for(int j= left;j<=right;j++){
                list.add(grid[top][j]);

            }

            for(int j=top+1;j<=bottom;j++){
                list.add(grid[j][right]);

            }
            for(int j=right-1;j>=left;j--){
                list.add(grid[bottom][j]);
            }

            for(int j=bottom-1;j>top;j--){
                list.add(grid[j][left]);
            }
            int rotate= k% list.size();

            List<Integer> rotatedarr= new ArrayList<>();

            for(int i=0;i< list.size();i++){
                rotatedarr.add(list.get((i+rotate)%list.size()));
            }
            int index=0;

            for(int j= left;j<=right;j++){
                grid[top][j]=rotatedarr.get(index++);
            }

            for(int j=top+1;j<=bottom;j++){
                grid[j][right]=rotatedarr.get(index++);
            }

            for(int j=right-1;j>=left;j--){
                grid[bottom][j]=rotatedarr.get(index++);
            }

            for(int j=bottom-1;j>top;j--){
                grid[j][left]=rotatedarr.get(index++);
            }




        }



        return grid;
    }


    public int minimumEffort(int[][] tasks) {

        int result=tasks[0][1];
        int cussion =0;
        for(int i =0;i< tasks.length;i++){
            for(int j=0;j<tasks[i].length;j++){
                


            }
        }
        return 0;
    }




    public int[] separateDigits(int[] nums) {
        ArrayList<Integer> ar = new ArrayList<>();

        for(int i =nums.length-1;i>= 0;i--){
            while(nums[i]>0){
                int digit =nums[i]%10;
                ar.add(digit);
                nums[i]=nums[i]/10;
            }
        }
        Collections.reverse(ar);
        int[] result = new int[ar.size()];
        for(int i =0;i<result.length;i++){
            result[i]= ar.get(i);
        }

        return result;



    }
}
