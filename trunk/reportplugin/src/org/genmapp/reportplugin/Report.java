package org.genmapp.reportplugin;
import java.util.HashMap;
import java.util.Map;

import cytoscape.command.CyCommandException;
import cytoscape.command.CyCommandManager;
import cytoscape.command.CyCommandResult;
import cytoscape.logger.CyLogger;

/*  Plugin Writers --- copy and paste this class into your plugin package
 * 
 *  Then, you can say something like
 *  
 *  Report.report( "message to the user" );
 *  
 *  and your message will be processed by the ReportPlugin
 */

public class Report
{
	// Custom behavior here -- cannot use CyLogger
	// may want a different sig?  Discuss this....
	static public void script( String msg )
	{
		
	}
	// think Microsoft Paperclip for novice users
	static public void assist( String msg )
	{
		
	}
	// Custom behavior here -- cannot use CyLogger
	static public void report( String msg )
	{	
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("msg", msg);
		CyCommandResult result = null;

		try {
			result = CyCommandManager.execute("report", "report",
					args );
		} catch (CyCommandException e) {
			// TODO Auto-generated catch block
			CyLogger.getLogger().warn( "Couldn't execute cycommand in ReportStub:" + e );
			e.printStackTrace();
		} 
	}
}
