
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class CamelTrading {
//	private static LinkedList multList = new java.util.LinkedList();
//	private static LinkedList addList = new java.util.LinkedList();

	private static HashMap<String, List<Integer>> ops;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader fin;
		ops = new HashMap<String, List<Integer>>();
		try {
			fin = new FileReader("cameltrading.txt");
			Scanner src = new Scanner(fin);
			ArrayList<String> lines = new ArrayList<String>();
			src.useDelimiter(" ");
			int lineIdx = 0;
			while (src.hasNext()) {
				String eq = src.nextLine();
//				readForOps(eq, "*", ops, lineIdx);
//				readForOps(eq, "+", ops, lineIdx);
				dualReadForOps(eq, ops, lineIdx);
				print(Integer.toString(lineIdx) + " ::::: " +eq);
			    lines.add(eq);
			    lineIdx++;
			}
			System.out.println(ops);
			for(int i = 0; i < lineIdx; i++){
				doOps(lines, i, "*", "+");
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void doOps(ArrayList<String> eqs, int lineIdx, String firstOp, String scndOp){
		List<Integer> frstOpsPos = ops.get(firstOp + Integer.toString(lineIdx));
		List<Integer> othrOpsOrigPos = ops.get(scndOp + Integer.toString(lineIdx));
		List<Integer> origPos = ops.get("ALLOPS" + Integer.toString(lineIdx));
		System.out.println("MULT OPS ::: "+frstOpsPos);
		System.out.println("ADD OPS ::: "+othrOpsOrigPos);
		System.out.println("ALL OPS ::: "+origPos);
		String eq = eqs.get(lineIdx);
		for(int i = 0; i < origPos.size(); i++){
			
		}
	}
	
	
	
	
	private static void readForOps(String str, String op, HashMap map, int lineIdx){
		int idx = str.indexOf(op), cnt = 0;
		List<Integer> arry = new ArrayList<Integer>();
		while(idx >= 0){
//			arry.add(cnt, arg1)
			arry.add(idx);
			print("INDEX FOUND " + op + " @ "+Integer.toString(idx));
			idx = str.indexOf(op, idx+1);
		}
		map.put(op + Integer.toString(lineIdx), arry);
	}
	
	private static void dualReadForOps(String str, HashMap map, int lineIdx){
		String opAdd = "+";
		String opMul = "*";
		int idxAdd = str.indexOf(opAdd);
		int idxMul = str.indexOf(opMul);
		List<Integer> arryAdd = new ArrayList<Integer>();
		List<Integer> arryMul = new ArrayList<Integer>();
		List<Integer> arry = new ArrayList<Integer>();
		while(idxAdd >= 0 || idxMul >= 0){
//			arry.add(cnt, arg1)
			if( idxAdd != -1){
				arryAdd.add(idxAdd);
				print("INDEX FOUND " + opAdd + " @ "+Integer.toString(idxAdd));
			}
			if( idxMul != -1){
				arryMul.add(idxMul);
				print("INDEX FOUND " + opMul + " @ "+Integer.toString(idxMul));
			}
			if(idxAdd >= 0 && idxMul >= 0){
				if( idxAdd > idxMul){
					arry.add(idxMul);
					arry.add(idxAdd);
				}else if( idxAdd < idxMul ){
					arry.add(idxAdd);
					arry.add(idxMul);
				}
			}else if(idxAdd > 0 && idxMul == -1){
				if( !arry.isEmpty() && arry.get(arry.size() - 1) > idxAdd){
					arry.add( arry.set(arry.size() - 1, idxAdd) );
				}else{
					arry.add(idxAdd);
				}
			}else if(idxAdd == -1 && idxMul > 0){
				if( !arry.isEmpty() && arry.get(arry.size() - 1) > idxMul){
					arry.add( arry.set(arry.size() - 1, idxMul) );
				}else{
					arry.add(idxMul);
				}
			}
			if( idxAdd != -1){
				idxAdd = str.indexOf(opAdd, idxAdd+1);
			}
			if( idxMul != -1){
				idxMul = str.indexOf(opMul, idxMul+1);
			}
		}
		map.put(opAdd + Integer.toString(lineIdx), arryAdd);
		map.put(opMul + Integer.toString(lineIdx), arryMul);
		map.put("ALLOPS" + Integer.toString(lineIdx), arry);
	}
	
	private static void print(String txt){
		System.out.println(txt);
	}
	
	
}
