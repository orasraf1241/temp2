package test;

import interpreter.Interpreter;

public class MyInterpreter {

	public static int interpreter(String[] lines){
		
		Interpreter interpeter = new Interpreter();
		return (int)interpeter.interpret(lines);
	}
}
