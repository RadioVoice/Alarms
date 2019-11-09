package alarms;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.experimental.runners.Enclosed;

@RunWith(Enclosed.class)
public class CameraViewTest {

	public static class OfTest {
		private CameraDirection direction = CameraDirection.FRONT;
		private int[][] inputData = { { 1, 1 }, { 1, 1 } };

		// direction is null
		@Test(expected = NullPointerException.class)
		public void badData1() {
			CameraView.of(null, inputData);
		}

		// inputData is null
		@Test(expected = NullPointerException.class)
		public void badData2() {
			CameraView.of(direction, null);
		}

		// good data, code coverage
		@Test
		public void goodData() {
			CameraView view = CameraView.of(direction, inputData);
			Assert.assertEquals(2, view.getxDim());
			Assert.assertEquals(2, view.getyDim());
			Assert.assertEquals(CameraDirection.FRONT, view.getCameraDirection());
		}
	}

	public static class PossibleShiftedViewsTest {
		private CameraDirection direction = CameraDirection.FRONT;
		private int[][] inputData1 = { { 1, 1 }, { 1, 1 } };
		private int[][] inputData2 = { { 1 } };
		private CameraView view;

		// camera view is null
		@Test(expected = NullPointerException.class)
		public void badData() {
			CameraView.possibleShiftedViews(null);
		}

		// good data, code coverage
		@Test
		public void goodData() {
			view = CameraView.of(direction, inputData1);
			List<CameraView> possibleViews = CameraView.possibleShiftedViews(view);
			Assert.assertEquals(9, possibleViews.size());
            Assert.assertEquals(view, possibleViews.get(4));
		}

		// boundary test, when dimension = 1
		@Test
		public void boundaryTest() {
			view = CameraView.of(direction, inputData2);
			List<CameraView> possibleViews = CameraView.possibleShiftedViews(view);
			Assert.assertEquals(1, possibleViews.size());
            Assert.assertEquals(view, possibleViews.get(0));
		}
	}

	public static class EqualsObjectTest {
		private CameraDirection direction1 = CameraDirection.FRONT;
		private CameraDirection direction2 = CameraDirection.SIDE;
		private int[][] inputData1 = { { 1, 1 }, { 1, 1 } };
		private int[][] inputData2 = { { 1, 1 }, { 1, 1 } };
		private int[][] inputData3 = { { 1 }, { 1 } };
		private CameraView view1 = CameraView.of(direction1, inputData1);
		private CameraView view2;

		// bad data, input is null
		@Test
		public void badData1() {
            Assert.assertNotEquals(null, view1);
		}

		// bad data, input is not a CameraView object
		@Test
		public void badData2() {
            Assert.assertNotEquals(1, view1);
		}

		// good data, code coverage
		@Test
		public void goodData() {
			view2 = CameraView.of(direction1, inputData2);
            Assert.assertEquals(view1, view2);
		}

		// branch coverage
		@Test
		public void branchCoverage1() {
			view2 = CameraView.of(direction2, inputData2);
            Assert.assertNotEquals(view1, view2);
		}

		// branch coverage
		@Test
		public void branchCoverage2() {
			view2 = CameraView.of(direction1, inputData3);
            Assert.assertNotEquals(view1, view2);
		}
	}

	public static class EmptyContainerTest {

		// bad data, xDim is negative
		@Test(expected = AssertionError.class)
		public void badData1() {
			CameraView.TESTHOOK.testEmptyContainer(-1, 1);
		}

		// bad data, xDim is 0
		@Test(expected = AssertionError.class)
		public void badData2() {
			CameraView.TESTHOOK.testEmptyContainer(0, 1);
		}

		// bad data, yDim is negative
		@Test(expected = AssertionError.class)
		public void badData3() {
			CameraView.TESTHOOK.testEmptyContainer(1, -1);
		}

		// bad data, yDim is 0
		@Test(expected = AssertionError.class)
		public void badData4() {
			CameraView.TESTHOOK.testEmptyContainer(1, 0);
		}

		// good data, code coverage
		@Test
		public void goodData() {
			int[][] emptyContainer = { { 0, 0 }, { 0, 0 } };
			Assert.assertTrue(Arrays.deepEquals(emptyContainer, CameraView.TESTHOOK.testEmptyContainer(2, 2)));
		}

		// boundary test, when dimension = 1
		@Test
		public void boundaryTest() {
			int[][] emptyContainer = { { 0 } };
			Assert.assertTrue(Arrays.deepEquals(emptyContainer, CameraView.TESTHOOK.testEmptyContainer(1, 1)));
		}
	}

}
