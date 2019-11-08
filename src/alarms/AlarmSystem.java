package alarms;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

public class AlarmSystem {

	private List<Frame> frames = new ArrayList<>();
	private EnumMap<CameraDirection, CameraView> originalViewMap;
	private EnumMap<CameraDirection, List<CameraView>> possibleShiftsMap;

	// constructor
	public AlarmSystem(List<Frame> frames) {
		this.frames = frames;
		originalViewMap = frames.get(0).getViewMap();
		EnumSet.allOf(CameraDirection.class).forEach(direction -> possibleShiftsMap.put(direction,
				CameraView.possibleShiftedViews(originalViewMap.get(direction))));
	}

	// check front, side, top view separately for each frame.
	boolean shouldAlarmSound(int index) {
		boolean returnBoolean = false;
		for (CameraDirection direction : CameraDirection.values()) { 
			returnBoolean = returnBoolean || isAlarmInDirection(direction, index);
			if (returnBoolean) {
				break;
			}
        }
		return returnBoolean;
	}

	// check if the view equals to or is a shift from the original one
	private boolean isAlarmInDirection(CameraDirection direction, int index) {
		CameraView view = frames.get(index).getViewMap().get(direction);
		if (!view.equals(originalViewMap.get(direction)) && !possibleShiftsMap.get(direction).contains(view)) {
			return isViewChangePermanent(direction, index);
		}
		return false;
	}

	// check if the view change permanently
	private boolean isViewChangePermanent(CameraDirection direction, int index) {
		CameraView view;
		int i = index + 1;
		while (i < frames.size() && i <= index + 5) {
			view = frames.get(i).getViewMap().get(direction);
			if (view.equals(originalViewMap.get(direction)) || possibleShiftsMap.get(direction).contains(view)) {
				return false;
			}
		}
		return true;
	}
	
	// boolean isCameraOff(alarms.State)
	// checks for any null CameraViews in the state
}
