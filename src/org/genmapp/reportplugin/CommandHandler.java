package org.genmapp.reportplugin;

import java.util.Collection;
import java.util.Map;

import cytoscape.command.AbstractCommandHandler;
import cytoscape.command.CyCommandException;
import cytoscape.command.CyCommandManager;
import cytoscape.command.CyCommandResult;
import cytoscape.layout.Tunable;

/**
 * Registers CyCommands to Cytoscape, and handles CyCommand events/requests
 * 
 */
class CommandHandler extends AbstractCommandHandler {

	protected static final String REPORTPLUGIN = "reportplugin";
	protected static final String REPORT = "report";
	protected static final String ASSIST = "assist";
	protected static final String SCRIPT = "script";
	protected static final String ARG_MSG = "msg";


	public CommandHandler() {
		super(CyCommandManager.reserveNamespace(REPORTPLUGIN));

		addDescription(REPORT, "Send message to Report Window");
		addArgument(REPORT, ARG_MSG);

		addDescription(ASSIST, "Send message to Assist Window");
		addArgument(ASSIST, ARG_MSG);

		addDescription(SCRIPT, "Send message to Script Window");
		addArgument(SCRIPT, ARG_MSG);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cytoscape.command.CyCommandHandler#execute(java.lang.String,
	 * java.util.Collection)
	 */
	public CyCommandResult execute(String command, Collection<Tunable> args)
			throws CyCommandException {
		return execute(command, createKVMap(args));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cytoscape.command.CyCommandHandler#execute(java.lang.String,
	 * java.util.Map)
	 */
	public CyCommandResult execute(String command, Map<String, Object> args)
			throws CyCommandException {
		CyCommandResult result = new CyCommandResult();
		
		String msg;
		Object m = getArg(command, ARG_MSG, args);
		if (m instanceof String)
			msg = (String) m;
		else
			throw new CyCommandException(ARG_MSG
					+ ": invalid type (try String!)");

		if (REPORT.equals(command)) {
			ReportPlugin.report(msg);
			result.addMessage("Reported "+msg);
		}
		else if (ASSIST.equals(command)){
			ReportPlugin.assist(msg);
			result.addMessage("Not yet supported");
		}
		else if (SCRIPT.equals(command)){
			ReportPlugin.script(msg);
			result.addMessage("Not yet supported");
		}
		else {
			result.addMessage("Command not recognized");
			throw new RuntimeException("Unknown command: " + command);
		}
		return (result);
	}

}