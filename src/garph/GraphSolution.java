package garph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GraphSolution {
    int[] dm = {-1, 0, 0, 1};
    int[] dn = {0, -1, 1, 0};

    public int[][] create(int m, int n){
        int[][] grid = new int[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int rand = Math.random()>0.7 ? 1 : 0;
                grid[i][j] = rand;
            }
        }

        return grid;
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0';
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                            neighbors.add((row-1) * nc + col);
                            grid[row-1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row+1][col] == '1') {
                            neighbors.add((row+1) * nc + col);
                            grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                            neighbors.add(row * nc + col-1);
                            grid[row][col-1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col+1] == '1') {
                            neighbors.add(row * nc + col+1);
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int area = 0;
                if(grid[i][j] == 1){
                    grid[i][j] = 0;
                    area++;
                    queue.offer(new int[]{i, j});
                    while(!queue.isEmpty()){
                        int[] cell = queue.poll();
                        for(int k=0; k<4; k++){
                            int x = cell[0] + dm[k];
                            int y = cell[1] + dn[k];
                            if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
                                grid[x][y] = 0;
                                queue.offer(new int[]{x, y});
                                area++;
                            }
                        }
                    }
                }
                if(area>count)
                    count = area;
            }
        }
        return count;
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int count = 0;
        boolean flag = true;
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid2[i][j] == 1){
                    if(grid1[i][j] == 0){
                        flag = false;
                    }
                    grid2[i][j] = 0;
                    queue.offer(new int[]{i, j});
                    while(!queue.isEmpty()){
                        int[] cell = queue.poll();
                        for(int k=0; k<4; k++){
                            int x = cell[0] + dm[k];
                            int y = cell[1] + dn[k];
                            if(x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1){
                                grid2[x][y] = 0;
                                queue.offer(new int[]{x, y});
                                if(flag && grid1[x][y] == 0){
                                    flag = false;
                                }
                            }
                        }
                    }
                    if(flag){
                        count++;
                    }
                }
                flag = true;
            }
        }
        return count;
    }

    public int maxDistance(int[][] grid) {
        final int INF = 1000000;
        int n = grid.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[i][j] = grid[i][j] == 1 ? 0 : INF;
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    continue;
                }
                if (i - 1 >= 0) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
                }
            }
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 1) {
                    continue;
                }
                if (i + 1 < n) {
                    f[i][j] = Math.min(f[i][j], f[i + 1][j] + 1);
                }
                if (j + 1 < n) {
                    f[i][j] = Math.min(f[i][j], f[i][j + 1] + 1);
                }
            }
        }

        int ans = -1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, f[i][j]);
                }
            }
        }

        if (ans == INF) {
            return -1;
        } else {
            return ans;
        }
    }
}
