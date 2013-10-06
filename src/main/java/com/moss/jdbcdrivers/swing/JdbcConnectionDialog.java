/**
 * Copyright (C) 2013, Moss Computing Inc.
 *
 * This file is part of jdbc-drivers.
 *
 * jdbc-drivers is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * jdbc-drivers is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jdbc-drivers; see the file COPYING.  If not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library.  Thus, the terms and
 * conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under
 * terms of your choice, provided that you also meet, for each linked
 * independent module, the terms and conditions of the license of that
 * module.  An independent module is a module which is not derived from
 * or based on this library.  If you modify this library, you may extend
 * this exception to your version of the library, but you are not
 * obligated to do so.  If you do not wish to do so, delete this
 * exception statement from your version.
 */
package com.moss.jdbcdrivers.swing;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.moss.jdbcdrivers.EnhancedJdbcConnectionConfig;

public class JdbcConnectionDialog extends JDialog {
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		try {
			JdbcConnectionDialog dialog = new JdbcConnectionDialog((JFrame)null, "Test", true);
			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			dialog.show();
			dialog.getConnectionConfig().createConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JdbcConnectionConfigurationWidget widget = new JdbcConnectionConfigurationWidget();
	private boolean connectWasSelected = false;

	public JdbcConnectionDialog(Dialog owner, String title, boolean modal) throws HeadlessException {
		super(owner, title, modal);
		init();
	}

	public JdbcConnectionDialog(Frame owner, String title, boolean modal) throws HeadlessException {
		super(owner, title, modal);
		init();
	}

	public JdbcConnectionDialog() {
		super();
		init();
	}

	private void init(){
		widget.setConnectAction(new AbstractAction("Connect"){
			public void actionPerformed(ActionEvent e) {
				connectWasSelected=true;
				dispose();
			}
		});
		widget.setConnectButtonVisible(true);
		widget.toggleFavorites();
		widget.toggleHelp();
		getContentPane().add(widget, BorderLayout.CENTER);
		pack();
		setSize(640, getHeight());
		centerOnScreen();
	}
	
	public boolean connectWasSelected(){
		return connectWasSelected;
	}


	public EnhancedJdbcConnectionConfig getConnectionConfig(){
		return widget.getConnectionConfig();
	}

	private void centerOnScreen(){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();

		int framePosX=0, framePosY=0;

		framePosX = (screenSize.width/2) - (getSize().width/2);
		framePosY = (screenSize.height/2) - (getSize().height/2);
		setLocation(framePosX, framePosY);

	}

}
