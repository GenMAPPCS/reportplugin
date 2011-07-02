package org.genmapp.reportplugin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import cytoscape.Cytoscape;
import cytoscape.logger.CyLogger;
import cytoscape.plugin.CytoscapePlugin;

public class ReportPlugin extends CytoscapePlugin {
	static JPanel reportPanel = null;
	static JEditorPane reportWindow = null;
	static JButton reportSaveButton = null;

	public ReportPlugin() {
		initializeReportUI();
		new CommandHandler();

		// TODO: add toggle for assisted mode and assist window
		// TODO: add toggle for record mode and script window

	}

	/**
	 * 
	 */
	void initializeReportUI() {
		// create textarea in results section
		reportWindow = new JEditorPane();
		reportPanel = new JPanel();
		reportSaveButton = new JButton("Save text to file...");

		reportPanel.setLayout(new BorderLayout());
		reportPanel.add(new JScrollPane(reportWindow), BorderLayout.CENTER);
		reportPanel.add(reportSaveButton, BorderLayout.SOUTH);
		Cytoscape.getDesktop().getCytoPanel(SwingConstants.EAST).add(
				"GenMAPP-CS Report", reportPanel);

		reportSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showSaveDialog(null);
				File file = chooser.getSelectedFile();
				CyLogger.getLogger().info("trying to write Report to " + file);
				try {
					PrintWriter out = new PrintWriter(new FileWriter(file));

					CyLogger.getLogger().info(reportWindow.getText());
					out.print(reportWindow.getText());
					out.close();
				} catch (IOException e) {
					CyLogger.getLogger().info(
							"couldn't write file in Report class: " + e);
				}
			}
		});
	}

	/**
	 * Messages will be sent to the Script Window when in Record Mode. The
	 * content should be limited to cycommands and comment lines, so that it can
	 * be run as a script. The user can manually edit and save the script at any
	 * time.
	 * 
	 * @param msg
	 */
	static public void script(String msg) {
		// TODO: implement
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
		// TODO: implement
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
		reportWindow.setText(reportWindow.getText() + msg + "\n");
	}

}
