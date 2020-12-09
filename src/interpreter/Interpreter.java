package interpreter;

import java.util.ArrayList;
import java.util.HashMap;

import command.Command;
import command.CommandFactory;
import expression.ExpressionCommand;
import expression.SimulatorVariable;
import expression.Variable;

public class Interpreter {

	private HashMap<String, Variable> serverSymbolTable;
	private HashMap<String, SimulatorVariable> simulatorSymbolTable;
	private CommandFactory commandFactory;
	private ArrayList<String[]> token;
	private double returnValue;
	private int indexToken;
	private int indexBlockOfTokens;

	public Interpreter() {
		this.serverSymbolTable = new HashMap<String, Variable>();
		this.simulatorSymbolTable = new HashMap<String, SimulatorVariable>();
		this.token = new ArrayList<String[]>();
		this.commandFactory = new CommandFactory();
		this.returnValue = 0;
		this.indexToken = 0;
		this.indexBlockOfTokens = 0;
	}

	// Getters & Setters
	public HashMap<String, Variable> getServerSymbolTable() {
		return this.serverSymbolTable;
	}

	public HashMap<String, SimulatorVariable> getSimulatorSymbolTable() {
		return this.simulatorSymbolTable;
	}

	public CommandFactory getCommandFactory() {
		return this.commandFactory;
	}

	public void setTokens(ArrayList<String[]> otherTokens) {
		this.token = otherTokens;
	}

	public ArrayList<String[]> getTokens() {
		return token;
	}

	public void setReturnValue(double otherReturnedValue) {
		this.returnValue = otherReturnedValue;
	}

	public double getReturnValue() {
		return this.returnValue;
	}

	public void setIndexToken(int otherIndexToken) {
		this.indexToken = otherIndexToken;
	}

	public int getIndexToken() {
		return this.indexToken;
	}

	public void setIndexBlockOfTokens(int otherIndexBlockOfTokens) {
		this.indexBlockOfTokens = otherIndexBlockOfTokens;
	}

	public int getIndexBlockOfTokens() {
		return this.indexBlockOfTokens;
	}

	public double interpreter(String[] codeLines) {
		resetInterpeter();
		lexer(codeLines);
		parser();
		//printData();
		return getReturnValue();
	}

	public void lexer(String[] code) {
		for (String line : code) {

			line = line.replaceAll("\\{", "\\ { ").replaceAll("\\}", "\\ } ").replaceAll("\\>", "\\ > ")
					.replaceAll("\\*", "\\ * ").replaceAll("\\/", "\\ / ").replaceAll("\\(", "\\ ( ")
					.replaceAll("\\<", "\\ < ").replaceAll("\\+", "\\ + ").replaceAll("\\-", "\\ - ")
					.replaceAll("\\)", "\\ ) ").replaceAll("\\=", "\\ = ").trim();

			this.token.add(line.split("\\s+"));
		}
	}

	public void printData() {
		System.out.println("\nResults:");
		System.out.println(this.serverSymbolTable.keySet());
		System.out.println(this.simulatorSymbolTable.keySet());

		for (Variable var : this.serverSymbolTable.values()) {
			System.out.print(var.getName() + " = " + var.getValue() + ", ");
		}
		System.out.println("");
		for (Variable var : this.simulatorSymbolTable.values()) {
			System.out.print(var.getName() + " = " + var.getValue() + ", ");
		}
		System.out.println("");
	}

	public void parser() {
		for (this.indexBlockOfTokens = 0; this.indexBlockOfTokens < this.token.size(); this.indexBlockOfTokens++) {
			for (this.indexToken = 0; this.indexToken < this.token.get(indexBlockOfTokens).length; this.indexToken++) {
				Command command = this.commandFactory.getCommand(this.token.get(indexBlockOfTokens)[this.indexToken]);
				if (command != null) {
					command.setInterpreter(this);
					new ExpressionCommand(command).calculate();
				}
			}
		}
	}

	// Resets variables
	private void resetInterpeter() {
		this.token.clear();
		this.token = null;
		this.token = new ArrayList<String[]>();
		this.indexToken = 0;
		this.indexBlockOfTokens = 0;
		this.returnValue = 0;
		this.serverSymbolTable.clear();
		this.simulatorSymbolTable.clear();
		this.simulatorSymbolTable.put("simX", new SimulatorVariable(0.0, "simX"));
		this.simulatorSymbolTable.put("simY", new SimulatorVariable(0.0, "simY"));
		this.simulatorSymbolTable.put("simZ", new SimulatorVariable(0.0, "simZ"));
	}
}