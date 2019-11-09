package alarms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CameraView {
	private final int[][] data;
	private final int xDim;
	private final int yDim;
	private CameraDirection cameraDirection;

	private CameraView(CameraDirection direction, int[][] inputData) {
		data = inputData;
		xDim = data.length;
		yDim = data[0].length;
		cameraDirection = direction;
	}

	public static CameraView of(CameraDirection direction, int[][] inputData) {
		Objects.requireNonNull(inputData);
		Objects.requireNonNull(direction);
		return new CameraView(direction, inputData);
	}

	public CameraDirection getCameraDirection() {
		return cameraDirection;
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

	// get all possible shifted views
	public static List<CameraView> possibleShiftedViews(CameraView view) {
		Objects.requireNonNull(view);
		List<CameraView> possibleShifts = new ArrayList<>();
		int[][] container;
		for (int i = 0; i <= view.xDim * 2 - 2; i++) {
			for (int j = 0; j <= view.yDim * 2 - 2; j++) {
				container = emptyContainer(view.xDim * 3 - 2, view.yDim * 3 - 2);
				copyViewToLocation(view.data, container, i, j);
				possibleShifts.add(CameraView.of(view.cameraDirection,
						trimmedView(container, view.xDim - 1, view.yDim - 1, view.xDim * 2 - 1, view.yDim * 2 - 1)));
			}
		}
		return possibleShifts;
	}

	// return emptyContainer
	private static int[][] emptyContainer(int rowNum, int colNum) {
		assert rowNum > 0;
		assert colNum > 0;
		int[][] container = new int[rowNum][colNum];
		for (int i = 0; i < container.length; i++) {
			Arrays.fill(container[i], 0);
		}
		return container;
	}

	// copy 2d array
	private static void copyViewToLocation(int[][] source, int[][] dest, int startI, int startJ) {
		Objects.requireNonNull(source);
		Objects.requireNonNull(dest);
		assert 0 <= startI && startI + source.length <= dest.length;
		assert 0 <= startJ && startJ + source[0].length <= dest[0].length;
		for (int i = 0; i < source.length; i++) {
			for (int j = 0; j < source[0].length; j++) {
				dest[startI + i][startJ + j] = source[i][j];
			}
		}
	}

	// return a part of a 2d array
	private static int[][] trimmedView(int[][] source, int startI, int startJ, int endI, int endJ) {
		Objects.requireNonNull(source);
		assert 0 <= startI && startI <= endI && endI < source.length;
		assert 0 <= startJ && startJ <= endJ && endJ < source[0].length;
		int[][] part = new int[endI - startI][endJ - startJ];
		for (int i = startI; i < endI; i++) {
			part[i - startI] = Arrays.copyOfRange(source[i], startJ, endJ);
		}
		return part;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof CameraView && ((CameraView) other).cameraDirection.equals(cameraDirection)
				&& (Arrays.deepEquals(((CameraView) other).data, data));
	}

	// test private methods
	public static class TESTHOOK {
		public static int[][] testEmptyContainer(int rowNum, int colNum) {
			return emptyContainer(rowNum, colNum);
		}

		public static void testCopyViewToLocation(int[][] source, int[][] dest, int startI, int startJ) {
			copyViewToLocation(source, dest, startI, startJ);
		}

		public static int[][] testTrimmedView(int[][] source, int startI, int startJ, int endI, int endJ) {
			return trimmedView(source, startI, startJ, endI, endJ);
		}
	}

}
