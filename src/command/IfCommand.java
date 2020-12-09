package command;

public class IfCommand extends ConditionParser {

	public IfCommand() {
		super();
	}

	@Override
	public int doCommand() {
		super.doCommand();

		if (checkCondtion()) {
			executeListOfCommands();
		}

		this.interpreter.setIndexBlockOfTokens(this.IndexBlockOfTokens + this.commandList.size());
		this.interpreter.setIndexToken(0);
		return 0;
	}

}