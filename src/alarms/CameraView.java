package alarms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.junit.Test;

public class CameraView {
	private final int[][] data;
	private final int xDim;
	private final int yDim;
	private CameraDirection cameraDirection;

	private CameraView(int x, int y, CameraDirection direction) {
		data = new int[x][y];
		xDim = x;
		yDim = y;
		cameraDirection = direction;
	}

	public static CameraView of(int x, int y) throws AssertionError{
		assert (x > 0 && y > 0);
		return new CameraView(x, y, null);
	}

	public static CameraView of(int x, int y, CameraDirection direction) throws AssertionError{
		assert (x > 0 && y > 0);
		return new CameraView(x, y, direction);
	}

	public static CameraView of(int x, int y, CameraDirection direction, String inputData) throws NullPointerException, AssertionError {
		assert (x > 0 && y > 0);
		Objects.requireNonNull(inputData);
		Objects.requireNonNull(direction);

		CameraView returnView = new CameraView(x, y, direction);
		byte[] inputBytes = inputData.getBytes();
		int byteIndex = 0;

		for (int j = 0; j < y; j++) {
			for (int i = 0; i < x; i++) {
				returnView.setValue(i, j, inputBytes[byteIndex]);
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

	public int getxDim() {
		return xDim;
	}

	public int getyDim() {
		return yDim;
	}

	boolean hasFloating(){
		assert(cameraDirection != CameraDirection.TOP);
		for (int i = 0; i < xDim; i++) {
			for (int j = yDim - 1; j > 0; j--) {
				if (data[i][j] == 0 && data[i][j - 1] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	boolean isShiftFrom(CameraView cameraView) {

		Iterator<int[][]> iter = possibleShiftedViews(cameraView).iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(data)) {
				return true;
			}
		}
		return false;
	}

	// get all possible shifted views
	static List<int[][]> possibleShiftedViews(CameraView view) {
		List<int[][]> possibleShifts = new ArrayList<>();
		int[][] container;
		for (int i = 0; i <= view.xDim * 2 - 2; i++) {
			for (int j = 0; j <= view.yDim * 2 - 2; j++) {
				container = emptyContainer(view.xDim * 3 - 2, view.yDim * 3 - 2);
				copyViewToLocation(view.data, container, i, j);
				possibleShifts.add(trimmedView(container, view.xDim - 1, view.yDim - 1, view.xDim * 2 - 1, view.yDim * 2 - 1));
			}
		}
		return possibleShifts;
	}

	private static int[][] emptyContainer(int rowNum, int colNum) {
		int[][] container = new int[rowNum][colNum];
		for(int i = 0; i < container.length; i++) {
			Arrays.fill(container[i], 0);
		}
		return container;
	}

	// copy 2d array
	private static void copyViewToLocation(int[][] source, int[][] dest, int startI, int startJ) {
		for (int i = 0; i < source.length; i++) {
			for (int j = 0; j < source[0].length; j++) {
				dest[startI + i][startJ + j] = source[i][j];
			}
		}
	}

	// return a part of a 2d array
	private static int[][] trimmedView(int[][] source, int startI, int startJ, int endI, int endJ) {
		int[][] part = new int[endI - startI][endJ - startJ];
		for (int i = startI; i < endI; i++) {
			part[i - startI] = Arrays.copyOfRange(source[i], startJ, endJ);
		}
		return part;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof CameraView
				&& ((CameraView) other).cameraDirection.equals(cameraDirection)
				&& ((CameraView) other).data.equals(data);
	}

}
