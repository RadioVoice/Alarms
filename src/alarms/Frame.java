package alarms;

import java.util.EnumMap;

public class Frame {

	private EnumMap<CameraDirection, CameraView> viewMap;

	private Frame(EnumMap<CameraDirection, CameraView> viewMap) {
		this.viewMap = viewMap;
	}

	public static Frame of(CameraView front, CameraView side, CameraView top) {
		EnumMap<CameraDirection, CameraView> viewMap = new EnumMap<>(CameraDirection.class);
		viewMap.put(CameraDirection.FRONT, front);
		viewMap.put(CameraDirection.SIDE, side);
		viewMap.put(CameraDirection.TOP, top);
		return new Frame(viewMap);
	}

	public EnumMap<CameraDirection, CameraView> getViewMap() {
		return viewMap;
	}
}
