package alarms;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.experimental.runners.Enclosed;;

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
			Assert.assertTrue(view.equals(possibleViews.get(4)));
		}

		// boundary test, when dimension = 1
		@Test
		public void boundaryTest() {
			view = CameraView.of(direction, inputData2);
			List<CameraView> possibleViews = CameraView.possibleShiftedViews(view);
			Assert.assertEquals(1, possibleViews.size());
			Assert.assertTrue(view.equals(possibleViews.get(0)));
		}
	}

	@Test
	public void testPossibleShiftedViews() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
