package main;

import java.util.*;

public class Solution {
    int count = 0;

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int m;
    int n;

    public static void printIndent(int n) {
        for (int i = 0; i < n; i++) {
            System.out.printf("-");
        }
    }

    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int result = height[i] > height[j] ? height[j] * (j - i) : height[i] * (j - i);
        int temp = 0;
        while (j - i > 1) {
            int left, right;
            left = height[i] > height[j - 1] ? height[j - 1] * (j - i - 1) : height[i] * (j - i - 1);
            right = height[i + 1] > height[j] ? height[j] * (j - i - 1) : height[i + 1] * (j - i - 1);
            if (left < right) {
                i = i + 1;
                if (temp < right) {
                    temp = right;
                }
            } else {
                if (left == right) {
                    int[] dest = new int[j - i];
                    System.arraycopy(height, i + 1, dest, 0, j - i);
                    int a = maxArea(dest);
                    if (a > temp) {
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
        int x = 0, y = 0;
        while (x < matrix.length && y < matrix[0].length) {
            if (matrix[x][y] == target)
                return true;
            else if (matrix[x][y] < target) {
                x++;
                if (x == matrix.length) {
                    x = 0;
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
        int i = 1, j = 0;
        int result = 0;
        if (s.length() < 2) {
            return s.length();
        }
        while (i < s.length()) {
            String window = s.substring(j, i);
            char ch = s.charAt(i);
            int delta = window.indexOf(ch);
            if (delta != -1)
                j = j + delta + 1;
            i = i + 1;
            if (result < i - j) result = i - j;
        }
        return result;
    }

    /**
     * Minimum delete. Always used in limited operation situation.
     * @param s1 text1
     * @param s2 text2
     * @return minimum sum of deleted letter's ascii in order to make the two string the same
     */
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = s1.charAt(i - 1) + dp[i - 1][0];
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = s2.charAt(i - 1) + dp[0][i - 1];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }
        return dp[m][n];
    }

    public int longestIncreasingPath(int[][] matrix){
        m = matrix.length;
        n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(dp[i][j] == 0){
                    dp[i][j] = travesal(i, j, dp, matrix);
                }
            }
        }
        int max = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    private int travesal(int i, int j, int[][] dp, int[][] matrix){
        int max = 1;
        for(int k = 0; k < 4; k++){
            int x = i + dx[k];
            int y = j + dy[k];
            if(x >= 0 && x < m && y >= 0 && y < n){
                if(matrix[x][y] > matrix[i][j]){
                    if(dp[x][y] != 0){
                        max = Math.max(max, dp[x][y] + 1);
                    } else {
                        max = Math.max(max, travesal(x, y, dp, matrix) + 1);
                    }
                }
            }
        }
        dp[i][j] = max;
        return max;
    }

    public List<String> generateParenthesis(int n) {
        Set<String> start = new HashSet<>();
        start.add("()");
        char[] par = {'(', ')'};
        for (int i = 1; i < n; i++) {
            Set<String> ret = new HashSet<>();
            int j = 0;
            for (String s : start) {
                StringBuilder temp = new StringBuilder(s);
                ret.add(temp.insert(j, par, 0, 2).toString());
                j++;
            }
            start = ret;
        }
        List<String> list = new ArrayList<String>(start);
        return list;
    }
}
