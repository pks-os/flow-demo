/*
 * Copyright 2000-2019 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.vaadin.flow.demo.dnd.dashboard;

import java.util.Objects;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dnd.DragEndEvent;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.dnd.DragStartEvent;
import com.vaadin.flow.component.dnd.DropEffect;
import com.vaadin.flow.component.dnd.DropEvent;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.dnd.DropTargetComponent;
import com.vaadin.flow.component.dnd.EffectAllowed;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.demo.dnd.MainLayout;
import com.vaadin.flow.router.Route;

@StyleSheet("frontend://styles.css")
@Route(value = "dashboard", layout = MainLayout.class)
public class Dashboard extends FlexLayout {

    private int counter = 0;

    public Dashboard() {
        super();
        getStyle().set("flex-wrap", "wrap");

        add(createDashboardWizard());
        createNewDashboard(null, null);
        createNewDashboard(null, null);
        createNewDashboard(null, null);
    }

    private Component createDashboardWizard() {
        VerticalLayout container = createContainer();
        TextField name = new TextField("Name");
        TextArea details = new TextArea("Details");
        Button button = new Button("Add new Dashboard", event -> {
            createNewDashboard(name.getValue(), details.getValue());
        });

        container.add(name, details, button);
        return container;
    }

    private void createNewDashboard(String name, String details) {
        if (name == null || name.isEmpty()) {
            name = "Foobar " + counter++;
        }
        if (details == null || details.isEmpty()) {
            details = "Το Lorem Ipsum είναι απλά ένα κείμενο χωρίς νόημα για "
                    + "τους επαγγελματίες της τυπογραφίας και στοιχειοθεσίας.";
        }
        VerticalLayout container = createContainer();
        Paragraph paragraph = new Paragraph(details);
        paragraph.getStyle().set("text-overflow", "ellipsis");
        container.add(new H2(name), paragraph);

        DragSource<VerticalLayout> dragSource = DragSource.of(container);
        dragSource.setEffectAllowed(EffectAllowed.MOVE);

        DropTarget<VerticalLayout> dropTarget = DropTarget.of(container);
        dropTarget.setDropEffect(DropEffect.MOVE);

        dragSource
                .addDragStartListener(event -> onDragStart(event, dropTarget));
        dragSource.addDragEndListener(event -> onDragEnd(event, dropTarget));
        dropTarget.addDropListener(this::onDrop);

        add(container);
    }

    private VerticalLayout createContainer() {
        VerticalLayout container = new VerticalLayout();
        container.setMargin(true);
        container.setHeight("300px");
        container.setWidth("300px");
        container.getStyle().set("border", "solid 1px #999");
        return container;
    }

    private void onDragStart(DragStartEvent<? extends Component> dragStartEvent,
            DropTarget<VerticalLayout> dropTarget) {
        // do not allow dropping on top if itself
        dropTarget.setDropEffect(DropEffect.NONE);
    }

    private void onDragEnd(DragEndEvent<? extends Component> dragEndEvent,
            DropTarget<VerticalLayout> dropTarget) {
        // clear blocking of drops
        dropTarget.setDropEffect(null);
    }

    private void onDrop(DropEvent<? extends Component> dropEvent) {
        if (dropEvent.getDragSourceComponent().isPresent()) {
            Component target = dropEvent.getComponent();
            Component source = dropEvent.getDragSourceComponent().get();

            int targetIndex = indexOf(target);
            remove(target);
            int sourceIndex = indexOf(source);
            replace(source, target);
            addComponentAtIndex(targetIndex, source);
        }
    }
}
