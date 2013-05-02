
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CamelTrading {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CamelTrading();
	}
	
	public CamelTrading(){
		FileReader fin;
		try {
			fin = new FileReader("cameltrading.txt");
			Scanner src = new Scanner(fin);
			src.useDelimiter(" ");

			while (src.hasNext()) {
				String eq = src.nextLine();	//get equation
				Equation tst = new Equation(eq);	//new equation obj
//			    print( " [ MINIMUM ] :: [ MAXIMUM ]  ----  " + tst.doOp("*") + " :: " + tst.doOp("+") + " " );	//mult 1st will give min, add 1st will give max
			    print( "The minimum and maximum are " + tst.doOp("*") + " and " + tst.doOp("+") );
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
		
		List<Object> eqObjs;
		
		public Equation(String eq){
			eqObjs = new ArrayList<Object>();
			int tmp = -1;
			for( int i = 0;  i < eq.length(); i++ ){
				char c = eq.charAt(i);
				if( Character.isDigit(c) ){	// char is a digit
					if( tmp != -1 ){
						int tmp2 = tmp*10;	//digit with 2 numbers
						tmp = Integer.parseInt( Character.toString(c) );
						eqObjs.add(tmp2 + tmp);
						tmp = -1;
					}else{					//single number digit
						tmp = Integer.parseInt( Character.toString(c) );
					}
				}else{
					if(tmp != -1){
						eqObjs.add(tmp);	//add tmp obj b4 operator
						tmp = -1;
					}						//add operator
					eqObjs.add(Character.toString(c));
				}
			}
			if(tmp != -1){
				eqObjs.add(tmp);			//add final operand
			}
		}
		
		
		public String doOp(String op){
			List<Object> eq = new ArrayList<Object>(eqObjs);
			for(int i = 0; i < eq.size(); i++){
				Object o = eq.get(i);
				if( o instanceof String){		//search 4 operands
					if( o.equals( op ) ){		//only operate on operators that match the 1st
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
				if( o instanceof String){			//seach 4 operands
					if( !o.equals( op ) ){			//Now do the operators that do not match
						int op2 = (Integer) eq.remove( i + 1 ) ;
						int op1 = (Integer) eq.remove( i - 1 ) ;
						if( op.equals("*") ){	
//							print("add");
							eq.set(i-1, (op1 + op2) );//remember do the opposite operation here		
						}else if( op.equals("+") ){
//							print("mult");
							eq.set(i-1, (op1 * op2) );//remember do the opposite operation here
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
