// "static void main" must be defined in a public class.
/*
Given a grid with w as width, h as height. Each cell of the grid represents a potential building lot and we will be adding "n" buildings inside this grid. The goal is for the furthest of all lots to be as near as possible to a building. Given an input n, which is the number of buildings to be placed in the lot, determine the building placement to minimize the distance the most distant empty lot is from the building. Movement is restricted to horizontal and vertical i.e. diagonal movement is not required.

For example, w=4, h=4 and n=3. An optimal grid placement sets any lot within two unit distance of the building. The answer for this case is 2.

"0" indicates optimal building placement and in this case the maximal value of all shortest distances to the closest building for each cell is "2".

1 0 1 2

2 1 2 1

1 0 1 0

2 1 2 1
*/
class Main {

    //Optimal Placement of Buildings in a grid

    //Time Complexity: O(H*W*((H*W)Pn)
    //Space Complexity: O(H*W)

    int minDistance;

    public int findMinDistance(int H, int W, int n){

        minDistance = Integer.MAX_VALUE;                        //to store the minimum distance

        int[][] grid = new int[H][W];                           //to keep track of the buildings placement and helps to get the minimum distance as well

        for(int i=0; i<H; i++){                                 //iterate through grid and assign all the values to -1
            for(int j=0; j<W; j++){
                grid[i][j] = -1;
            }
        }

        backtrack(grid, 0, 0, n, H, W);                     //call the backtrack function on 0,0

        return minDistance;                                 //return the minimum distance
    }

    private void bfs(int[][] grid, int H, int W){

        Queue<int[]> queue = new LinkedList<>();                        //create a queue to store the building location and helps to get the maximum distance from those building as well

        boolean[][] visited = new boolean[H][W];                        //helps to keep track of the visited locations

        for(int i=0; i<H; i++){                                         //iterate from grid
            for(int j=0; j<W; j++){

                if(grid[i][j] != -1){                                   //check if that location has building or not
                    queue.add(new int[]{i,j});                          //if that location has building then, add that location into queue
                    visited[i][j] = true;                               //mark that location as visited
                }
            }
        }

        int[][] directions = new int[][]{                               //helps to give directions
                {0, 1}, {1, 0}, {-1, 0}, {0, -1}
        };

        int distance = 0;                                               //to store the maximum distance from the buildings

        while(!queue.isEmpty()){                                        //iterate till queue is empty

            int size = queue.size();                                    //get the size of the queue

            for(int i=0; i<size; i++){                                  //iterate till i reaches to size

                int[] current = queue.poll();                           //get the current location

                for(int[] dir: directions){                             //iterate directions array

                    int nr = current[0] + dir[0];                       //get the new row
                    int nc = current[1] + dir[1];                       //get the new column

                    if(nr >=0 && nc >=0 && nr<H && nc<W && !visited[nr][nc]){           //check if the newRow and newColumn is in a grid and it's not visited earlier
                        queue.add(new int[]{nr, nc});                   //if all the conditions are satisfied, then add it into the queue and mark that location as visited
                        visited[nr][nc] = true;
                    }
                }
            }
            distance++;                                             //increament the distance , as we iterate till size and we added all neighbouring location in a queue to reach that location we need one more distance
        }
        minDistance = Math.min(minDistance, distance - 1);          //take the minimum between minDistance and distance -1(as we take one more step in while loop) and assign it to the minDistance
    }

    private void backtrack(int[][] grid, int row, int column, int remainingBuilding, int H, int W){

        //base
        if(remainingBuilding == 0){                                     //check if the remainingBuilding is 0 or not
            bfs(grid, H, W);                                            //if it's zero means we placed all the building is a grid, now we have to get the maximum distance from that buildings, so we call the bfs function on that grid, and return from here
            return;
        }

        if(column == W){                                                //check if current column value is W or not
            row++;                                                      //if it is W, means we placed the last building on end of the column, so we have to increament the row and reset the column value to 0
            column = 0;
        }

        //logic

        for(int i=row; i<H; i++){                                   //iterate from current row to H
            for(int j=column; j<W; j++){                            //iterate from current column to W

                //action
                grid[i][j] = 0;                                     //place the building

                //recurse
                backtrack(grid, i, j+1, remainingBuilding-1, H, W);         //call the recursive backtrack function on i, j+1 and remainingBuilding -1 as we placed one building

                //backtrack
                grid[i][j] = -1;                                //backtrack the earlier action
            }
            column = 0;                                         //as we iterating over one row, we need to reset the column to 0
        }
    }

    public static void main(String[] args) {
        // System.out.println("Hello World!");
        Main obj = new Main();

        System.out.println("Optimal Placement of Buildings in a grid:");
        System.out.print("(4,4,1) => ");
        System.out.println(obj.findMinDistance(4,4,1));
        System.out.print("(4,4,2) => ");
        System.out.println(obj.findMinDistance(4,4,2));
        System.out.print("(4,4,3) => ");
        System.out.println(obj.findMinDistance(4,4,3));
        System.out.print("(3,2,1) => ");
        System.out.println(obj.findMinDistance(3,2,1));
    }
}

public class OptimalPlacementOfBuildingsInAGridS30Backtracking4 {
}
