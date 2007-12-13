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
package org.jmesa.view.html.toolbar;

import static org.jmesa.view.html.HtmlConstants.TOOLBAR_IMAGE;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_IMAGE_CLEAR;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_IMAGE_FILTER;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_IMAGE_FIRST_PAGE;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_IMAGE_FIRST_PAGE_DISABLED;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_IMAGE_LAST_PAGE;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_IMAGE_LAST_PAGE_DISABLED;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_IMAGE_NEXT_PAGE;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_IMAGE_NEXT_PAGE_DISABLED;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_IMAGE_PREV_PAGE;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_IMAGE_PREV_PAGE_DISABLED;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_IMAGE_SEPARATOR;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_TEXT_CLEAR;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_TEXT_FILTER;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_TEXT_FIRST_PAGE;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_TEXT_LAST_PAGE;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_TEXT_NEXT_PAGE;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_TEXT_PREV_PAGE;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_TOOLTIP;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_TOOLTIP_CLEAR;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_TOOLTIP_FILTER;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_TOOLTIP_FIRST_PAGE;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_TOOLTIP_LAST_PAGE;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_TOOLTIP_NEXT_PAGE;
import static org.jmesa.view.html.HtmlConstants.TOOLBAR_TOOLTIP_PREV_PAGE;

import org.apache.commons.lang.StringUtils;
import org.jmesa.core.CoreContext;
import org.jmesa.view.html.HtmlUtils;
import org.jmesa.web.WebContext;

/**
 * @since 2.0
 * @author Jeff Johnston
 */
public class ToolbarItemFactoryImpl implements ToolbarItemFactory {
    private String imagesPath;
    private CoreContext coreContext;

    public ToolbarItemFactoryImpl(WebContext webContext, CoreContext coreContext) {
        this.imagesPath = HtmlUtils.imagesPath(webContext, coreContext);
        this.coreContext = coreContext;
    }

    public ImageItem createFirstPageItem() {
        ImageItemImpl item = new ImageItemImpl();
        item.setCode(ToolbarItemType.FIRST_PAGE_ITEM.valueOfCode());
        item.setTooltip(coreContext.getMessage(TOOLBAR_TOOLTIP_FIRST_PAGE));
        item.setDisabledImage(getImage(TOOLBAR_IMAGE_FIRST_PAGE_DISABLED));
        item.setImage(getImage(TOOLBAR_IMAGE_FIRST_PAGE));
        item.setAlt(coreContext.getMessage(TOOLBAR_TEXT_FIRST_PAGE));
        item.setStyle("border:0");

        ToolbarItemRenderer renderer = new FirstPageItemRenderer(item, coreContext);
        renderer.setOnInvokeAction("onInvokeAction");
        item.setToolbarItemRenderer(renderer);

        return item;
    }

    public ImageItem createPrevPageItem() {
        ImageItemImpl item = new ImageItemImpl();
        item.setCode(ToolbarItemType.PREV_PAGE_ITEM.valueOfCode());
        item.setTooltip(coreContext.getMessage(TOOLBAR_TOOLTIP_PREV_PAGE));
        item.setDisabledImage(getImage(TOOLBAR_IMAGE_PREV_PAGE_DISABLED));
        item.setImage(getImage(TOOLBAR_IMAGE_PREV_PAGE));
        item.setAlt(coreContext.getMessage(TOOLBAR_TEXT_PREV_PAGE));
        item.setStyle("border:0");

        ToolbarItemRenderer renderer = new PrevPageItemRenderer(item, coreContext);
        renderer.setOnInvokeAction("onInvokeAction");
        item.setToolbarItemRenderer(renderer);

        return item;
    }

    public ImageItem createNextPageItem() {
        ImageItemImpl item = new ImageItemImpl();
        item.setCode(ToolbarItemType.NEXT_PAGE_ITEM.valueOfCode());
        item.setTooltip(coreContext.getMessage(TOOLBAR_TOOLTIP_NEXT_PAGE));
        item.setDisabledImage(getImage(TOOLBAR_IMAGE_NEXT_PAGE_DISABLED));
        item.setImage(getImage(TOOLBAR_IMAGE_NEXT_PAGE));
        item.setAlt(coreContext.getMessage(TOOLBAR_TEXT_NEXT_PAGE));
        item.setStyle("border:0");

        ToolbarItemRenderer renderer = new NextPageItemRenderer(item, coreContext);
        renderer.setOnInvokeAction("onInvokeAction");
        item.setToolbarItemRenderer(renderer);

        return item;
    }

