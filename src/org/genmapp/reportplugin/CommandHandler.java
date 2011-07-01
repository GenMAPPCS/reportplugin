package org.genmapp.reportplugin;

import java.net.URL;
import java.util.HashMap;
import java.util.Collection;
import java.util.Map;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import cytoscape.command.AbstractCommandHandler;
import cytoscape.command.CyCommandException;
import cytoscape.command.CyCommandManager;
import cytoscape.command.CyCommandResult;
import cytoscape.layout.LayoutProperties;
import cytoscape.layout.Tunable;


/**
 *  Registers CyCommands to Cytoscape, and handles CyCommand events/requests
 *
 */
class CommandHandler extends AbstractCommandHandler {

	protected static final String REPORT = "report";

	protected static final String ARG_MSG = "msg"; // argument name
	// in future, add "save report" 
	
	LayoutProperties props = null;

	public CommandHandler() {
		super(CyCommandManager.reserveNamespace("report"));

		// *** functions definitions for the plugin to expose to the world
		// REPORT:
		// get data id="id"
		addDescription(REPORT, "");
		addArgument(REPORT, ARG_MSG);

	}

	public CyCommandResult execute(String command, Collection<Tunable> args)
			throws CyCommandException {
		return execute(command, createKVMap(args));
	}

	public CyCommandResult execute(String command, Map<String, Object> args )
			throws CyCommandException {
		CyCommandResult result = new CyCommandResult();
		result.addMessage("test");
		for ( String t : args.keySet() )
		{
		   result.addMessage( "Arg: " + t + " = " + args.get( t ) );
		}

		if (REPORT.equals(command)) 
		{
			// convert to map of String,String
			String msg = getArg( command, ARG_MSG, args);

			ReportPlugin.report( msg );
		}
		return (result);
	}
}