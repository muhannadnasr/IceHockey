package eg.edu.alexu.csd.datastructure.iceHockey.cs86;

import java.awt.Point;

public class IceHockey implements IPlayersFinder{
	
	private static int C = 48;
	  
	  private int counter = 0, x_max = 0, x_min = 0, y_min = 0, y_max = 0;
	 
	  private int[][] Scanned;

	
	  public Point[] findPlayers( String[] photo,
	       int team,
	       int threshold) {
		  
		  if(photo.length == 0) {
			  
			  return null;
		  }
	    
	    int pCounter = 0;
	     int m = photo.length, n = photo[0].length();
	     char[][] Array = new char[m][n];
	    for (int i = 0; i < m; i++) {
	      for (int j = 0; j < n; j++) {
	        Array[i][j] = photo[i].charAt(j);
	      }
	    }
	     Scanned = new int[m][n];
	    arrayInitiliaze(Scanned, m, n);
	    Point[] points = new Point[100];
	    for (int index = 0; index < points.length; index++) {
	      points[index] = new Point(0, 0);
	    }
	    for (int i = 0; i < m; i++) {
	      for (int j = 0; j < n; j++) {
	        if (Scanned[i][j] == 1) {
	          continue;
	        }
	        if (Array[i][j] != (char) (team + C)) {
	          continue;
	        }
	        counter++;
	        Scanned[i][j] = 1;
	        x_max = j;
	        x_min = j;
	        y_min = i;
	        y_max = i;
	        checkSpot(i, j, team, Array, m, n);
	        if (((counter * 4) / threshold) >= 1) {
	          points[pCounter].x = x_max + x_min + 1;
	          points[pCounter].y = y_min + y_max + 1;
	          pCounter++;
	        }
	        resetCounters();

	      }
	    }
	    Point[] finals = new Point[pCounter];
	    for (int i = 0; i < finals.length; i++) {
	      finals[i] = new Point(0, 0);
	    }

	    for (int i = 0; i < pCounter; i++) {
	      finals[i] = points[i];
	    }
	    sort(finals);
	    return finals;
	  }
	  
	  

	  public void sort(Point[] finals) {
	    Point temp = new Point();
	    for (int i = 0; i < finals.length; i++) {
	      for (int j = 0; j < finals.length - 1 - i; j++) {
	        if (finals[j].x >= finals[j + 1].x) {
	          if (finals[j].x == finals[j + 1].x) {
	            if (finals[j].y > finals[j + 1].y) {
	              temp = finals[j + 1];
	              finals[j + 1] = finals[j];
	              finals[j] = temp;
	            }
	          } else {
	            temp = finals[j + 1];
	            finals[j + 1] = finals[j];
	            finals[j] = temp;
	          }
	        }
	      }
	    }
	  }

	 
	  public void arrayInitiliaze(final int[][] array,
	      int m,
	      int n) {
	    for (int i = 0; i < m; i++) {
	      for (int j = 0; j < n; j++) {
	        array[i][j] = 0;
	      }
	    }
	  }
	  

	  public void resetCounters() {
	    counter = 0;
	    x_max = 0;
	    x_min = 0;
	    y_min = 0;
	    y_max = 0;
	  }
	  

	  public void checkSpot(int x,
	      int y,
	      int team,
	      char[][] thearray,
	      int m, int n) {
		  
	    for (int i = x - 1; i <= x + 1; i++) {
	      for (int j = y - 1; j <= y + 1; j++) {
	        if (i < 0 | j < 0 | i >= m | j >= n) {
	          continue;
	        }
	        if ((i == x - 1) & (j == y - 1)) {
	          continue;
	        }
	        if ((i == x - 1) & (j == y + 1)) {
	          continue;
	        }
	        if ((i == x + 1) & (j == y - 1)) {
	          continue;
	        }
	        if ((i == x + 1) & (j == y + 1)) {
	          continue;
	        }
	        if (Scanned[i][j] == 1) {
	          continue;
	        }
	        if (thearray[i][j] == (char) (team + C)) {
	          counter++;
	          Scanned[i][j] = 1;
	          if (j < x_max) {
	            x_max = j;
	          }
	          if (j > x_min) {
	        	x_min = j;
	          }
	          if (i > y_min) {
	            y_min = i;
	          }
	          if (i < y_max) {
	            y_max = i;
	          }
	          checkSpot(i, j, team, thearray, m, n);
	        }
	      }
	    }
	  }
	

}
