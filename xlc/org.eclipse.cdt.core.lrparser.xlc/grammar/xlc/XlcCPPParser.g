-----------------------------------------------------------------------------------
-- Copyright (c) 2009 IBM Corporation and others.
-- All rights reserved. This program and the accompanying materials
-- are made available under the terms of the Eclipse Public License v1.0
-- which accompanies this distribution, and is available at
-- http://www.eclipse.org/legal/epl-v10.html
--
-- Contributors:
--     IBM Corporation - initial API and implementation
-----------------------------------------------------------------------------------

%options la=2
%options package=org.eclipse.cdt.internal.core.lrparser.xlc.cpp
%options template=LRParserTemplate.g


$Import
	GPPGrammar.g
$End

$Import
	XlcGrammarExtensions.g
$End 

$Globals
/.
	import org.eclipse.cdt.core.lrparser.xlc.action.XlcCPPBuildASTParserAction;
	import org.eclipse.cdt.core.dom.lrparser.action.gnu.GPPSecondaryParserFactory;
	import org.eclipse.cdt.internal.core.lrparser.xlc.ast.XlcCPPNodeFactory;
./
$End

$Define

	$build_action_class /. XlcCPPBuildASTParserAction ./
	$parser_factory_create_expression /. GPPSecondaryParserFactory.getDefault() ./
	$node_factory_create_expression /. XlcCPPNodeFactory.getDefault() ./
	
$End



$Terminals

	_Complex
	restrict
	
$End


$Start
    translation_unit
$End


$Rules 

simple_type_specifier_token
	::= '_Complex'
	
cv_qualifier
    ::= 'restrict'

block_declaration
    ::= vector_declaration
    

identifier_token
    ::= 'vector' 
      | 'pixel'
    
    
specifier_qualifier
    ::= 'typedef'
          /. $Build  consumeToken(); $EndBuild ./
          
$End