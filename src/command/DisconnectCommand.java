package command;

public class DisconnectCommand extends Command {

	public DisconnectCommand() {
		super();
	}

	@Override
	public int doCommand() {
		ConnectCommand.closeConnection();

		OpenDataServerCommand.stopServer();

		return 1;
	}

}