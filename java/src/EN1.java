import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class EN1 {
	
	public static double phi(int x, int y, int n) {
		if (x==0 || x==n) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public static double evolution2(int x, int y, int n, double alpha) {
		boolean dague = false;
		Random rand = new Random();
		while (x!=0 && x!=n && y!=0 && y!=n && dague==false) {
			double test = rand.nextDouble();
			if (test<alpha/(n*n)) {
				dague = true;
			}
			else {
				x+= (rand.nextInt(2)*2-1);
				y+=(rand.nextInt(2)*2-1);
			}
		}
		if (dague) {
			return 0;
		}
		else {
			return phi(x,y,n);
		}
	}
	
	public static double solution2(int x, int y, int n, int k, double alpha) {
		double sum = 0;
		for (int i=0; i<k; i++) {
			sum += evolution2(x,y,n,alpha);
		}
		return sum/k;
	}
	
	public static void main(String[] args) throws IOException {
		int n = 400;
		int k = 30;
		double alpha = 10000;
		double[][] mesh_solution = new double[n+1][n+1];
		for (int i=0; i<n+1; i++) {
			System.out.println(i);
			for (int j=0; j<n+1; j++) {
				mesh_solution[i][j] = solution2(i,j,n,k,alpha);
			}
		}
		
		// replace path with your own path ; the CSV file can easily be turned into a graph with matplotlib
		BufferedWriter br = new BufferedWriter(new FileWriter("C:/Users/gecre/Desktop/EN/EN1/res.csv"));
		StringBuilder sb = new StringBuilder();

		// Append strings from array
		for (int i=0; i<n+1; i++) {
			for (int j=0; j<n+1; j++) {
				sb.append(mesh_solution[i][j]);
				sb.append(",");
			}
		}

		br.write(sb.toString());
		br.close();	
	}
}