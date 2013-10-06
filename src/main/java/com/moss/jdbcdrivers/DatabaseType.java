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

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class DatabaseType implements Serializable {
		public static final DatabaseType 
			DB_TYPE_SQL_SERVER = new DatabaseTypeMicrosoftSqlServer(),
			DB_TYPE_POSTGRESQL = new DatabaseTypePostgreSQL(),
			DB_TYPE_MYSQL = new DatabaseTypeMySQL(),
			DB_TYPE_DERBY = new DatabaseTypeDerby(),
			DB_TYPE_HSQLDB = new DatabaseTypeHsqldb();
		private static DatabaseType[] registeredTypes = {
			DB_TYPE_SQL_SERVER,
			DB_TYPE_POSTGRESQL,
			DB_TYPE_MYSQL,
			DB_TYPE_DERBY,
			DB_TYPE_HSQLDB
		};
		
		private String prettyName, hibernateDialect, shortName;
		private Integer defaultPort;
		private String defaultSchemaName;
		private JdbcDriverImplementation driver;
		
		protected DatabaseType(String prettyName, String shortName, JdbcDriverImplementation driver, String hibernateDialect){
			this.prettyName = prettyName;
			this.hibernateDialect = hibernateDialect;
			this.shortName = shortName;
			this.driver = driver;
		}
		
		protected DatabaseType(String prettyName, String shortName, JdbcDriverImplementation driver, String hibernateDialect, Integer defaultPort, String defaultSchemaName){
			this(prettyName, shortName, driver, hibernateDialect);
			this.defaultPort = defaultPort;
			this.defaultSchemaName = defaultSchemaName;
		}
		
		public String getHibernateDialect() {
			return hibernateDialect;
		}
		public String getPrettyName() {
			return prettyName;
		}
		public String getShortName() {
			return shortName;
		}
		public JdbcDriverImplementation getJdbcDriver() {
			return driver;
		}
		public Integer getDefaultPort() {
			return defaultPort;
		}
		
		/**
		 * Returns the DatabaseType instance for the given short name based on the list of registered DatabaseTypes
		 * @param typeShortName
		 * @return
		 */
		public static DatabaseType getDatabaseType(String typeShortName){
			for (int x = 0; x < registeredTypes.length; x++) {
				DatabaseType dbType = registeredTypes[x];
				if(dbType.getShortName().equals(typeShortName))return dbType;
			}
			return null;
		}
		
		public static DatabaseType[] getDatabaseTypes(){
			return registeredTypes;
		}
		
		public String getDefaultSchemaName() {
			return defaultSchemaName;
		}
		
		public String toString(){
			return prettyName + " (" + shortName + ")";
		}
		
		public boolean equals(Object o) {
			return
				o != null
				&&
				o instanceof DatabaseType
				&&
				((DatabaseType)o).shortName.equals(shortName);
		}
		
		public int hashCode() {
			return shortName.hashCode();
		}
}
