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

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.config.Config;

import java.util.*;

import static org.mybatis.generator.api.dom.OutputUtilities.calculateImports;
import static org.mybatis.generator.api.dom.OutputUtilities.newLine;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * @author Jeff Butler
 */
public class TopLevelClass extends InnerClass implements CompilationUnit {
    private Set<FullyQualifiedJavaType> importedTypes;

    private Set<String> staticImports;

    private List<String> fileCommentLines;

    private String tableName;

    private String[] lombokImport = {"import lombok.Data;\n", "import lombok.NoArgsConstructor;\n"};

    private String[] javaImport = {"import javax.persistence.*;\n"};

    private List<IntrospectedColumn> primaryKeyColumns;
    private List<IntrospectedColumn> baseColumns;
    private List<IntrospectedColumn> blobColumns;

    /**
     *
     */
    public TopLevelClass(FullyQualifiedJavaType type) {
        super(type);
        importedTypes = new TreeSet<FullyQualifiedJavaType>();
        fileCommentLines = new ArrayList<String>();
        staticImports = new TreeSet<String>();
    }

    public TopLevelClass(String typeName) {
        this(new FullyQualifiedJavaType(typeName));
    }

    /**
     * @return Returns the importedTypes.
     */
    public Set<FullyQualifiedJavaType> getImportedTypes() {
        return Collections.unmodifiableSet(importedTypes);
    }

    public void addImportedType(String importedType) {
        addImportedType(new FullyQualifiedJavaType(importedType));
    }

    public void addImportedType(FullyQualifiedJavaType importedType) {
        if (importedType != null && importedType.isExplicitlyImported() && !importedType.getPackageName().equals(getType().getPackageName())) {
            importedTypes.add(importedType);
        }
    }

    public void removeImportedType(FullyQualifiedJavaType importedType) {
        if (importedType != null && importedType.isExplicitlyImported() && !importedType.getPackageName().equals(getType().getPackageName())) {
            importedTypes.remove(importedType);
        }
    }

    public String getFormattedContent() {
        StringBuilder sb = new StringBuilder();

        for (String fileCommentLine : fileCommentLines) {
            sb.append(fileCommentLine);
            newLine(sb);
        }

        if (stringHasValue(getType().getPackageName())) {
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

        //TODO lombok import
        if (Config.lombokImport) {
            for (String string : lombokImport) {
                sb.append(string);
                newLine(sb);
            }
        }

        //TODO java import
        if (Config.javaImport) {
            for (String string : javaImport) {
                sb.append(string);
                newLine(sb);
            }
        }

        if (importStrings.size() > 0) {
            newLine(sb);
        }

        //TODO table注解
        if (Config.tableAnnotation) {
            if (tableName != null && !tableName.isEmpty()) {
                sb.append("@Data" + "\n" + "@NoArgsConstructor\n" + "@Table(name = \"" + tableName.toLowerCase() + "\")\n");
            }
        }
        sb.append(super.getFormattedContent(0));

        return sb.toString();
    }

    public boolean isJavaInterface() {
        return false;
    }

    public boolean isJavaEnumeration() {
        return false;
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

    @Override
    public List<IntrospectedColumn> primaryKeyColumns(List<IntrospectedColumn> primaryKeyColumns) {
        this.primaryKeyColumns = primaryKeyColumns;
        super.primaryKeyColumns = primaryKeyColumns;
        return null;
    }

    @Override
    public List<IntrospectedColumn> baseColumns(List<IntrospectedColumn> baseColumns) {
        this.baseColumns = baseColumns;
        super.baseColumns = baseColumns;
        return null;
    }

    @Override
    public List<IntrospectedColumn> blobColumns(List<IntrospectedColumn> blobColumns) {
        super.blobColumns = blobColumns;
        this.blobColumns = blobColumns;
        return null;
    }

}
