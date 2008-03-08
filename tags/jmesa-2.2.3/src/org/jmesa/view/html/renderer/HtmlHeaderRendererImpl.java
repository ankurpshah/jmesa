/*
 * Copyright 2004 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jmesa.view.html.renderer;

import org.jmesa.limit.Order;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.renderer.AbstractHeaderRenderer;

/**
 * @since 2.0
 * @author Jeff Johnston
 */
public class HtmlHeaderRendererImpl extends AbstractHeaderRenderer implements HtmlHeaderRenderer {
    private String style;
    private String styleClass;
    private boolean defaultSortOrderable = true;

    public HtmlHeaderRendererImpl(HtmlColumn column) {
        setColumn(column);
    }

    @Override
    public HtmlColumn getColumn() {
        return (HtmlColumn) super.getColumn();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * TODO: This should move to the HtmlColumn class.
     */
    @Deprecated
    public boolean isDefaultSortOrderable() {
        return defaultSortOrderable;
    }

    /**
     * TODO: This should move to the HtmlColumn class.
     */
    @Deprecated
    public void setDefaultSortOrderable(boolean defaultSortOrderable) {
        if (!defaultSortOrderable) {
            getColumn().setSortOrder(new Order[] { Order.ASC, Order.DESC });
        }

        this.defaultSortOrderable = defaultSortOrderable;
    }

    public Object render() {
        HtmlBuilder html = new HtmlBuilder();

        html.td(2);
        html.width(getColumn().getWidth());
        html.style(getStyle());
        html.styleClass(getStyleClass());

        html.append(getHeaderEditor().getValue());

        html.tdEnd();

        return html.toString();
    }
}