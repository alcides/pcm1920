import java.util.Random;

public class Pi {
	public static void main(String[] args) {
		int inside = 0;
		int total = 10000000;
		
		Random r = new Random();
		for(int i=0; i<total; i++) {
			double x = r.nextDouble();
			double y = r.nextDouble();
			if (Math.pow(x, 2) + Math.pow(y, 2) < 1) {
				inside++;
			}
		}
		
		double pi = 4.0 * inside / total;
		System.out.println("PI=" + pi);
	}
}
