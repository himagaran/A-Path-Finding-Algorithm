
import java.util.PriorityQueue;

class Cell {

	int heuristics = 0;
	int fCost = 0;
	int i, j;
	Cell parent;

	Cell(int x, int y) {
		this.i = x;
		this.j = y;
	}
}

public class Astart {
	public static final int diagonal_Cost = 14;
	public static final int V_HCOST = 10;

	Cell[][] grid;

	PriorityQueue<Cell> open;

	boolean close[][];
	int sI, sJ;
	int eI, eJ;

	public void setBlockedSquare(int i, int j) {
		grid[i][j] = null;
	}

	public void setStartSquare(int i, int j) {
		sI = i;
		sJ = j;
	}

	public void setEndSquare(int i, int j) {
		eI = i;
		eJ = j;
	}

	void checkAndUpdateCost(Cell currentCell, Cell tempCell, int cost) {
		if (tempCell == null || close[tempCell.i][tempCell.j])
			return;
		int tempFinalCost = tempCell.heuristics + cost;

		boolean isOpen = open.contains(tempCell);
		if (!isOpen || tempFinalCost < tempCell.fCost) {
			tempCell.fCost = tempFinalCost;
			tempCell.parent = currentCell;
			if (!isOpen)
				open.add(tempCell);
		}
	}

	public void aStar() {

		// add the start location to open list.
		open.add(grid[sI][sJ]);

		Cell current;

		while (true) {
			current = open.poll();
			if (current == null)
				break;
			close[current.i][current.j] = true;

			if (current.equals(grid[eI][eJ])) {
				return;
			}

			Cell temp;
			if (current.i - 1 >= 0) {
				temp = grid[current.i - 1][current.j];
				//checkAndUpdateCost(current, temp, current.fCost + V_HCOST);

				if (current.j - 1 >= 0) {
					temp = grid[current.i - 1][current.j - 1];
					checkAndUpdateCost(current, temp, current.fCost + diagonal_Cost);
				}

				if (current.j + 1 < grid[0].length) {
					temp = grid[current.i - 1][current.j + 1];
					checkAndUpdateCost(current, temp, current.fCost + diagonal_Cost);
				}
			}

			if (current.j - 1 >= 0) {
				temp = grid[current.i][current.j - 1];
				checkAndUpdateCost(current, temp, current.fCost + V_HCOST);
			}

			if (current.j + 1 < grid[0].length) {
				temp = grid[current.i][current.j + 1];
				checkAndUpdateCost(current, temp, current.fCost + V_HCOST);
			}

			if (current.i + 1 < grid.length) {
				temp = grid[current.i + 1][current.j];
				//checkAndUpdateCost(current, temp, current.fCost + V_HCOST);

				if (current.j - 1 >= 0) {
					temp = grid[current.i + 1][current.j - 1];
					checkAndUpdateCost(current, temp, current.fCost + diagonal_Cost);
				}

				if (current.j + 1 < grid[0].length) {
					temp = grid[current.i + 1][current.j + 1];
					checkAndUpdateCost(current, temp, current.fCost + diagonal_Cost);
				}
			}
		}
	}

	public String[] myProcess(String method, int x, int y, int si, int sj, int ei, int ej, int[][] blocked) {
		String[] finalArray = new String[2];
		String path = "";
		String finalCost = "";
		// Reset
		grid = new Cell[x][y];
		close = new boolean[x][y];
		open = new PriorityQueue<>((Object o1, Object o2) -> {
			Cell c1 = (Cell) o1;
			Cell c2 = (Cell) o2;

			return c1.fCost < c2.fCost ? -1 : c1.fCost > c2.fCost ? 1 : 0;
		});

		setStartSquare(si, sj);

		setEndSquare(ei, ej);

		for (int i = 0; i < x; ++i) {
			for (int j = 0; j < y; ++j) {
				grid[i][j] = new Cell(i, j);
				if (method.equals("manhattan")) {
					grid[i][j].heuristics = Math.abs(i - eI) + Math.abs(j - ej); // manhattan
				} else if (method.equals("chebyshev")) {
					grid[i][j].heuristics = Math.max(Math.abs(i - eI), Math.abs(j - ej)); // chebyshev
				} else {
					grid[i][j].heuristics = (int) Math.sqrt(((i - eI) ^ 2) + ((j - ej) ^ 2)); // euclidean
				}
			}
		}
		grid[si][sj].fCost = 0;

		for (int i = 0; i < blocked.length; ++i) {
			setBlockedSquare(blocked[i][0], blocked[i][1]);
		}

		aStar();
		int cost = 0;
		if (close[ei][ej]) {

			Cell current = grid[eI][ej];

			while (current.parent != null) {
				path = path + "" + current.i + "" + current.j;
				cost += current.fCost;
				current = current.parent;
				path = path + "" + current.i + "" + current.j;
			}
			finalCost = Integer.toString(cost);
			System.out.println(method + " -> " + finalCost);
			finalArray[0] = path;
			finalArray[1] = finalCost;

			System.out.println();
		} else {
			System.out.println("No possible path");
		}
		return finalArray;
	}

}