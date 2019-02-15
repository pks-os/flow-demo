package com.vaadin.flow.demo.dnd;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.demo.dnd.dashboard.Dashboard;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class MainLayout extends AppLayout implements RouterLayout {

    private final AppLayoutMenu menu;

    public MainLayout() {
        menu = createMenu();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        attachEvent.getUI().getRouter().getRegistry().getRegisteredRoutes()
                .forEach(routeData -> {
                    AppLayoutMenuItem menuItem = menu
                            .addMenuItem(routeData.getUrl());
                    menuItem.setRoute(routeData.getUrl());
                });
    }
}
