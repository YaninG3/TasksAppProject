package com.todo.view;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.todo.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for printing item list per given user ID.
 * If a specific item ID was provided it will show only this item
 */
public class ShowTodoItems extends SimpleTagSupport {
	
	private Integer userid = null;
	private Integer itemid = null;
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	
	
	/**
	 * check if an itemid attribute was provided
	 * if it was ask the DAO for associated  TodoItem Object and print it in an HTML table
	 * if it wasn't ask DAO for entire Item List associated with the username attribute provided
	 * print the list in an HTML table
	 */
	public void doTag() throws JspException, IOException 
	{
	  JspWriter out = getJspContext().getOut();
	  IToDoListDAO dao = HibernateToDoListDAO.getInstance();
	  StringWriter buffer = new StringWriter();
	  List<TodoItem> itemList = null;
	  
	  //if item ID provided -> only that one item will be printed
	  if(itemid != null)
	  {
			try {
				TodoItem item = dao.getItemByID(itemid);
				itemList = new ArrayList<TodoItem>();
				itemList.add(item);
			} catch (TodoPlatformException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  
	  //if no item id provided , will print all item relevant to this user ID
	  else
	  {
			try {
				itemList = dao.getItemListByUserId(userid);
			} catch (TodoPlatformException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  
	  //print itemList in HTML table
	  if (itemList != null)
	  {
		  buffer.write("<table class=\"table table-striped\"><thead><tr><th>Topic</th><th>Due Date</th><th></th><th></th></tr></thead><tbody>");
		  for(int i = 0 ; i < itemList.size(); i++)
		  {
			  buffer.write("<tr>" + itemList.get(i).toHtmlString());
			  buffer.write("<td><a href='/TasksProjectB/controller/editItemPage?id=" + itemList.get(i).getId() + "'>Edit Task</a></td>");
			  buffer.write("<td><a href='/TasksProjectB/controller/removeItem?id=" + itemList.get(i).getId() + "'>Drop Task</a></td></tr>");
		  }
		  buffer.write("</tbody></table>");
		  out.print(buffer);
	  }
	}
  
}
 
