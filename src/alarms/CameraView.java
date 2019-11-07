package alarms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class CameraView {
	private final int[][] data;
	private CameraDirection cameraDirection;

	private CameraView(int x, int y, CameraDirection direction) {
		data = new int[x][y];
		cameraDirection = direction;

	}

	public static CameraView of(int x, int y, CameraDirection direction) {
		assert (x > 0 && y > 0);
		return new CameraView(x, y, direction);
	}

	public static CameraView of(int x, int y, CameraDirection direction, String inputData) {
		assert (x > 0 && y > 0);
		Objects.requireNonNull(inputData);
		Objects.requireNonNull(direction);

		CameraView returnView = new CameraView(x, y, direction);
		byte[] inputBytes = inputData.getBytes();
		int byteIndex = 0;

		for (int j = 0; j < y; j++) {
			for (int i = 0; i < x; i++) {
				returnView.data[i][j] = inputBytes[byteIndex];
			}
		}

		return returnView;
	}

	public void setValue(int x, int y, int value) {
		data[x][y] = value;
	}

	public int getValue(int x, int y) {
		return data[x][y];
	}

	public CameraDirection getCameraDirection() {
		return this.cameraDirection;
	}

	public int[][] getData() {
		return data;
	}

	public boolean hasFloating() {
		for (int i = 0; i < data.length; i++) {
			for (int j = data.length - 1; j > 0; j--) {
				if (data[i][j] == 0 && data[i][j - 1] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isShiftFrom(CameraView cameraView) {
		assert (cameraView.cameraDirection.equals(cameraDirection));
		assert (cameraView.data.length == data.length);
		assert (cameraView.data[0].length == data[0].length);
		if (this.getClass().equals(cameraView)) {
			return true;
		}
		Iterator<int[][]> iter = possibleShiftedViews(cameraView.data).iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(data)) {
				return true;
			}
		}
		return false;
	}

	// get all possible shifted views
	private List<int[][]> possibleShiftedViews(int[][] view) {
		List<int[][]> views = new ArrayList<>();
		int[][] grid;
		for (int i = 0; i <= view.length * 2; i++) {
			for (int j = 0; j <= view[0].length * 2; j++) {
				grid = emptyGrid(view.length * 3, view[0].length * 3);
				copyArray(view, grid, i, j);
				views.add(arrayRange(grid, view.length, view[0].length, view.length * 2, view[0].length * 2));
			}
		}
		return views;
	}

	private int[][] emptyGrid(int rowNum, int colNum) {
		int[][] grid = new int[rowNum][colNum];
		Arrays.fill(grid, 0);
		return grid;
	}

	// copy 2d array
	private void copyArray(int[][] source, int[][] dest, int startI, int startJ) {
		for (int i = 0; i < source.length; i++) {
			for (int j = 0; j < source[0].length; j++) {
				dest[startI + i][startJ + j] = source[i][j];
			}
		}
	}

	// return a part of a 2d array
	private int[][] arrayRange(int[][] source, int startI, int startJ, int endI, int endJ) {
		int[][] grid = new int[endI - startI][endJ - startJ];
		for (int i = startI; i < endI; i++) {
			grid[i - startI] = Arrays.copyOfRange(source[i], startJ, endJ);
		}
		return grid;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof CameraView && ((CameraView) other).cameraDirection.equals(cameraDirection)
				&& ((CameraView) other).data.equals(data);
	}

}
