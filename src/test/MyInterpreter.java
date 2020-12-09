package test;

import interpreter.Interpreter;

public class MyInterpreter {

	public static int interpreter(String[] lines){
		
		Interpreter interpreter = new Interpreter();
		return (int)interpreter.interpreter(lines);
	}
}
