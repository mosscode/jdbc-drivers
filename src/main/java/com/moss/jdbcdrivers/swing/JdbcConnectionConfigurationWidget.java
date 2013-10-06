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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import com.moss.jdbcdrivers.DatabaseType;
import com.moss.jdbcdrivers.EnhancedJdbcConnectionConfig;
import com.moss.jdbcdrivers.JdbcDriverImplementation;

public class JdbcConnectionConfigurationWidget extends JPanel{
	private static final long serialVersionUID = 1L;



	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		JFrame frame = new JFrame("Test");
		frame.setSize(640, 480);
		JdbcConnectionConfigurationWidget widget = new JdbcConnectionConfigurationWidget();
		frame.getContentPane().add(widget);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * Used to store favorites
	 */
	private Preferences favoritesPrefsNode = Preferences.userNodeForPackage(JdbcConnectionDialog.class);

	private int helpSectionHeight = 300;
	
	private int favoritesWidth=300;
	private Map favorites = new TreeMap();
	
    private JdbcConnectionConfigurationWidgetView view;
    
    
	
	public JdbcConnectionConfigurationWidgetView getConfigPanel() {
		return view;
	}
	
	public JdbcConnectionConfigurationWidget(){
		view = new JdbcConnectionConfigurationWidgetView();
        view.getPanelHelpSection().setVisible(false);
        view.getPanelFavorites().setVisible(false);
        view.getSplitPaneFavorites().setDividerSize(0);
        view.getSplitPaneHelp().setDividerSize(0);
        
        DatabaseType[] types = DatabaseType.getDatabaseTypes();
        
        for (int x = 0; x < types.length; x++) {
			DatabaseType type = types[x];
			view.getComboBoxRdbmsType().addItem(type.getPrettyName());
		}
        
        if(favoritesPrefsNode != null){
        	try {
				String[] names = favoritesPrefsNode.childrenNames();
				for (int i = 0; i < names.length; i++) {
					String name = names[i];
					ConnectionFavorite favorite = new ConnectionFavorite(favoritesPrefsNode.node(name));
					favorites.put(name, favorite);
				}
			} catch (BackingStoreException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error retreiving favorites:" + e1.getMessage());
			}
        }
        
        favoritesListModel = new DefaultListModel();
		for (Iterator i = favorites.keySet().iterator(); i.hasNext();) {
			String name = (String) i.next();
			favoritesListModel.addElement(name);
		}
        view.getListFavorites().setModel(favoritesListModel);
        
        view.getComboBoxRdbmsType().addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				rdbmsTypeSelected();
			}});
        view.getComboBoxDriver().addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				driverSelected();
			}});
        
        
        view.getButtonShowHelp().addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
        	toggleHelp();
        };});
        
        view.getButtonFavorites().addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
        	toggleFavorites();
        };});
        
        view.getButtonAddToFavorites().addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
        	addToFavorites();
        };});
        
        view.getButtonRemoveFavorite().addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
        	removeSelectedFavorite();
        };});
        
        view.getListFavorites().addListSelectionListener(new ListSelectionListener(){public void valueChanged(javax.swing.event.ListSelectionEvent e) {
        	favoriteWasSelected();
        };});
        rdbmsTypeSelected();
		
        // SHOW THE HELP & FAVORITES BY DEFAULT
        setFavoritesVisible(true);
        setHelpVisible(true);
		
        setConnectButtonVisible(false);
		setLayout(new BorderLayout());
		add(view, BorderLayout.CENTER);
		
		
	}
	
	public void setBorderTitle(String title){
		((TitledBorder)view.getMainPanel().getBorder()).setTitle(title);
	}
	
	private void favoriteWasSelected(){
		String name = (String)view.getListFavorites().getSelectedValue();
		if(name==null) return;
		ConnectionFavorite favorite = (ConnectionFavorite) favorites.get(name);
		showFavorite(favorite);
	}
	
	
	private void showFavorite(ConnectionFavorite favorite){
		DatabaseType dbType = DatabaseType.getDatabaseType(favorite.rdbmsType);
		view.getComboBoxRdbmsType().setSelectedItem(dbType.getPrettyName());
		view.getComboBoxDriver().setSelectedItem(favorite.driverClass);
		
		view.getTextFieldHostname().setText(favorite.host);
		view.getTextFieldPort().setText(favorite.port);
		view.getTextFieldCatalog().setText(favorite.catalog);
		view.getTextFieldUsername().setText(favorite.logon);
		view.getTextFieldPassword().setText(favorite.password);
//		view.getTextField().setText(favorite.);
	}
	
	private void removeSelectedFavorite(){
		String favorite = (String) view.getListFavorites().getSelectedValue();
		favoritesListModel.removeElement(favorite);
		favorites.remove(favorite);
		saveFavorites();
	}
	
	private void addToFavorites(){
		String name = JOptionPane.showInputDialog(this, "Name this favorite");
		if(name == null) return;
		favorites.put(name, new ConnectionFavorite());
		saveFavorites();	
		favoritesListModel.addElement(name);
	}
	
	private void saveFavorites(){
		try {
			
			String[] savedFavorites = favoritesPrefsNode.childrenNames();
			for (int i = 0; i < savedFavorites.length; i++) {
				String name = savedFavorites[i];
				Preferences node = favoritesPrefsNode.node(name);
				node.removeNode();
			}
			
			favoritesPrefsNode.flush();
			
			for (Iterator i = favorites.entrySet().iterator(); i.hasNext();) {
				Map.Entry entry = (Map.Entry) i.next();
				Preferences node = favoritesPrefsNode.node((String)entry.getKey());
				ConnectionFavorite favorite = (ConnectionFavorite) entry.getValue();
				favorite.saveToPrefs(node);
			}
			favoritesPrefsNode.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error saving favorites: " + e.getMessage());
		}
		
	}
	
	public boolean getFavoritesVisible(){
		return view.getPanelFavorites().isVisible();
	}
	
	public void setFavoritesVisible(boolean setVisible){
    	if(setVisible){
    		int oldHeight = view.getSplitPaneFavorites().getWidth()-7;
    		view.getPanelFavorites().setVisible(true);
    		view.getSplitPaneFavorites().setDividerSize(7);
    		setSize(getWidth() + favoritesWidth, getHeight());
    		validate();
    		view.getSplitPaneFavorites().setDividerLocation(-1);
    	}else{
        	view.getPanelFavorites().setVisible(false);
        	view.getSplitPaneFavorites().setDividerSize(0);
        	favoritesWidth = view.getPanelFavorites().getWidth();
        	setSize(getWidth() - favoritesWidth, getHeight());
        	view.getSplitPaneFavorites().setDividerLocation(-1);
    	}

    	if(getParent()!=null){
    		getParent().validate();
    	}
	}
	
	public void toggleFavorites(){
		setFavoritesVisible(!getFavoritesVisible());
	}
	
	public void setHelpVisible(boolean setVisible){

    	if(setVisible){
    		int oldHeight = view.getSplitPaneHelp().getHeight()-7;
    		view.getPanelHelpSection().setVisible(true);
    		view.getSplitPaneHelp().setDividerSize(7);
    		setSize(getWidth(), getHeight() + helpSectionHeight);
    		view.getSplitPaneHelp().setDividerLocation(-1);
    	}else{
    		view.getPanelHelpSection().setVisible(false);
    		view.getSplitPaneHelp().setDividerSize(0);
    		helpSectionHeight = view.getPanelHelpSection().getHeight();
    		setSize(getWidth(), getHeight() - helpSectionHeight);
    		view.getSplitPaneHelp().setDividerLocation(-1);
    	}

    	if(getParent()!=null){
    		getParent().validate();
    	}
	}
	
	public boolean getHelpVisible(){
		return view.getPanelHelpSection().isVisible();
	}
	
    public void toggleHelp(){
    	setHelpVisible(!getHelpVisible());
    }
    
    public EnhancedJdbcConnectionConfig getConnectionConfig(){
    	return new EnhancedJdbcConnectionConfig(selectedDatabaseType, selectedDriver, view.getTextFieldHostname().getText(), null, view.getTextFieldCatalog().getText(), view.getTextFieldUsername().getText(), view.getTextFieldPassword().getText());
    }
    
    private void updateButtons(){
    	view.getButtonConnect().setEnabled(selectedDriver != null && selectedDatabaseType != null);
    }
    
    public void setConnectButtonVisible(boolean visible){
    	view.getButtonConnect().setVisible(visible);
    }
    
    public boolean isConnectButtonVisible(){
    	return view.getButtonConnect().isVisible();
    }
    
    public void setConnectAction(Action action){
    	view.getButtonConnect().setAction(action);
    }
    
    /**
     * TODO: add the ability for alternate drivers to be listed
     *
     */
    private void driverSelected(){
    	selectedDriver = selectedDatabaseType.getJdbcDriver();
    	String description = selectedDriver.getDescription();
    	if(description.equals(""))description = "No help available for this driver";
    	
    	view.getTextPaneDriverDocumentation().setText(description);
    	updateButtons();
    }
    
    private DatabaseType selectedDatabaseType;
    private JdbcDriverImplementation selectedDriver;

	private DefaultListModel favoritesListModel;
    
    /**
     * Loads the list of drivers applicable to the selectedDatabaseType
     */
    private void populateDriversList(){
    	view.getComboBoxDriver().removeAllItems();
    	view.getComboBoxDriver().addItem(selectedDatabaseType.getJdbcDriver().getClassName());
    	view.getComboBoxDriver().setSelectedItem(selectedDatabaseType.getJdbcDriver().getClassName());
    }
    
    private void rdbmsTypeSelected(){
    	DatabaseType[] types = DatabaseType.getDatabaseTypes();
        
        for (int x = 0; x < types.length; x++) {
			DatabaseType type = types[x];
			String name = (String) view.getComboBoxRdbmsType().getSelectedItem();
			if(type.getPrettyName().equals(name)){
				this.selectedDatabaseType = type;
				populateDriversList();
			}
		}
        updateButtons();
    }
    
	
	private class ConnectionFavorite {
		String rdbmsType;
		String driverClass;
		String host;
		String port;
		String catalog;
		String logon;
		String password;
		
		ConnectionFavorite(){
			rdbmsType = selectedDatabaseType.getShortName();
			driverClass = selectedDriver.getClassName();
			host = view.getTextFieldHostname().getText();
			port = view.getTextFieldPort().getText();
			catalog = view.getTextFieldCatalog().getText();
			logon = view.getTextFieldUsername().getText();
			password = view.getTextFieldPassword().getText();
		}
		
		ConnectionFavorite(Preferences node){
			rdbmsType = node.get("rdbmsType", "");
			driverClass = node.get("driverClass", "");
			host = node.get("host", "");
			port  = node.get("port", "");
			catalog  = node.get("catalog", "");
			logon = node.get("logon", "");
			password = node.get("password", "");
		}
		
		void saveToPrefs(Preferences node){
			node.put("rdbmsType", rdbmsType);
			node.put("driverClass", driverClass);
			node.put("host", host);
			node.put("port", port);
			node.put("catalog", catalog);
			node.put("logon", logon);
			node.put("password", password);
			
		}
		
	}
}
