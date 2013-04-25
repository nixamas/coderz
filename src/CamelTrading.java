import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class CamelTrading {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader fin;
		try {
			fin = new FileReader("cameltrading.txt");
			Scanner src = new Scanner(fin);
			ArrayList<String> lines = new ArrayList<String>();
			src.useDelimiter(" ");
			while (src.hasNext()) {
				String eq = src.nextLine();
//				print(eq);
			    lines.add(eq);
			}
			
			for(String s: lines){
				print("checking equation :: " + s);
				
				findMinimum(s);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private static int findMinimum(String eq){
			//multiply values first
		int tmp = 0;
		for(int i = 0; i < eq.length(); i++){
			char c = eq.charAt(i);
//			print(new String(c));
			if('+' == c){
				print("found a plus @ " + i);
			}
		}
		
//		unsigned long long int res = expression[i++];
//	    for(;expression[i]&&expression[i]!='+';i++)
//	        if(expression[i]!='*')
//	            res  = res * expression[i];
//	    if(!expression[i])
//	        return res;
//	    else
//	        return res + multiplyFirst(i+1,expression);
		return tmp;
	}
	
	
	private static void print(String txt){
		System.out.println(txt);
	}
	
	
}
