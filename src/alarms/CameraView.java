package alarms;

import java.util.Objects;

public class CameraView {
    private final int[][] data;
    private CameraDirection cameraDirection;


    private CameraView(int x, int y, CameraDirection direction) {
        data = new int[x][y];
        cameraDirection = direction;

    }

    public static CameraView of(int x, int y, CameraDirection direction){
        assert(x>0 && y>0);
        return new CameraView(x, y, direction);
    }

    public static CameraView of(int x, int y, CameraDirection direction, String inputData){
        assert(x>0 && y>0);
        Objects.requireNonNull(inputData);
        Objects.requireNonNull(direction);

        CameraView returnView = new CameraView(x, y, direction);
        byte[] inputBytes = inputData.getBytes();
        int byteIndex = 0;

        for (int j = 0; j < y; j++){
            for (int i = 0; i < x; i++){
                returnView.data[i][j] = inputBytes[byteIndex];
            }
        }

        return returnView;
    }

    public void setValue(int x, int y, int value){
        data[x][y] = value;
    }

    public int getValue(int x, int y){
        return data[x][y];
    }

    public CameraDirection getCameraDirection() {
        return this.cameraDirection;
    }

    public int[][] getData(){
        return data;
    }
    
    public boolean hasFloating() {
    	for (int i = 0; i < data.length; i++) {
    		for (int j = data.length - 1; j > 0; j--) {
    			if (data[i][j] == 0 && data[i][j-1] == 1) {
    				return true;
    			}
    		}
    	}
    	return false;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof CameraView && ((CameraView) other).cameraDirection.equals(cameraDirection)
                && ((CameraView) other).data.equals(data);
    }
}
