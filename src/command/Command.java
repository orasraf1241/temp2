package command;

import interpreter.Interpreter;

public abstract class Command {

	Interpreter interpreter;

	public Command() {
		this.interpreter = null;
	}

	public abstract int doCommand();

	public void setInterpreter(Interpreter otherInterpeter) {
		this.interpreter = otherInterpeter;
	}
}