package com.todoadminpanel.view;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@SuppressWarnings("serial")
@Theme("todoadminpanel")
public class TodoadminpanelUI extends UI {

	private SQLContainer sessionEventContainer = null;
	private SQLContainer sessionAttributeEventContainer = null;
	private HorizontalLayout layout;
	private VerticalLayout leftLayout;
	private VerticalLayout rightLayout;
	private Button refreshBtn;
	private Button clearSessionEventsBtn;
	private Button clearAttributeEventsBtn;
	
	
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = TodoadminpanelUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		initContainers();
		initLayout();
		initButtons();
		
		Table attributesEventsTable = new Table("Sessions' Attributes Events Log");
		attributesEventsTable.setContainerDataSource(sessionAttributeEventContainer); 
		
		Table SessionEventsTable = new Table("Session Events Log");
		SessionEventsTable.setContainerDataSource(sessionEventContainer);
		
		leftLayout.addComponent(refreshBtn);
		leftLayout.addComponent(clearAttributeEventsBtn);
		leftLayout.addComponent(attributesEventsTable);
		rightLayout.addComponent(clearSessionEventsBtn);
		rightLayout.addComponent(SessionEventsTable);

	}

	private void initContainers(){
		try {
			JDBCConnectionPool pool = new SimpleJDBCConnectionPool(
			        "com.mysql.jdbc.Driver",
			        "jdbc:mysql://localhost:3306/inventory", "admin", "admin", 2, 5);
			
			TableQuery sessionAttributeEventTable = new TableQuery("sessionattributeevent", pool);
			TableQuery sessionEventTable = new TableQuery("sessionevent", pool);
			sessionAttributeEventTable.setVersionColumn("OPTLOCK");
			sessionEventTable.setVersionColumn("OPTLOCK");
			sessionAttributeEventContainer = new SQLContainer(sessionAttributeEventTable);
			sessionEventContainer = new SQLContainer(sessionEventTable);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void initLayout(){
		layout = new HorizontalLayout();
		leftLayout = new VerticalLayout();
		rightLayout = new VerticalLayout();
		
		layout.setMargin(true);
		leftLayout.setMargin(true);
		rightLayout.setMargin(true);
		setContent(layout);
		
		layout.addComponent(leftLayout);
		layout.addComponent(rightLayout);
	}
	
	private void initButtons(){
		refreshBtn = new Button("Refresh");
		clearSessionEventsBtn = new Button("Clear Session Events");
		clearAttributeEventsBtn = new Button("Clear Attributes Events");

		refreshBtn.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	sessionEventContainer.refresh();
		    	sessionAttributeEventContainer.refresh();
		    }
		});
		
		clearSessionEventsBtn.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	sessionEventContainer.removeAllItems();
		    }
		});
		
		clearAttributeEventsBtn.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	sessionAttributeEventContainer.removeAllItems();
		    }
		});
	}

}