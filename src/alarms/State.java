package alarms;

public class State {

	private CameraView frontView;
	private CameraView sideView;
	private CameraView topView;

	public State(CameraView front, CameraView side, CameraView top) {
		this.frontView = front;
		this.sideView = side;
		this.topView = top;
	}

	public CameraView getFrontView() {
		return this.frontView;
	}

	public CameraView getSideView() {
		return this.sideView;
	}

	public CameraView getTopView() {
		return this.topView;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof State &&
				((State) other).frontView.equals(frontView)	&&
				((State) other).sideView.equals(sideView) &&
				((State) other).topView.equals(topView);
	}
}
