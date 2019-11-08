package alarms;

import java.util.EnumMap;

public class State {

	private EnumMap<CameraDirection, CameraView> viewMap;

	private State(CameraView front, CameraView side, CameraView top) {
		viewMap.put(CameraDirection.FRONT, front);
		viewMap.put(CameraDirection.SIDE, side);
		viewMap.put(CameraDirection.TOP, top);
	}

	public static State of(CameraView front, CameraView side, CameraView top) {
		return new State(front, side, top);
	}

	public EnumMap<CameraDirection, CameraView> getViewMap() {
		return viewMap;
	}
}
