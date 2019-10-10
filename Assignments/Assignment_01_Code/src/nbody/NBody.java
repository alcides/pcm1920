package nbody;

import java.util.Random;

public class NBody {

	public double x;
	public double y;
	public double z;
	public double vx;
	public double vy;
	public double vz;
	public double mass;

	public NBody(Random r) {
		x = r.nextDouble();
		y = r.nextDouble();
		z = r.nextDouble();
		vx = r.nextDouble();
		vy = r.nextDouble();
		vz = r.nextDouble();
		mass = r.nextDouble();

	}

	NBody offsetMomentum(double px, double py, double pz) {
		vx = -px / NBodySystem.SOLAR_MASS;
		vy = -py / NBodySystem.SOLAR_MASS;
		vz = -pz / NBodySystem.SOLAR_MASS;
		return this;
	}

}
