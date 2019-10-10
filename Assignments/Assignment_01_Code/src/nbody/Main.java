package nbody;


public class Main {
	public static void main(String[] args) {
		NBodySystem bodies = new NBodySystem(NBodySystem.DEFAULT_SIZE, 1L);
		System.out.printf("%.9f\n", bodies.energy());
		for (int i = 0; i < NBodySystem.DEFAULT_ITERATIONS; ++i) {
			bodies.advance(0.01);
			System.out.printf("%.9f\n", bodies.energy());
		}
	}
}
