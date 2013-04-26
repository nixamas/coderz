import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Testing t = new Testing();
	}
	
	public Testing(){
		FileReader fin;
		try {
			fin = new FileReader("cameltrading.txt");
			Scanner src = new Scanner(fin);
			src.useDelimiter(" ");

			while (src.hasNext()) {
				String eq = src.nextLine();
				Equation tst = new Equation(eq);
			    print( " [ MINIMUM ] :: [ MAXIMUM ]  ----  " + tst.doOp("*") + " :: " + tst.doOp("+") + " " );
			}

			src.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Equation
	 */
	class Equation {
		
		List<Object> objs;
		
		public Equation(String eq){
			objs = new ArrayList<Object>();
			int tmp = -1;
			for( int i = 0;  i < eq.length(); i++ ){
				char c = eq.charAt(i);
				if( Character.isDigit(c) ){
					if( tmp != -1 ){
						int tmp2 = tmp*10;
						tmp = Integer.parseInt( Character.toString(c) );
						objs.add(tmp2 + tmp);
						tmp = -1;
					}else{
						tmp = Integer.parseInt( Character.toString(c) );
					}
				}else{
					if(tmp != -1){
						objs.add(tmp);
						tmp = -1;
					}
					objs.add(Character.toString(c));
				}
			}
			if(tmp != -1){
				objs.add(tmp);
			}
		}
		
		
		public String doOp(String op){
			List<Object> eq = new ArrayList<Object>(objs);
			for(int i = 0; i < eq.size(); i++){
				Object o = eq.get(i);
				if( o instanceof String){
					if( o.equals( op ) ){
						int op2 = (Integer) eq.remove( i + 1 ) ;
						int op1 = (Integer) eq.remove( i - 1 ) ;
						if( op.equals("*") ){
//							print("mult");
							eq.set(i-1, (op1 * op2) );
						}else if( op.equals("+") ){
//							print("add");
							eq.set(i-1, (op1 + op2) );
						}
						i = 0;
//						print("done FIRST operation :::: " + eq.toString());
					}
				}
			}
			for(int i = 0; i < eq.size(); i++){
				Object o = eq.get(i);
				if( o instanceof String){
					if( !o.equals( op ) ){
						int op2 = (Integer) eq.remove( i + 1 ) ;
						int op1 = (Integer) eq.remove( i - 1 ) ;
						if( op.equals("*") ){
//							print("add");
							eq.set(i-1, (op1 + op2) );		//remember do the opposite here
						}else if( op.equals("+") ){
//							print("mult");
							eq.set(i-1, (op1 * op2) );
						}
//						print("done SECOND operation :::: " + eq.toString());
						i = 0;
					}
				}
			}
//			System.out.println(" DONE ADDING " + eq.toString());
			return eq.toString();
		}

	}
	
	private static void print(String txt){
		System.out.println(txt);
	}
}