    public ImageItem createLastPageItem() {
        ImageItemImpl item = new ImageItemImpl();
        item.setCode(ToolbarItemType.LAST_PAGE_ITEM.valueOfCode());
        item.setTooltip(coreContext.getMessage(TOOLBAR_TOOLTIP_LAST_PAGE));
        item.setDisabledImage(getImage(TOOLBAR_IMAGE_LAST_PAGE_DISABLED));
        item.setImage(getImage(TOOLBAR_IMAGE_LAST_PAGE));
        item.setAlt(coreContext.getMessage(TOOLBAR_TEXT_LAST_PAGE));
        item.setStyle("border:0");

        ToolbarItemRenderer renderer = new LastPageItemRenderer(item, coreContext);
        renderer.setOnInvokeAction("onInvokeAction");
        item.setToolbarItemRenderer(renderer);

        return item;
    }

    public ImageItem createFilterItem() {
        ImageItemImpl item = new ImageItemImpl();
        item.setCode(ToolbarItemType.FILTER_ITEM.valueOfCode());
        item.setTooltip(coreContext.getMessage(TOOLBAR_TOOLTIP_FILTER));
        item.setImage(getImage(TOOLBAR_IMAGE_FILTER));
        item.setAlt(coreContext.getMessage(TOOLBAR_TEXT_FILTER));
        item.setStyle("border:0");

        ToolbarItemRenderer renderer = new FilterItemRenderer(item, coreContext);
        renderer.setOnInvokeAction("onInvokeAction");
        item.setToolbarItemRenderer(renderer);

        return item;
    }

    public ImageItem createClearItem() {
        ImageItemImpl item = new ImageItemImpl();
        item.setCode(ToolbarItemType.CLEAR_ITEM.valueOfCode());
        item.setTooltip(coreContext.getMessage(TOOLBAR_TOOLTIP_CLEAR));
        item.setImage(getImage(TOOLBAR_IMAGE_CLEAR));
        item.setAlt(coreContext.getMessage(TOOLBAR_TEXT_CLEAR));
        item.setStyle("border:0");

        ToolbarItemRenderer renderer = new ClearItemRenderer(item, coreContext);
        renderer.setOnInvokeAction("onInvokeAction");
        item.setToolbarItemRenderer(renderer);

        return item;
    }

    public MaxRowsItem createMaxRowsItem() {
        MaxRowsItemImpl item = new MaxRowsItemImpl();
        item.setCode(ToolbarItemType.MAX_ROWS_ITEM.valueOfCode());

        MaxRowsItemRenderer renderer = new MaxRowsItemRenderer(item, coreContext);
        renderer.setOnInvokeAction("onInvokeAction");
        item.setToolbarItemRenderer(renderer);

        return item;
    }

    public ImageItem createExportItem(ToolbarExport export) {
        ImageItemImpl item = new ImageItemImpl();
        item.setCode(ToolbarItemType.EXPORT_ITEM.valueOfCode());

        item.setTooltip(getExportTooltip(export));
        item.setImage(imagesPath + getExportImage(export));

        item.setAlt(export.getText());
        item.setStyle("border:0");

        ToolbarItemRenderer renderer = new ExportItemRenderer(item, export, coreContext);
        renderer.setOnInvokeAction("onInvokeExportAction");
        item.setToolbarItemRenderer(renderer);

        return item;
    }

    public ImageItem createSeparatorItem() {
        ImageItemImpl item = new SeparatorItem();

        item.setImage(getImage(TOOLBAR_IMAGE_SEPARATOR));
        item.setAlt("Separator");
        item.setStyle("border:0");

        return item;
    }

    protected String getImage(String image) {
        return imagesPath + coreContext.getPreference(image);
    }

    protected String getExportImage(ToolbarExport export) {
        String image = export.getImage();
        if (StringUtils.isNotBlank(image)) {
            return image;
        }

        image = coreContext.getPreference(TOOLBAR_IMAGE + export.getType());

        return image;
    }

    protected String getExportTooltip(ToolbarExport export) {
        String tooltip = export.getTooltip();
        if (StringUtils.isNotBlank(tooltip)) {
            return tooltip;
        }

        tooltip = coreContext.getMessage(TOOLBAR_TOOLTIP + export.getType());

        return tooltip;
    }
}