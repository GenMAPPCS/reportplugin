package org.genmapp.reportplugin;

import java.util.HashMap;
import java.util.Map;

import cytoscape.command.CyCommandException;
import cytoscape.command.CyCommandManager;
import cytoscape.logger.CyLogger;

/* Dear Plugin Writers,
 * 
 * Copy and paste this class into your plugin package so you can send messages 
 * to the Report, Assist and Script Windows like so: 
 * 
 * Report.report( "a noteworthy action" );
 * Report.assist( "a helpful tip");
 * Report.script( "a cycommand or comment line");
 *  
 */

public class Report {

	protected static final String REPORTPLUGIN = "reportplugin";
	protected static final String REPORT = "report";
	protected static final String ASSIST = "assist";
	protected static final String SCRIPT = "script";
	protected static final String ARG_MSG = "msg";

	/**
	 * Messages will be sent to the Script Window when in Record Mode. The
	 * content should be limited to cycommands and comment lines, so that it can
	 * be run as a script. The user can manually edit and save the script at any
	 * time.
	 * 
	 * @param msg
	 *            message to script
	 */
	static public void script(String msg) {
		sendCommand(msg, SCRIPT);
	}

	/**
	 * Messages will be sent to the Assist Window when in Assisted Mode. Think
	 * Microsoft Paperclip, e.g., for novice users, but without the annoying
	 * paperclip.
	 * 
	 * @param msg
	 *            message to assist users
	 */
	static public void assist(String msg) {
		sendCommand(msg, ASSIST);
	}

	/**
	 * Messages will be sent to the Report Window. The contents of the window
	 * are intended to be user-friendly reports on actions taken. The user can
	 * manually edit and save the report at any time during a session for future
	 * reference.
	 * 
	 * @param msg
	 *            message to report
	 */
	static public void report(String msg) {
		sendCommand(msg, REPORT);
	}

	/**
	 * Sends message to the ReportWindow for processing.
	 * 
	 * @param msg
	 * @param com
	 */
	static private void sendCommand(String msg, String com) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put(ARG_MSG, msg);

		try {
			CyCommandManager.execute(REPORTPLUGIN, com, args);
		} catch (CyCommandException e) {
			CyLogger.getLogger().warn(
					"Couldn't execute cycommand in Report:" + e);
			e.printStackTrace();
		}
	}

}
