/*
 *  Copyright 2006 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.mybatis.generator.api.dom.java;

import static org.mybatis.generator.api.dom.OutputUtilities.calculateImports;
import static org.mybatis.generator.api.dom.OutputUtilities.newLine;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.IntrospectedColumn;

/**
 * @author Jeff Butler
 * 
 */
public class TopLevelEnumeration extends InnerEnum implements CompilationUnit {
    private Set<FullyQualifiedJavaType> importedTypes;

    private String tableName;

    private Set<String> staticImports;

    private List<String> fileCommentLines;

    private List<IntrospectedColumn> primaryKeyColumns;
    private List<IntrospectedColumn> baseColumns;
    private List<IntrospectedColumn> blobColumns;

    /**
     * @param type
     */
    public TopLevelEnumeration(FullyQualifiedJavaType type) {
	super(type);
	importedTypes = new TreeSet<FullyQualifiedJavaType>();
	fileCommentLines = new ArrayList<String>();
	staticImports = new TreeSet<String>();
    }

    public String getFormattedContent() {
	StringBuilder sb = new StringBuilder();

	for (String fileCommentLine : fileCommentLines) {
	    sb.append(fileCommentLine);
	    newLine(sb);
	}

	if (getType().getPackageName() != null && getType().getPackageName().length() > 0) {
	    sb.append("package "); //$NON-NLS-1$
	    sb.append(getType().getPackageName());
	    sb.append(';');
	    newLine(sb);
	    newLine(sb);
	}

	for (String staticImport : staticImports) {
	    sb.append("import static "); //$NON-NLS-1$
	    sb.append(staticImport);
	    sb.append(';');
	    newLine(sb);
	}

	if (staticImports.size() > 0) {
	    newLine(sb);
	}

	Set<String> importStrings = calculateImports(importedTypes);
	for (String importString : importStrings) {
	    sb.append(importString);
	    newLine(sb);
	}

	if (importStrings.size() > 0) {
	    newLine(sb);
	}

	sb.append(super.getFormattedContent(0));

	return sb.toString();
    }

    public Set<FullyQualifiedJavaType> getImportedTypes() {
	return Collections.unmodifiableSet(importedTypes);
    }

    public FullyQualifiedJavaType getSuperClass() {
	throw new UnsupportedOperationException(getString("RuntimeError.11")); //$NON-NLS-1$
    }

    public boolean isJavaInterface() {
	return false;
    }

    public boolean isJavaEnumeration() {
	return true;
    }

    public void addImportedType(FullyQualifiedJavaType importedType) {
	if (importedType.isExplicitlyImported() && !importedType.getPackageName().equals(getType().getPackageName())) {
	    importedTypes.add(importedType);
	}
    }

    public void addFileCommentLine(String commentLine) {
	fileCommentLines.add(commentLine);
    }

    public List<String> getFileCommentLines() {
	return fileCommentLines;
    }

    public void addImportedTypes(Set<FullyQualifiedJavaType> importedTypes) {
	this.importedTypes.addAll(importedTypes);
    }

    public Set<String> getStaticImports() {
	return staticImports;
    }

    public void addStaticImport(String staticImport) {
	staticImports.add(staticImport);
    }

    public void addStaticImports(Set<String> staticImports) {
	this.staticImports.addAll(staticImports);
    }

    @Override
    public String tableName(String tableName) {
	this.tableName = tableName;
	return null;
    }

    @Override
    public List<IntrospectedColumn> primaryKeyColumns(List<IntrospectedColumn> primaryKeyColumns) {
	this.primaryKeyColumns = primaryKeyColumns;
	return null;
    }

    @Override
    public List<IntrospectedColumn> baseColumns(List<IntrospectedColumn> baseColumns) {
	this.baseColumns = baseColumns;
	return null;
    }

    @Override
    public List<IntrospectedColumn> blobColumns(List<IntrospectedColumn> blobColumns) {
	this.blobColumns = blobColumns;
	return null;
    }
    public List<IntrospectedColumn> getPrimaryKeyColumns() {
	return primaryKeyColumns;
    }

    public void setPrimaryKeyColumns(List<IntrospectedColumn> primaryKeyColumns) {
	this.primaryKeyColumns = primaryKeyColumns;
    }

    public List<IntrospectedColumn> getBaseColumns() {
	return baseColumns;
    }

    public void setBaseColumns(List<IntrospectedColumn> baseColumns) {
	this.baseColumns = baseColumns;
    }

    public List<IntrospectedColumn> getBlobColumns() {
	return blobColumns;
    }

    public void setBlobColumns(List<IntrospectedColumn> blobColumns) {
	this.blobColumns = blobColumns;
    }
}
