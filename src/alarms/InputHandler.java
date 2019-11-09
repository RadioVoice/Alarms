package alarms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
    private final int x, y, z;
    private ArrayList<Frame> frames;

    private InputHandler(ArrayList<Frame> frameList, int xDim, int yDim, int zDim) {
        x = xDim;
        y = yDim;
        z = zDim;
        frames = frameList;
    }

    public static InputHandler of(File file) throws IOException {
        int xDim, yDim, zDim;
        ArrayList<Frame> frameList = new ArrayList<Frame>();

        Scanner scan = new Scanner(file);
        requireValidInput(scan);

        xDim = Integer.parseInt(scan.findInLine("\\d"));
        yDim = Integer.parseInt(scan.findInLine("\\d"));
        zDim = Integer.parseInt(scan.findInLine("\\d"));

        while(scan.hasNextLine()) {
            String[] frameSet = scan.nextLine().split(" ");
            CameraView front = CameraView.of(CameraDirection.FRONT, matrixFromString(xDim, zDim, frameSet[0]));
            CameraView side = CameraView.of(CameraDirection.SIDE, matrixFromString(yDim, zDim, frameSet[1]));
            CameraView top = CameraView.of(CameraDirection.TOP, matrixFromString(xDim, yDim, frameSet[2]));
            frameList.add(Frame.of(front, side, top));
        }
        return new InputHandler(frameList, xDim, yDim, zDim);
    }

    static void requireValidInput(Scanner scan) throws IOException {
        String dimensionLine = scan.nextLine();
        try{
            checkDimensionLine(dimensionLine);
            String[] dimensions = dimensionLine.split(" ");
            int x = Integer.parseInt(dimensions[0]);
            int y = Integer.parseInt(dimensions[1]);
            int z = Integer.parseInt(dimensions[2]);

            while (scan.hasNextLine()){
                checkFrameLine(scan.nextLine(), x, y, z);
            }
        } catch (IllegalArgumentException e){
            throw new IOException();
        }
    }

    static void checkDimensionLine(String s) throws IllegalArgumentException{
        Pattern dimLineP = Pattern.compile("\\d+ \\d+ \\d+");
        Pattern zeroOrOne = Pattern.compile("[01]");
        Matcher m = dimLineP.matcher(s);
        if (!m.matches()){
            throw new IllegalArgumentException();
        }
        String[] dimensions = s.split(" ");
        for (String digit: dimensions){
            Matcher m2 = zeroOrOne.matcher(digit);
            if (!m2.matches()){
                throw new IllegalArgumentException();
            }
        }
    }

    static void checkFrameLine(String s, int xDim, int yDim, int zDim) throws IllegalArgumentException{
        Pattern frameLineP = Pattern.compile("[01]+ [01]+ [01]+");
        Matcher m = frameLineP.matcher(s);
        if (!m.matches()){
            throw new IllegalArgumentException();
        }
        String[] views = s.split(" ");
        int[] validSizes = {xDim*zDim, yDim*zDim, xDim*yDim};

        for (int i = 0; i < 3; i++){
            if (views[i].length() != validSizes[i])
                throw new IllegalArgumentException();
        }
    }
    
    // convert a string to a 2D array with given row and column numbers
    private static int[][] matrixFromString(int x, int y, String inputData) {
		Objects.requireNonNull(inputData);
		assert (x > 0 && y > 0);
		assert (inputData.length() == x * y);

		byte[] inputBytes = inputData.getBytes();
		int byteIndex = 0;
		int[][] returnData = new int[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				returnData[i][j] = inputBytes[byteIndex] - '0';
				byteIndex++;
			}
		}

		return returnData;
    }

    ArrayList<Frame> getFrames(){
        return frames;
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

    public class TestHook{
        int[][] testMatrixFromString(int x, int y, String s){
            return matrixFromString(x, y, s);
        }
    }
}
