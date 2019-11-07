package alarms;

public class State {

	private CameraView frontView;
	private CameraView sideView;
	private CameraView topView;

	private State(CameraView front, CameraView side, CameraView top) {
		this.frontView = front;
		this.sideView = side;
		this.topView = top;
	}

	public static State of(CameraView front, CameraView side, CameraView top) {
		return new State(front, side, top);
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

	public boolean hasFloating() {
		return frontView.hasFloating() || sideView.hasFloating();
	}

	boolean isShiftFrom(State state) {
		return frontView.isShiftFrom(state.frontView)
				&& sideView.isShiftFrom(state.sideView)
				&& topView.isShiftFrom(state.topView);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof State
				&& ((State) other).frontView.equals(frontView)
				&& ((State) other).sideView.equals(sideView)
				&& ((State) other).topView.equals(topView);
	}
}
