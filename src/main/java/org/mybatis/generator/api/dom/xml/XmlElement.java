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
package org.mybatis.generator.api.dom.xml;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.dom.OutputUtilities;

/**
 * @author Jeff Butler
 */
public class XmlElement extends Element {
    private List<Attribute> attributes;

    private List<Element> elements;

    private String name;
    
    private boolean flag = true;

    /**
     *  
     */
    public XmlElement(String name) {
	super();
	attributes = new ArrayList<Attribute>();
	elements = new ArrayList<Element>();
	this.name = name;
    }

    /**
     * @return Returns the attributes.
     */
    public List<Attribute> getAttributes() {
	return attributes;
    }

    public void addAttribute(Attribute attribute) {
	attributes.add(attribute);
    }

    public void removeAttribute(Attribute attribute) {
	attributes.remove(attribute);
    }

    public void removeAttribute(int index) {
	attributes.remove(index);
    }

    /**
     * @return Returns the elements.
     */
    public List<Element> getElements() {
	return elements;
    }

    public void addElement(Element element) {
	elements.add(element);
    }

    public void addElement(int index, Element element) {
	elements.add(index, element);
    }

    public void removeElement(int index) {
	elements.remove(index);
    }

    public void clear() {
	elements.clear();
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
	return name;
    }

    private String[] ignore = { "select", "delete", "insert", "update" }; //"sql", 

    @Override
    public String getFormattedContent(int indentLevel) {
	StringBuilder sb = new StringBuilder();
	// TODO 修改xml 如果name在 ignore中得话就忽略
	if (flag) {
	    for (String string : ignore) {
		if (name != null && !name.isEmpty() && string.equalsIgnoreCase(name)) {
		    //return sb.toString();
		}
	    }
	}
	OutputUtilities.xmlIndent(sb, indentLevel);
	sb.append('<');
	sb.append(name);

	for (Attribute att : attributes) {
	    sb.append(' ');
	    sb.append(att.getFormattedContent());
	}

	if (elements.size() > 0) {
	    sb.append(" >"); //$NON-NLS-1$
	    for (Element element : elements) {
		OutputUtilities.newLine(sb);
		sb.append(element.getFormattedContent(indentLevel + 1));
	    }
	    OutputUtilities.newLine(sb);
	    OutputUtilities.xmlIndent(sb, indentLevel);
	    sb.append("</"); //$NON-NLS-1$
	    sb.append(name);
	    sb.append('>');

	} else {
	    sb.append(" />"); //$NON-NLS-1$
	}

	return sb.toString();
    }

    public void setName(String name) {
	this.name = name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
