package command;

import java.util.ArrayList;

import expression.Variable;

public class DefineVariableCommand extends Command {

	public DefineVariableCommand() {
		super();
	}

	@Override
	public int doCommand() {
		ArrayList<String[]> token = this.interpreter.getTokens();
		int indexBlockOfTokens = this.interpreter.getIndexBlockOfTokens();
		int indexToken = this.interpreter.getIndexToken();
		String variableServerName = token.get(indexBlockOfTokens)[indexToken + 1];

		this.interpreter.getServerSymbolTable().put(variableServerName, new Variable(0.0, variableServerName));

		this.interpreter.setIndexToken(indexToken + 1);

		return 0;
	}
}
