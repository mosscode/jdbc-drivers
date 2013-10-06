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

import java.util.Map;
import java.util.Properties;

@SuppressWarnings("serial")
public class JdbcDriverTypeDerby extends AbstractDerbyJdbcDriverType{

	public String createJdbcUrl(String hostname, Integer port, String catalog, String username, String password, Properties props) {
		StringBuffer url = new StringBuffer("jdbc:derby:");
		url.append(catalog);

		for (Map.Entry<Object, Object> property : props.entrySet()) {
			url.append(";");
			url.append(property.getKey());
			url.append("=");
			url.append(property.getValue());
		}
		return  url.toString();
	}

	public String getClassName() {
		return "org.apache.derby.jdbc.EmbeddedDriver";
	}
	@Override
	public String getDescription() {
		return "Settting the 'create' property to 'true' makes the driver initialize an uninitialized directory, but this will fail if the directory has already been initialized.\n" + 
		       "For a complete list of attributes, see http://db.apache.org/derby/docs/10.4/ref/rrefattrib24612.html#rrefattrib24612";
	}
}
