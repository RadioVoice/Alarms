package alarms;

import java.util.EnumMap;

public class Frame {

	private EnumMap<CameraDirection, CameraView> viewMap;

	private Frame(CameraView front, CameraView side, CameraView top) {
		viewMap.put(CameraDirection.FRONT, front);
		viewMap.put(CameraDirection.SIDE, side);
		viewMap.put(CameraDirection.TOP, top);
	}

	public static Frame of(CameraView front, CameraView side, CameraView top) {
		return new Frame(front, side, top);
	}

	public EnumMap<CameraDirection, CameraView> getViewMap() {
		return viewMap;
	}
}
