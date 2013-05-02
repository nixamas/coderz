import java.util.ArrayList;
import java.util.List;

//10405
public class ProblemThree {
	private static boolean[][] table;
	private static String str1, str2;
	private static List subsets;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		subsets = new ArrayList();
		str1 = "abdsfrabr";
		str2 = "beradsaab";
		table = new boolean[str1.length()][str2.length()];
		
		StringBuilder sb = new StringBuilder();
		
		// ignore case
	    str1 = str1.toLowerCase();
	    str2 = str2.toLowerCase();

	    // java initializes them already with 0
	    int[][] num = new int[str1.length()][str2.length()];
	    int maxlen = 0;
	    int lastSubsBegin = 0;

	    for (int i = 0; i < str1.length(); i++) {
	        for (int j = 0; j < str2.length(); j++) {
	            if (str1.charAt(i) == str2.charAt(j)) {
	                if ((i == 0) || (j == 0))
	                    num[i][j] = 1;
	                else
	                    num[i][j] = 1 + num[i - 1][j - 1];

	                if (num[i][j] > maxlen) {
	                    maxlen = num[i][j];
	                    // generate substring from str1 => i
	                    int thisSubsBegin = i - num[i][j] + 1;
	                    if (lastSubsBegin == thisSubsBegin) {
	                        // if the current LCS is the same as the last time
	                        // this block ran
	                        sb.append(str1.charAt(i));
	                    } else {
	                        // this block resets the string builder if a
	                        // different LCS is found
	                        lastSubsBegin = thisSubsBegin;
	                        sb = new StringBuilder();
	                        sb.append(str1.substring(lastSubsBegin, i + 1));
	                    }
	                }
	            }
	        }
	    }

	    print(sb.toString());
		
		
		
//		for(int i = 0; i < str1.length(); i++){
//			for(int j = 0; j < str1.length(); j++){
//				if(str1.charAt(i) == str2.charAt(j)){
//					print("match found at i="+i+" & j="+j);
//					table[i][j] = true;
//				}else{
//					table[i][j] = false;
//				}
//			}
//		}
//		print("done...");
//		System.out.println(table.toString());
//		boolean rowMatchFound = false;
//		int i = 0, j = 0;
//		while( i < str1.length() && j < str2.length() ){
//			if(str1.charAt(i) == str2.charAt(j)){
//				
//			}
//		}
		
//		for(int i = 0; i < str1.length(); i++){
//			for(int j = 0; j < str1.length(); j++){
//				System.out.println(str1.charAt(i) + " -- " + str2.charAt(j));
//				if( !rowMatchFound && table[i][j] == true){
////					print("FOUND MATCH");
////					System.out.println(str1.charAt(i) + " -- " + str2.charAt(j));
//					rowMatchFound = true;
//				}
//			}
//			rowMatchFound = false;
//		}
	}

	private static void print(String txt){
		System.out.println(txt);
	}
}
