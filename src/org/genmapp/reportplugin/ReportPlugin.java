package org.genmapp.reportplugin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import cytoscape.Cytoscape;
import cytoscape.logger.CyLogger;
import cytoscape.logger.LogLevel;
import cytoscape.plugin.CytoscapePlugin;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;


public class ReportPlugin extends CytoscapePlugin
{
	static JPanel reportPanel = null;
	static JEditorPane reportWindow = null;
	static JButton reportSaveButton = null;
	
	void initializeReportUI()
	{
		// create textarea in results section
		reportWindow = new JEditorPane();
		reportPanel = new JPanel();
		reportSaveButton = new JButton( "Save text to file..." );
		
  	    reportPanel.setLayout(new BorderLayout());
		reportPanel.add( new JScrollPane( reportWindow ), BorderLayout.CENTER );
		reportPanel.add( reportSaveButton, BorderLayout.SOUTH );
		Cytoscape.getDesktop().getCytoPanel(
				SwingConstants.EAST).add("GenMAPP-CS Report", reportPanel );
	
		reportSaveButton.addActionListener( new ActionListener() 
		{
			public void actionPerformed( ActionEvent evt ) 
			{
			    JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showSaveDialog( null );
			    File file = chooser.getSelectedFile();
			    CyLogger.getLogger().info( "trying to write Report to " + file );
			    try
			    {
			    	PrintWriter out = new PrintWriter( new FileWriter( file ) );

			    	CyLogger.getLogger().info( reportWindow.getText() );
			    	out.print( reportWindow.getText() );
			    	out.close();
			    }
			    catch( IOException e )
			    {
			    	CyLogger.getLogger().info( "couldn't write file in Report class: " + e );
			    }
			}
		} );	
	}
	public ReportPlugin() 
	{
		initializeReportUI();
		new CommandHandler();
	}
	
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
		reportWindow.setText( reportWindow.getText() + msg + "\n" );
	}
		
}
