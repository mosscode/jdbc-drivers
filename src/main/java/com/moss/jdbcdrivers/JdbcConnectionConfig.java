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
package com.moss.jdbcdrivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.prefs.Preferences;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JdbcConnectionConfig {
	
	private static final Log log = LogFactory.getLog(JdbcConnectionConfig.class);
	
	private String jdbcUrl, jdbcDriverClassName, logon, password;
	
	public JdbcConnectionConfig(){}
	
	public JdbcConnectionConfig(Preferences prefs){
		loadFromPrefs(prefs);
	}
	public JdbcConnectionConfig(String jdbcUrl,String  jdbcDriverClassName,String  logon,String  password){
		this.jdbcUrl = jdbcUrl;
		this.jdbcDriverClassName = jdbcDriverClassName;
		this.logon = logon;
		this.password = password;
	}
	
	public JdbcConnectionConfig(DatabaseType dbType, JdbcDriverImplementation driver, String hostname, Integer port, String catalog, String logon, String password){
		if(driver == null) driver = dbType.getJdbcDriver(); //use the default driver if none was passed
		this.jdbcUrl = driver.createJdbcUrl(hostname, port, catalog, logon, password);
		this.jdbcDriverClassName = driver.getClassName();
		this.logon = logon;
		this.password = password;
	}
	
	public JdbcConnectionConfig(DatabaseType dbType, JdbcDriverImplementation driver, String hostname, Integer port, String catalog, String logon, String password, Properties properties){
		if(driver == null) driver = dbType.getJdbcDriver(); //use the default driver if none was passed
		this.jdbcUrl = driver.createJdbcUrl(hostname, port, catalog, logon, password, properties);
		this.jdbcDriverClassName = driver.getClassName();
		this.logon = logon;
		this.password = password;
	}
	
	public void saveToPrefs(Preferences node){
		node.put("url", jdbcUrl);
		node.put("driver", jdbcDriverClassName);
		node.put("logon", logon);
		node.put("password", password);
	}
	
	public void loadFromPrefs(Preferences node){
		jdbcUrl = node.get("url", "");
		jdbcDriverClassName = node.get("driver", "");
		logon = node.get("logon", "");
		password = node.get("password", "");
	}
	
	public String getJdbcDriverClassName() {
		return jdbcDriverClassName;
	}

	public void setJdbcDriverClassName(String jdbcDriverClassName) {
		this.jdbcDriverClassName = jdbcDriverClassName;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getLogon() {
		return logon;
	}

	public void setLogon(String logon) {
		this.logon = logon;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Connection createConnection() throws ClassNotFoundException, SQLException{
		Class.forName(getJdbcDriverClassName());
		String url = getJdbcUrl();
		log.info("Connecting to " + url);
		return DriverManager.getConnection(url, getLogon(), getPassword());
	}
	
}
