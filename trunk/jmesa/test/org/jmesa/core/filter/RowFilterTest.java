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
package org.jmesa.core.filter;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jmesa.core.Name;
import org.jmesa.core.President;
import org.jmesa.core.PresidentDao;
import org.jmesa.limit.Limit;
import org.jmesa.limit.LimitFactory;
import org.jmesa.limit.LimitFactoryImpl;
import org.jmesa.test.AbstractTestCase;
import org.jmesa.test.ParametersAdapter;
import org.jmesa.test.ParametersBuilder;
import org.jmesa.web.WebContext;
import org.junit.Test;

/**
 * @since 2.0
 * @author Jeff Johnston
 */
public class RowFilterTest extends AbstractTestCase {
    @Test
    public void filterItems() {
        FilterMatchRegistry registry = new FilterMatchRegistryImpl();
        MatchKey key = new MatchKey(String.class);
        FilterMatch match = new StringFilterMatch();
        registry.addFilterMatch(key, match);

        RowFilter itemsFilter = new SimpleRowFilter(registry);

        WebContext webContext = createWebContext();
        
        Map<String, Object> results = new HashMap<String, Object>();
        ParametersAdapter parametersAdapter = new ParametersAdapter(results);
        ParametersBuilder builder = new ParametersBuilder(ID, parametersAdapter);
        builder.addFilter("name.fullName", "george");
        webContext.setParameterMap(results);
        
        LimitFactory limitFactory = new LimitFactoryImpl(ID, webContext);
        Limit limit = limitFactory.createLimit();

        PresidentDao dao = new PresidentDao();
        Collection<Object> items = dao.getPresidents();
        items = itemsFilter.filterItems(items, limit);

        assertTrue(items.size() == 3);
    }

    @Test
    @SuppressWarnings({"unchecked"})
    public void filterNullItems() {
        FilterMatchRegistry registry = new FilterMatchRegistryImpl();
        MatchKey key = new MatchKey(String.class);
        FilterMatch match = new StringFilterMatch();
        registry.addFilterMatch(key, match);

        RowFilter itemsFilter = new SimpleRowFilter(registry);

        WebContext webContext = createWebContext();
        
        Map<String, Object> results = new HashMap<String, Object>();
        ParametersAdapter parametersAdapter = new ParametersAdapter(results);
        ParametersBuilder builder = new ParametersBuilder(ID, parametersAdapter);
        builder.addFilter("name.firstName", "James");
        webContext.setParameterMap(results);
        
        LimitFactory limitFactory = new LimitFactoryImpl(ID, webContext);
        Limit limit = limitFactory.createLimit();

        Collection<Object> items = new ArrayList<Object>();
        
        President president = new President();
        Name  name = new Name("James", "Monroe");
        president.setName(name);
        items.add(president);

        president = new President();
        name = new Name(null, "Washington"); // The null object
        president.setName(name);
        items.add(president);

        president = new President();
        name = new Name("James", "Madison");
        president.setName(name);
        items.add(president);
        
        president = new President();
        name = new Name("Andrew", "Jackson");
        president.setName(name);
        items.add(president);

        items = itemsFilter.filterItems(items, limit);

        assertTrue(items.size() == 2);
    }
}
