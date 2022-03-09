package main;

import java.util.*;

public class Solution{

    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length-1;
        int result = height[i]>height[j]?height[j]*(j-i):height[i]*(j-i);
        int temp = 0;
        while (j-i>1){
            int left, right;
            left = height[i]>height[j-1]? height[j-1]*(j-i-1):height[i]*(j-i-1);
            right = height[i+1]>height[j]? height[j]*(j-i-1):height[i+1]*(j-i-1);
            if(left<right){
                i = i + 1;
                if(temp<right){
                    temp = right;
                }
            } else {
                if(left==right){
                    int[] dest = new int[j-i];
                    System.arraycopy(height, i+1, dest, 0, j-i);
                    int a = maxArea(dest);
                    if(a>temp){
                        temp = a;
                    }
                }
                j = j - 1;
                if (temp < left) {
                    temp = left;
                }
            }
        }
        return result;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int x=0, y=0;
        while(x<matrix.length&&y<matrix[0].length){
            if (matrix[x][y]==target)
                return true;
            else if (matrix[x][y]<target){
                x++;
                if(x==matrix.length){
                    x=0;
                    y++;
                }
            } else {
                x--;
                y++;
            }
        }
        return false;
    }

    public int lengthOfLongestSubstring(String s) {
        int i=1, j=0;
        int result = 0;
        if(s.length()<2){
            return s.length();
        }
        while(i<s.length()){
            String window = s.substring(j,i);
            char ch = s.charAt(i);
            int delta = window.indexOf(ch);
            if(delta!=-1)
                j = j+delta+1;
            i = i+1;
            if(result<i-j) result = i-j;
        }
        return result;
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        dp[0] = 0;
        for(int i=1; i<amount+1; i++){
            int res = amount+1;
            for (int coin : coins) {
                if (i - coin < 0) continue;
                res = Math.min(res, 1 + dp[i - coin]);
            }
            if(res == amount+1){
                dp[i] = amount;
            } else {
                dp[i] = res;
            }
        }
        if(dp[amount] == amount+1) return -1;
        return dp[amount];
    }

    public int hammingDistance(int x, int y) {
        int max = Math.max(x, y);
        int count = 0;
        while((max = max/2) != 0){
            Integer a = x%2;
            Integer b = y%2;
            if(!a.equals(b))
                count ++;
            x = x/2;
            y = y/2;
        }
        return count;
    }
}
