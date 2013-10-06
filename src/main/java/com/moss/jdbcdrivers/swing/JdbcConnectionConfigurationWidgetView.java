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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

class JdbcConnectionConfigurationWidgetView extends JPanel {
	private JPanel panel;
	private JSplitPane splitPane;
	private JButton favoritesButton;
	private JSplitPane splitPane_1;
	private JPanel panel_4;
	private JPanel panel_1;
	private JTextPane textPane;
	private JButton button_1;
	private JButton driverHelpButton;
	private JList list;
	private JButton addToFavoritesButton;
	private static final long serialVersionUID = 1L;
	
    private JComboBox comboBox_1;
    private JComboBox comboBox;
    private JTextField textField_3;
    private JTextField textField_2;
    private JTextField textField;
    private JButton button;
    private JTextField textField_1;
    private JPasswordField passwordField;
    public JdbcConnectionConfigurationWidgetView() {
        super();
    	setLayout(new BorderLayout());

    	final JPanel panel_2 = new JPanel();
    	add(panel_2);
    	panel_2.setLayout(new BorderLayout());

    	splitPane_1 = new JSplitPane();
    	splitPane_1.setResizeWeight(0);
    	splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    	panel_2.add(splitPane_1);

    	splitPane = new JSplitPane();
    	splitPane.setResizeWeight(.76d);
    	splitPane_1.setLeftComponent(splitPane);

    	panel = new JPanel();
    	splitPane.setLeftComponent(panel);
    	panel.setBorder(new TitledBorder(null, "Configuration", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
    	panel.setLayout(new GridBagLayout());

    	final JLabel rdbmsTypeLabel = new JLabel();
    	final GridBagConstraints gridBagConstraints_14 = new GridBagConstraints();
    	gridBagConstraints_14.insets = new Insets(5, 5, 5, 0);
    	gridBagConstraints_14.ipadx = 5;
    	gridBagConstraints_14.fill = GridBagConstraints.HORIZONTAL;
    	panel.add(rdbmsTypeLabel, gridBagConstraints_14);
    	rdbmsTypeLabel.setText("RDBMS Type");

    	comboBox = new JComboBox();
    	final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
    	gridBagConstraints_11.insets = new Insets(5, 5, 5, 5);
    	gridBagConstraints_11.fill = GridBagConstraints.HORIZONTAL;
    	gridBagConstraints_11.weightx = 1.0;
    	gridBagConstraints_11.gridy = 0;
    	gridBagConstraints_11.gridx = 1;
    	panel.add(comboBox, gridBagConstraints_11);

    	final JLabel jdbcDriverLabel = new JLabel();
    	final GridBagConstraints gridBagConstraints_12 = new GridBagConstraints();
    	gridBagConstraints_12.insets = new Insets(0, 5, 5, 0);
    	gridBagConstraints_12.ipadx = 5;
    	gridBagConstraints_12.fill = GridBagConstraints.HORIZONTAL;
    	gridBagConstraints_12.gridy = 1;
    	gridBagConstraints_12.gridx = 0;
    	panel.add(jdbcDriverLabel, gridBagConstraints_12);
    	jdbcDriverLabel.setText("JDBC Driver");

    	comboBox_1 = new JComboBox();
    	final GridBagConstraints gridBagConstraints_13 = new GridBagConstraints();
    	gridBagConstraints_13.insets = new Insets(0, 5, 5, 5);
    	gridBagConstraints_13.fill = GridBagConstraints.HORIZONTAL;
    	gridBagConstraints_13.weightx = 1.0;
    	gridBagConstraints_13.gridy = 1;
    	gridBagConstraints_13.gridx = 1;
    	panel.add(comboBox_1, gridBagConstraints_13);

    	final JLabel hostButton = new JLabel();
    	final GridBagConstraints gridBagConstraints = new GridBagConstraints();
    	gridBagConstraints.insets = new Insets(0, 5, 5, 0);
    	gridBagConstraints.ipadx = 5;
    	gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    	gridBagConstraints.gridy = 2;
    	gridBagConstraints.gridx = 0;
    	panel.add(hostButton, gridBagConstraints);
    	hostButton.setText("Host");

    	textField_3 = new JTextField();
    	final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
    	gridBagConstraints_10.insets = new Insets(0, 5, 5, 5);
    	gridBagConstraints_10.fill = GridBagConstraints.HORIZONTAL;
    	gridBagConstraints_10.weightx = 1.0;
    	gridBagConstraints_10.gridy = 2;
    	gridBagConstraints_10.gridx = 1;
    	panel.add(textField_3, gridBagConstraints_10);

    	final JLabel portButton = new JLabel();
    	final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
    	gridBagConstraints_1.insets = new Insets(0, 5, 5, 0);
    	gridBagConstraints_1.ipadx = 5;
    	gridBagConstraints_1.fill = GridBagConstraints.HORIZONTAL;
    	gridBagConstraints_1.gridy = 3;
    	gridBagConstraints_1.gridx = 0;
    	panel.add(portButton, gridBagConstraints_1);
    	portButton.setText("Port");

    	textField = new JTextField();
    	final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
    	gridBagConstraints_8.insets = new Insets(0, 5, 5, 5);
    	gridBagConstraints_8.fill = GridBagConstraints.HORIZONTAL;
    	gridBagConstraints_8.weightx = 1.0;
    	gridBagConstraints_8.gridy = 3;
    	gridBagConstraints_8.gridx = 1;
    	panel.add(textField, gridBagConstraints_8);

    	final JLabel catalogButton = new JLabel();
    	final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
    	gridBagConstraints_7.insets = new Insets(0, 5, 5, 0);
    	gridBagConstraints_7.ipadx = 5;
    	gridBagConstraints_7.fill = GridBagConstraints.HORIZONTAL;
    	gridBagConstraints_7.gridy = 4;
    	gridBagConstraints_7.gridx = 0;
    	panel.add(catalogButton, gridBagConstraints_7);
    	catalogButton.setText("Catalog");

    	textField_2 = new JTextField();
    	final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
    	gridBagConstraints_9.insets = new Insets(0, 5, 5, 5);
    	gridBagConstraints_9.fill = GridBagConstraints.HORIZONTAL;
    	gridBagConstraints_9.weightx = 1.0;
    	gridBagConstraints_9.gridy = 4;
    	gridBagConstraints_9.gridx = 1;
    	panel.add(textField_2, gridBagConstraints_9);

    	final JLabel label_1 = new JLabel();
    	final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
    	gridBagConstraints_2.insets = new Insets(0, 5, 5, 0);
    	gridBagConstraints_2.ipadx = 5;
    	gridBagConstraints_2.fill = GridBagConstraints.HORIZONTAL;
    	gridBagConstraints_2.gridy = 5;
    	gridBagConstraints_2.gridx = 0;
    	panel.add(label_1, gridBagConstraints_2);
    	label_1.setText("Username");

    	textField_1 = new JTextField();
    	final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
    	gridBagConstraints_5.insets = new Insets(0, 5, 5, 5);
    	gridBagConstraints_5.fill = GridBagConstraints.HORIZONTAL;
    	gridBagConstraints_5.weightx = 1.0;
    	gridBagConstraints_5.gridy = 5;
    	gridBagConstraints_5.gridx = 1;
    	panel.add(textField_1, gridBagConstraints_5);

    	final JLabel label_2 = new JLabel();
    	final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
    	gridBagConstraints_3.insets = new Insets(0, 5, 5, 0);
    	gridBagConstraints_3.ipadx = 5;
    	gridBagConstraints_3.fill = GridBagConstraints.HORIZONTAL;
    	gridBagConstraints_3.gridy = 6;
    	gridBagConstraints_3.gridx = 0;
    	panel.add(label_2, gridBagConstraints_3);
    	label_2.setText("Password");

    	passwordField = new JPasswordField();
    	final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
    	gridBagConstraints_4.insets = new Insets(0, 5, 5, 5);
    	gridBagConstraints_4.fill = GridBagConstraints.HORIZONTAL;
    	gridBagConstraints_4.weightx = 1.0;
    	gridBagConstraints_4.gridy = 6;
    	gridBagConstraints_4.gridx = 1;
    	panel.add(passwordField, gridBagConstraints_4);

    	final JPanel panel_3 = new JPanel();
    	final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
    	gridBagConstraints_6.insets = new Insets(5, 0, 5, 5);
    	gridBagConstraints_6.anchor = GridBagConstraints.SOUTHEAST;
    	gridBagConstraints_6.weighty = 1.0;
    	gridBagConstraints_6.gridy = 7;
    	gridBagConstraints_6.gridx = 1;
    	panel.add(panel_3, gridBagConstraints_6);

    	driverHelpButton = new JButton();
    	driverHelpButton.setText("Driver Help");
    	panel_3.add(driverHelpButton);

    	favoritesButton = new JButton();
    	favoritesButton.setText("Favorites");
    	panel_3.add(favoritesButton);

    	button = new JButton();
    	panel_3.add(button);
    	button.setText("Connect");

    	panel_4 = new JPanel();
    	splitPane.setRightComponent(panel_4);
    	panel_4.setBorder(new TitledBorder(null, "Favorites", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
    	panel_4.setLayout(new BorderLayout());

    	final JScrollPane scrollPane_1 = new JScrollPane();
    	panel_4.add(scrollPane_1);

    	list = new JList();
    	scrollPane_1.setViewportView(list);

    	final JPanel panel_5 = new JPanel();
    	panel_4.add(panel_5, BorderLayout.NORTH);

    	addToFavoritesButton = new JButton();
    	panel_5.add(addToFavoritesButton);
    	addToFavoritesButton.setText("+");

    	button_1 = new JButton();
    	button_1.setText("-");
    	panel_5.add(button_1);

    	panel_1 = new JPanel();
    	splitPane_1.setRightComponent(panel_1);
    	panel_1.setBorder(new TitledBorder(null, "Help", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
    	panel_1.setLayout(new BorderLayout());

    	final JScrollPane scrollPane = new JScrollPane();
    	panel_1.add(scrollPane);

    	textPane = new JTextPane();
    	scrollPane.setViewportView(textPane);
        //
    }
	public JButton getButtonConnect() {
		return button;
	}
	public JTextField getTextFieldPort() {
		return textField;
	}
	public JTextField getTextFieldUsername() {
		return textField_1;
	}

	public JComboBox getComboBoxRdbmsType() {
		return comboBox;
	}
	public JComboBox getComboBoxDriver() {
		return comboBox_1;
	}
	public JTextField getTextFieldHostname() {
		return textField_3;
	}
	public JTextField getTextFieldCatalog() {
		return textField_2;
	}
	public JPasswordField getTextFieldPassword() {
		return passwordField;
	}
	public JTextPane getTextPaneDriverDocumentation() {
		return textPane;
	}
	public JButton getButtonAddToFavorites() {
		return addToFavoritesButton;
	}
	public JButton getButtonShowHelp() {
		return driverHelpButton;
	}
	public JButton getButtonRemoveFavorite() {
		return button_1;
	}
	public JTextPane getTextPaneDocumentation() {
		return textPane;
	}
	public JPanel getPanelHelpSection() {
		return panel_1;
	}
	public JPanel getPanelFavorites() {
		return panel_4;
	}
	public JSplitPane getSplitPaneHelp() {
		return splitPane_1;
	}
	public JButton getButtonFavorites() {
		return favoritesButton;
	}
	public JSplitPane getSplitPaneFavorites() {
		return splitPane;
	}
	public JList getListFavorites() {
		return list;
	}
	public JPanel getMainPanel() {
		return panel;
	}
}
