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

		Iterator<int[][]> iter = possibleShiftedViews(cameraView.data).iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(data)) {
				return true;
			}
		}
		return false;
	}

	// get all possible shifted views
	static List<int[][]> possibleShiftedViews(int[][] checkView) {
		List<int[][]> possibleShifts = new ArrayList<>();
		int[][] container;
		for (int i = 0; i <= checkView.length * 2; i++) {
			for (int j = 0; j <= checkView[0].length * 2; j++) {
				container = emptyContainer(checkView.length * 3, checkView[0].length * 3);
				copyViewToLocation(checkView, container, i, j);
				possibleShifts.add(trimmedView(container, checkView.length, checkView[0].length, checkView.length * 2, checkView[0].length * 2));
			}
		}
		return possibleShifts;
	}

	static int[][] emptyContainer(int rowNum, int colNum) {
		int[][] container = new int[rowNum][colNum];
		for(int i = 0; i < container.length; i++) {
			Arrays.fill(container[i], 0);
		}
		return container;
	}

	// copy 2d array
	static void copyViewToLocation(int[][] source, int[][] dest, int startI, int startJ) {
		for (int i = 0; i < source.length; i++) {
			for (int j = 0; j < source[0].length; j++) {
				dest[startI + i][startJ + j] = source[i][j];
			}
		}
	}

	// return a part of a 2d array
	static int[][] trimmedView(int[][] source, int startI, int startJ, int endI, int endJ) {
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

	public static class CameraViewPrivateMethodsTest {
		private int[][] view = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		private int[][] container = emptyContainer(9, 9);

		@Test
		public void possibleShiftedViewsTest() {
//			Iterator<int[][]> iter = possibleShiftedViews(view).iterator();
//			while (iter.hasNext()) {
//				int[][] temp = iter.next();
//				for(int i = 0; i < temp.length; i++) {
//					for(int j = 0; j < temp.length; j++) {
//						System.out.print(temp[i][j]);
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
		}
		
		@Test
		public void emptyContainerTest() {
			int[][] temp = emptyContainer(9, 9);
			for(int i = 0; i < temp.length; i++) {
				for(int j = 0; j < temp.length; j++) {
					System.out.print(temp[i][j]);
				}
				System.out.println();
			}
			System.out.println();
		}
		
		@Test
		public void copyArrayTest() {
			copyViewToLocation(view, container, 3, 3);
			for(int i = 0; i < container.length; i++) {
				for(int j = 0; j < container.length; j++) {
					System.out.print(container[i][j]);
				}
				System.out.println();
			}
			System.out.println();
			
			int[][] temp = trimmedView(container, 3, 3, 6, 6);
			for(int i = 0; i < temp.length; i++) {
				for(int j = 0; j < temp.length; j++) {
					System.out.print(temp[i][j]);
				}
				System.out.println();
			}
			System.out.println();
		}
		
		@Test
		public void arrayRangeTest() {
			
		}
	}

}
