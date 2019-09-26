
public class MatrixMult {
	public static void main(String[] args) {
		int M = 100;
		int N = 100;
		int O = 100;
		
		float[][] A = createMatrix(M, N);
		float[][] B = createMatrix(N, O);
		float[][] C = new float[M][O];
		
		for (int i=0; i<M; i++) {
			for (int j=0; j<O; j++) {
				float v = 0;
				for (int k=0; k<N; k++) {
					v += A[i][k] * B[k][j];
				}
				C[i][j] = v;
			}
		}
		
		System.out.println(C[0][0]);
		
		
	}

	private static float[][] createMatrix(int m, int n) {
		float[][] mat = new float[m][n];
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				mat[i][j] = i + j;
			}
		}
		return mat;
	}
}
