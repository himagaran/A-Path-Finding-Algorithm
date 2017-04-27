
import java.util.Random;
import java.util.Scanner;

public class PathFindingOnSquaredGrid {

	public static void show(boolean[][] a, boolean which) {
		int N = a.length;
		StdDraw.setXscale(-1, N);
		;
		StdDraw.setYscale(-1, N);
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				if (a[i][j] == which){
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.filledSquare(j, N - i - 1, .5);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.square(j, N - i - 1, .5);
				}else{
					StdDraw.filledSquare(j, N - i - 1, .5);
				}
			}
			StdDraw.setPenColor(StdDraw.BLACK);
		}
	}

	public static void show(boolean[][] a, boolean which, int x1, int y1, int x2, int y2) {
		int N = a.length;
		StdDraw.setXscale(-1, N);
		;
		StdDraw.setYscale(-1, N);
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (a[i][j] == which)
					if ((i == x1 && j == y1) || (i == x2 && j == y2)) {
						StdDraw.circle(j, N - i - 1, .5);
					} else
						StdDraw.square(j, N - i - 1, .5);
				else
					StdDraw.filledSquare(j, N - i - 1, .5);
	}


	public static boolean[][] random(int N, double p) {
		boolean[][] a = new boolean[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				a[i][j] = StdRandom.bernoulli(p);
		return a;
	}

	
	public static void main(String[] args) {
		
		Random ran = new Random();
		int gridLength = 10;
		double perculation = 0.6;
		boolean[][] randomlyGenMatrix = random(gridLength, perculation);

		StdArrayIO.print(randomlyGenMatrix);
		show(randomlyGenMatrix, true);

		int blockedArrayLength = 0;
		for (int i = 0; i < randomlyGenMatrix.length; i++) {
			for (int j = 0; j < randomlyGenMatrix.length; j++) {
				if (randomlyGenMatrix[i][j] == false) {
					blockedArrayLength++;
				}
			}
		}
		int noOfBlocks = 0;
		int[][] blockkedArray = new int[blockedArrayLength][2];
		for (int i = 0; i < randomlyGenMatrix.length; i++) {
			for (int j = 0; j < randomlyGenMatrix.length; j++) {
				if (randomlyGenMatrix[i][j] == false) {
					blockkedArray[noOfBlocks][0] = i;
					blockkedArray[noOfBlocks][1] = j;
					noOfBlocks++;
				}
			}

		}

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter i for A > ");
		int Ai = scanner.nextInt();

		System.out.println("Enter j for A > ");
		int Aj = scanner.nextInt();

		System.out.println("Enter i for B > ");
		int Bi = scanner.nextInt();

		System.out.println("Enter j for B > ");
		int Bj = scanner.nextInt();
		
		scanner.close();
		
		show(randomlyGenMatrix, true, Ai, Aj, Bi, Bj);

		Astart object = new Astart();
		//for (int k = 0; k < 3; k++) {
			show(randomlyGenMatrix, true);
			String equation = "";
			//if (k == 0) {
				//equation = "manhattan";
//			} else if (k == 1) {
				equation = "chebyshev";
//			} else {
	//	equation = "euclidean";
//			}
			Stopwatch timerFlow = new Stopwatch();
			String[] finalArray = object.myProcess(equation, gridLength, gridLength, Bi, Bj, Ai, Aj, blockkedArray);
			StdOut.println("Elapsed time = " + timerFlow.elapsedTime());
			String path = finalArray[0];
			char[] arrayPath = path.toCharArray();
			for (int i = 0; i < arrayPath.length; i += 2) {
				show(randomlyGenMatrix, Character.getNumericValue(arrayPath[i]),
						Character.getNumericValue(arrayPath[i + 1]));
			}
			show(randomlyGenMatrix, true, Ai, Aj, Bi, Bj);
		}
	//}

	public static void show(boolean[][] a, int x1, int y1) {
		int N = a.length;
		StdDraw.setXscale(-1, N);
		;
		StdDraw.setYscale(-1, N);
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (a[i][j] == true) {
					if ((i == x1 && j == y1)) {
						StdDraw.setPenColor(StdDraw.GREEN);
						StdDraw.filledSquare(j, N - i - 1, .5);
					} else {
						StdDraw.square(j, N - i - 1, .5);
					}
				} else {
					StdDraw.filledSquare(j, N - i - 1, .5);
				}

				StdDraw.setPenColor(StdDraw.BLACK);
			}
		}
	}
}
