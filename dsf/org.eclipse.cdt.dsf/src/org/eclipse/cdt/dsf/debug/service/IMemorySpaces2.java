/*******************************************************************************
 * Copyright (c) 2014 Ericsson AB and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Anders Dahlberg (Ericsson)  - Need additional API to extend support for memory spaces (Bug 431627)
 *     Alvaro Sanchez-Leon (Ericsson AB) - Need additional API to extend support for memory spaces (Bug 431627)
 *******************************************************************************/
package org.eclipse.cdt.dsf.debug.service;

import org.eclipse.cdt.dsf.concurrent.DataRequestMonitor;
import org.eclipse.cdt.dsf.datamodel.IDMContext;

/**
 * This extension allows the decoding of an expression with the help of the external debugger
 * 
 * @since 2.5
 */
public interface IMemorySpaces2 extends IMemorySpaces {

	/**
	 * Provides the means to use the debugger backend to help with asynchronous 
	 * resolution of memory space, expression pair from a single string expression
	 * 
	 */
	public void decodeExpression(IDMContext context, String expression, DataRequestMonitor<DecodeResult> rm);

}
