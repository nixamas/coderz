import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class EulerProblem {

	static int runningSum;
	static ArrayList<String> path = new ArrayList<String>();

	public static void main(String[] args) {
		try{
			FileReader fin = new FileReader("eulerproblem.txt");
			Scanner src = new Scanner(fin);
			ArrayList<String> lines = new ArrayList<String>();
			src.useDelimiter(" ");
			while (src.hasNext()) {
			    lines.add(src.nextLine());
			}
			src.close();
			String[] lineArray = new String[lines.size()];
			lines.toArray(lineArray);
			path.add(0, lineArray[0]);
			runningSum = 0;
			for(int rowIndex = 0; rowIndex < lineArray.length-1; rowIndex++){
				String row = lineArray[rowIndex];
				String nextRow = "";
				if(rowIndex <= lineArray.length){ nextRow = lineArray[rowIndex+1]; } //get next row if it exist
				System.out.println(row);
				String[] nums = row.split(" ");	//split curr row into its numbers
				String[] nrNums = nextRow.split(" ");	//split next row into its numbers
				int rowMax = 0, nrMax = 0;
				int prevMax = Integer.parseInt(path.get(rowIndex));
				System.out.println("PREVIOUS MAX :: " + prevMax);
				for(int colIndex = 0; colIndex < nums.length; colIndex++){
					int num = Integer.parseInt(nums[colIndex]);
					int nrNum1 = 0, nrNum2 = 0;
					int prevMaxIdx = 0;
					nrNum1 = Integer.parseInt(nrNums[colIndex]);
					if( (colIndex+1) <= nums.length ) { nrNum2 = Integer.parseInt(nrNums[colIndex + 1]); }
//					rowMax = Math.max(num, rowMax);
					System.err.println(num + " :: " + nrNum1 + "--" + nrNum2);
					int MAX = Math.max((num + nrNum1),(num + nrNum2));
					rowMax = MAX - num;
//					System.out.println("MAX::::::::" + MAX);
					
				}
				runningSum += rowMax;
				path.add(rowIndex+1, Integer.toString(rowMax));
			}
			for( String s : path){
				System.out.print("[" + s + "] ");
			}
			System.out.println("");
			System.out.println("done");
		}catch(Exception e){
			System.err.println("Error ::: " + e.getLocalizedMessage());
		}
	}
	
	private int[][] createTable() throws FileNotFoundException{
		int table[][] = {{},{}};
		FileReader fin = new FileReader("eulerproblem.txt");
		Scanner src = new Scanner(fin);
		ArrayList<String> lines = new ArrayList<String>();
		src.useDelimiter(" ");
		while (src.hasNext()) {
		    lines.add(src.nextLine());
		}
		src.close();

		
		return table;
	}

}
