import java.util.Objects;

public class State {

	private CameraView view1;
	private CameraView view2;
	private CameraView view3;

	public State(CameraView view1, CameraView view2, CameraView view3) {
		this.view1 = view1;
		this.view2 = view2;
		this.view3 = view3;
	}

	public CameraView getView1() {
		return this.view1;
	}

	public CameraView getView2() {
		return this.view2;
	}

	public CameraView getView3() {
		return this.view3;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof State && ((State) other).view1.equals(view1)
				&& ((State) other).view2.equals(view2) && ((State) other).view3.equals(view3);
	}
}
