package command;

public class LoopCommand extends ConditionParser {

	public LoopCommand() {
		super();
	}

	@Override
	public int doCommand() {
		super.doCommand();

		while (checkCondtion()) {
			executeListOfCommands();
		}

		this.interpreter.setIndexBlockOfTokens(this.IndexBlockOfTokens + this.commandList.size());
		this.interpreter.setIndexToken(0);
		return 0;
	}

}