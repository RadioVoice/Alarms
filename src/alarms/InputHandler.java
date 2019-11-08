package alarms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class InputHandler {
    private final int x, y, z;
    private ArrayList<State> states;

    private InputHandler(ArrayList<State> stateList, int xDim, int yDim, int zDim) {
        x = xDim;
        y = yDim;
        z = zDim;
        states = stateList;
    }

    public static InputHandler of(File file) throws FileNotFoundException{
        int xDim, yDim, zDim;
        ArrayList<State> stateList = new ArrayList<State>();

        Scanner scan = new Scanner(file);
        requireValidInput(scan);

        xDim = Integer.parseInt(scan.findInLine("\\d"));
        yDim = Integer.parseInt(scan.findInLine("\\d"));
        zDim = Integer.parseInt(scan.findInLine("\\d"));

        while(scan.hasNextLine()) {
            String[] frameSet = scan.nextLine().split(" ");
            int[][] frontData = dataFromInput(xDim, zDim, frameSet[0]);
            int[][] sideData = dataFromInput(yDim, zDim, frameSet[1]);
            int[][] topData = dataFromInput(xDim, yDim, frameSet[2]);
            CameraView front = CameraView.of(CameraDirection.FRONT, frontData);
            CameraView side = CameraView.of(CameraDirection.SIDE, sideData);
            CameraView top = CameraView.of(CameraDirection.TOP, topData);
            stateList.add(State.of(front, side, top));
        }
        return new InputHandler(stateList, xDim, yDim, zDim);
    }
    
    // convert a string to a 2D array with given row and column numbers
    private static int[][] dataFromInput(int x, int y, String inputData) throws NullPointerException, AssertionError, IllegalArgumentException {
		Objects.requireNonNull(x);
		Objects.requireNonNull(y);
		Objects.requireNonNull(inputData);
		assert (x > 0 && y > 0);
		assert (inputData.length() == x * y);

		byte[] inputBytes = inputData.getBytes();
		int byteIndex = 0;
		int[][] returnData = new int[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				checkBytesValidity(inputBytes[byteIndex]);
				returnData[i][j] = inputBytes[byteIndex] - '0';
				byteIndex++;
			}
		}
		
		return returnData;
    }
    
    // check whether inputByte only is 0 or 1
    private static void checkBytesValidity(byte inputByte) {
		if (inputByte != '0' || inputByte != '1') {
			throw new IllegalArgumentException("input must be 0 or 1");
		}
	}

    //boolean checkValidInput()
    //In any of these cases, throw invalid input exception
    //  is there 3 dimensions on the first line and nothing else
    //  are there any negative numbers
    //  does each line after contain 3 "words" separated by spaces?
    //  Does each line only contain 0 and 1?
    //  check size of each view and make sure it matches what it should be (in sets of 3)

    static void requireValidInput(Scanner scan){
        String[] dimensions = scan.nextLine().split(" ");
        //check size of set
        //check for all positive numbers
        while (scan.hasNextLine()){
            String[] views = scan.nextLine().split(" ");
            //check size of set
            //check only 0 or 1 in each string
            //check string [0] is x*z, string[1] is y*z, string [2] is x*y
        }
    }

    ArrayList<State> getStates(){
        return states;
    }
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }
    int getZ(){
        return z;
    }
}
