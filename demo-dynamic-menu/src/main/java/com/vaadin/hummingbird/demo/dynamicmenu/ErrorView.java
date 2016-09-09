/*
 * Copyright 2000-2016 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.hummingbird.demo.dynamicmenu;

import com.vaadin.hummingbird.dom.ElementFactory;
import com.vaadin.hummingbird.html.Div;
import com.vaadin.hummingbird.router.LocationChangeEvent;
import com.vaadin.hummingbird.router.View;

/**
 * An error view showing a message with the wrong location to the user.
 */
public class ErrorView extends Div implements View {

    @Override
    public void onLocationChange(LocationChangeEvent locationChangeEvent) {
        getStyle().set("textAlign", "center");
        getElement().appendChild(
                ElementFactory.createHeading1("404 - Page not found"),
                ElementFactory.createSpan("Please check the location "),
                ElementFactory.createEmphasis(
                        locationChangeEvent.getLocation().getPath()),
                ElementFactory.createSpan(" and try again."));
    }
}