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
package com.vaadin.hummingbird.demo.webcomponent.progressbubble;

import com.vaadin.annotations.HtmlImport;
import com.vaadin.annotations.Tag;
import com.vaadin.ui.Component;

/**
 * An integration of https://github.com/tehapo/progress-bubble.
 *
 * @author Vaadin Ltd
 */
@Tag("progress-bubble")
@HtmlImport(PolyGit.BASE_URL + "progress-bubble/progress-bubble.html")
public class ProgressBubble extends Component {

    /**
     * Creates a new progress bubble with the default value (0) and max value
     * (100).
     */
    public ProgressBubble() {
        // Using default values
    }

    /**
     * Creates a new progress bubble with the given value and max value.
     *
     * @param value
     *            the initial value to use
     * @param max
     *            the max value to use
     */
    public ProgressBubble(int value, int max) {
        setValue(value);
        setMax(max);
    }

    /**
     * Sets the value.
     *
     * @param value
     *            the value to set, must be between 0 and {@link #getMax()}.
     */
    public void setValue(int value) {
        int adjustedValue = value;
        if (adjustedValue < 0) {
            adjustedValue = 0;
        }
        if (adjustedValue > getMax()) {
            adjustedValue = getMax();
        }
        getElement().setProperty("value", adjustedValue);
        getElement().setText(adjustedValue + " %");
    }

    /**
     * Gets the current value.
     *
     * @return the current value
     */
    public int getValue() {
        return getElement().getProperty("value", 0);
    }

    /**
     * Sets the max value.
     * <p>
     * The max value corresponds to 100% progress.
     *
     * @param max
     *            the max value to use
     */
    public void setMax(int max) {
        getElement().setProperty("max", max);
    }

    /**
     * Gets the max value.
     * <p>
     * The max value corresponds to 100% progress.
     *
     * @return the max value
     */
    public int getMax() {
        return getElement().getProperty("max", 100);
    }
}
