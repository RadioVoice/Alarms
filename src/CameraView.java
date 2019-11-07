public class CameraView {
    
	private CameraDirection cameraDirection;
	private int[][] view;
	
	public CameraView(CameraDirection cameraDirection, int[][] view) {
		this.cameraDirection = cameraDirection;
		this.view = view;
	}
	
	public CameraDirection getCameraDirection() {
		return this.cameraDirection;
	}
	
	public int[][] getView(){
		return this.view;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof CameraView && ((CameraView) other).cameraDirection.equals(cameraDirection)
				&& ((CameraView) other).view.equals(view);
	}
}
