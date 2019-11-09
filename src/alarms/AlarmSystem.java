package alarms;

import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class AlarmSystem {

	private List<Frame> frames;
	private EnumMap<CameraDirection, CameraView> originalViewMap;
	private EnumMap<CameraDirection, List<CameraView>> possibleShiftsMap;

	// constructor
	private AlarmSystem(List<Frame> frames, EnumMap<CameraDirection, CameraView> original, EnumMap<CameraDirection, List<CameraView>> shifts) {
		this.frames = frames;
		originalViewMap = original;
		possibleShiftsMap = shifts;
	}

	public static AlarmSystem of(List<Frame> frames) {
        Objects.requireNonNull(frames);
        EnumMap<CameraDirection, CameraView> original = frames.get(0).getViewMap();
        EnumMap<CameraDirection, List<CameraView>> shifts = new EnumMap<>(CameraDirection.class);
        EnumSet.allOf(CameraDirection.class).forEach(direction -> shifts.put(direction,
                CameraView.possibleShiftedViews(original.get(direction))));

        return new AlarmSystem(frames, original, shifts);
    }

	public static void main(String[] args){
	    if (args.length != 1){
	        System.out.println("invalid commandline argument");
            System.exit(-1);
        }
	    String filePath = args[0];
	    try{
            InputHandler handler = InputHandler.of(new File(filePath));
            AlarmSystem as = AlarmSystem.of(handler.getFrames());

            for (int i = 0; i < as.getFrames().size(); i++){
                if (as.shouldAlarmSound(i)){
                    System.out.println("true");
                    System.exit(0);
                }
            }
        } catch (IOException e){
	        System.out.println("invalid");
	        System.exit(-1);
        }
	    System.out.println("false");
    }

	// check front, side, top view separately for each frame.
	boolean shouldAlarmSound(int index) {
		for (CameraDirection direction : CameraDirection.values()) { 
			if (isAlarmInDirection(direction, index)) {
				return true;
			}
        }
		return false;
	}

	// check if the view equals to or is a shift from the original one
	private boolean isAlarmInDirection(CameraDirection direction, int index) {
		CameraView view = viewFromFrames(direction, index);
		if (!isOriginalOrShift(direction, view)) {
			return isViewChangePermanent(direction, index);
		}
		return false;
	}

	// check if the view change permanently
	private boolean isViewChangePermanent(CameraDirection direction, int index) {
		CameraView view;
		int i = index + 1;
		while (i < frames.size() && i <= index + 5) {
			view = viewFromFrames(direction, i);
			if (isOriginalOrShift(direction, view)) {
				return false;
			}
			i++;
		}
		return true;
	}

	private CameraView viewFromFrames(CameraDirection cd, int index){
		return frames.get(index).getViewMap().get(cd);
	}

	private boolean isOriginalOrShift(CameraDirection cd, CameraView cv){
		return cv.equals(originalViewMap.get(cd)) || possibleShiftsMap.get(cd).contains(cv);
	}

	private List<Frame> getFrames(){
        return frames;
    }

}
