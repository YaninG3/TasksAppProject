package com.todo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.model.*;

/**
 * Servlet implementation class ServletController
 */
@WebServlet({ "/controller/*"})
public class ServletController extends HttpServlet {
	//default message attributes names:
	private static final String errorMsg = "errorMsg";
	private static final String msg = "msg";
	
	private static final long serialVersionUID = 1L;
	private IToDoListDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     * get a DAO instance
     * create a logger associated with this class
     */
    public ServletController() {
        super();
        dao = HibernateToDoListDAO.getInstance();
    }
    
    /**
     * doGet will pass the request handling to processRequest method
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		processRequest(request, response);
	}

	/**
     * doPost will pass the request handling to processRequest method
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		processRequest(request, response);
	}
    /**
     * processRequest will check the URL's end and direct to the proper handling method.
     * each method will return a RequestDispatcher that will be activated with forward 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		String url = request.getRequestURL().toString();
		RequestDispatcher dispatcher = null;
		
		if(url.endsWith("homepage")){
			dispatcher = homepage(request,response);
		}
		else if(url.endsWith("loginPage")){
			dispatcher = loginPage(request,response);
		}
		else if(url.endsWith("addItemPage")){
			dispatcher = addItemPage(request,response);
		}
		else if(url.endsWith("registrationPage")){
			dispatcher = registrationPage(request,response);
		}
		else if(url.endsWith("editUserPage")){
			dispatcher = editUserPage(request,response);
		}
		else if(url.endsWith("welcome")){
			dispatcher = welcome(request,response);
		}		
		else if(url.endsWith("loginAction")){
			dispatcher = loginAction(request,response);
		}
		else if(url.endsWith("registrationAction")){
			dispatcher = registrationAction(request,response);
		}
		else if(url.endsWith("editItemAction")){
			dispatcher = editItemAction(request,response);
		}
		else if(url.endsWith("editItemPage")){
			dispatcher = editItemPage(request,response);
		}
		else if(url.endsWith("addItemAction")){
			dispatcher = addItemAction(request,response);
		}
		else if(url.endsWith("removeItem")){
			dispatcher = removeItem(request,response);
		}
		else if(url.endsWith("logoff")){
			dispatcher = logoff(request,response);
		}
		else if(url.endsWith("editUserAction")){
			dispatcher = editUserAction(request,response);
		}
		else{
			dispatcher = errorPage(request,response); 
		}
		
			dispatcher.forward(request, response);
    }
    
    /**
     * Will try to load browser cookie relevant to the application.
     * if one exist, will try to load the relevant {@link User} object and attach it to the session
     * navigate directly the homepage.
     * if the cookie doesn't exist, will navigate to login page
     * 
     * @param request
     * @param response
     * @return RequestDispatcher
     */
    
    private RequestDispatcher loginPage(HttpServletRequest request, HttpServletResponse response){
    	return getServletContext().getRequestDispatcher("/login.jsp");
    }
    
    private RequestDispatcher errorPage(HttpServletRequest request, HttpServletResponse response){
    	if (request.getAttribute(msg) == null){
    		request.setAttribute(msg, "Oops... somthing went wrong");
    	}
    	return getServletContext().getRequestDispatcher("/errorPage.jsp");
    }
    
    private RequestDispatcher homepage(HttpServletRequest request, HttpServletResponse response){
    	if (request.getAttribute(msg) == null){
    		request.setAttribute(msg, "There's no place like home!");
    	}
    	return getServletContext().getRequestDispatcher("/homepage.jsp");
    }
    
    private RequestDispatcher addItemPage(HttpServletRequest request, HttpServletResponse response){
    	if (request.getAttribute(msg) == null){
    		request.setAttribute(msg, "Let's try somthing new");
    	}
		return getServletContext().getRequestDispatcher("/addItem.jsp");
    }
	private RequestDispatcher registrationPage(HttpServletRequest request, HttpServletResponse response){
		if (request.getAttribute(msg) == null){
			request.setAttribute(msg, "Let's start by signing your details");
		}
		return getServletContext().getRequestDispatcher("/registration.jsp");
	
	}
	private RequestDispatcher editUserPage(HttpServletRequest request, HttpServletResponse response){
		if (request.getAttribute(msg) == null){
			request.setAttribute(msg, "Sometimes a little change is a good thing");
		}
		return getServletContext().getRequestDispatcher("/editUser.jsp");
	}
	
    private RequestDispatcher welcome(HttpServletRequest request, HttpServletResponse response){
    	Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie ob : cookies)
			{
				//loop through cookies array until the TodoProjectUserid cookie found
				if(ob.getName().equals("TodoProjectUserid"))
				{
					Integer userid = Integer.parseInt(ob.getValue());
					try
					{
						User user = dao.findUserByID(userid);
						request.getSession().setAttribute("user", user);
		    			request.setAttribute(msg, "Welcome back, " + user.getFirstName());
		    			return homepage(request, response);
					}
					catch (TodoPlatformException e)
					{
						e.printStackTrace();
						//if for any reason the user loading fails, go to the login page
						//error message will be viewed to the user
						request.setAttribute(errorMsg, e.getMessage());
						return loginPage(request, response);
					}
				}
			}
		}
		//if the cookie doesn't exist, will navigate to login page
		request.setAttribute(msg, "Ahoy! would you like to sign in?");
		return loginPage(request, response);
    }
    
    /**
     * 
     * get user ID by session attribute user object
     * created a new user with the values from the edit page
     * ask DAO to replace the user in the database with the new one
     * attach the new user to the session attribute "user"
     * attach appropriate message to the msg attribute"
     * return dispatcher to homepage
     *
     */
    private RequestDispatcher editUserAction(HttpServletRequest request, HttpServletResponse response){
    	String firstname = request.getParameter("firstname");
    	String lastname = request.getParameter("lastname");
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	//check if firstname, lastname, username - all hold valid characters
    	if(!isAlphaNumeric(firstname) || !isAlphaNumeric(lastname) || !isAlphaNumeric(username)){
    		request.setAttribute(errorMsg, "Hold it...! letters and numbers only!");
    		return editUserPage(request, response);
    	}
    	
    	User user = (User)request.getSession().getAttribute("user");
    		
    	user.setFirstName(firstname);
    	user.setLastName(lastname);
    	user.setUserName(username);
    	
    	//input validation
    	if(!user.getPassword().equals(password)) 
    	{
    		String hashPassword = User.hashFunction(request.getParameter("password")); 
    		user.setPassword(hashPassword);
    	}
    	
    	try{
    		dao.replaceUser(user, user.getId());
    		request.getSession().setAttribute("user", user); //not sure if the action is needed
    		request.setAttribute(msg, "Got your new user information ;)");
    		return getServletContext().getRequestDispatcher("/homepage.jsp");
    	}
    	catch(TodoPlatformException e){
    		e.printStackTrace();
    		request.setAttribute(errorMsg, e.getMessage());
    		return getServletContext().getRequestDispatcher("/homepage.jsp");
    	}
    }
    
    /**
     * 
     * attach null to the session attribute "user" (delete the attribute content)
     * delete the cookie
     * attach a message to request attribute msg
     * return request dispatcher with login page
     * 
     * @param request
     * @param response
     * @return RequestDispatcher
     */
    private RequestDispatcher logoff(HttpServletRequest request, HttpServletResponse response){
    	//deleting the "user" attribute
    	request.getSession().setAttribute("user", null);
    	
    	//deleting the cookie
    	Cookie killMyCookie  = new Cookie("TodoProjectUserid", null);
    	killMyCookie.setPath("/TasksProjectB/");
    	killMyCookie .setMaxAge(0); //delete the cookie
    	response.addCookie(killMyCookie);
    	
    	request.setAttribute(msg, "Hate to see you leave like that...");
    	return loginPage(request, response);
    }
    
    /**
     * 
     * get parameter id from request
     * ask DAO to remove the item by the id
     * set the msg Attribute accordingly
     * return the request dispatcher with homepage's page 
     * @param request
     * @param response
     * @return RequestDispatcher
     */
    private RequestDispatcher removeItem(HttpServletRequest request, HttpServletResponse response)
    {
    	String itemId = request.getParameter("id");
    	try{
    		dao.deleteItem(Integer.valueOf(itemId));
    		request.setAttribute(msg, "Task was dropped");
    		return homepage(request, response);
    	}
    	catch(TodoPlatformException e)
    	{
    		e.printStackTrace();
    		request.setAttribute(errorMsg, e.getMessage());
    		return errorPage(request, response);
    	}
    }
    
    /**
     * addItem will generate a new To-do Item according the parameters received.
     * ask the DAO to update the Database and will return a RequestDispatcher to the Homepage
     * @param request
     * @param response
     * @return RequestDispatcher
     */
    private RequestDispatcher addItemAction(HttpServletRequest request, HttpServletResponse response)
    {
    	String subject = request.getParameter("subject");
    	String date = request.getParameter("date");
    	
    	//input validation
    	if(!isAlphaNumericAndSpace(subject) || !isAlphaNumericAndSpace(date))
    	{
    		request.setAttribute(errorMsg, "Hold it...! letters and numbers only!");
    		return addItemPage(request, response);
    	}
    	
    	Integer userId = ((User)request.getSession().getAttribute("user")).getId();
    	TodoItem item = new TodoItem(subject, date, userId);
    	
    	//ask the DAO to submit the new item to the database
    	try
    	{
    		dao.createItem(item);
    		request.setAttribute(msg, "Task Added");
    		return homepage(request, response);
    	}
    	catch(TodoPlatformException e){
    		e.printStackTrace();
    		request.setAttribute(errorMsg, e.getMessage());
    		return errorPage(request, response);
    	}
    }
    
    /**
     * will get the To-Do Item according the ID parameter received (hidden).
     * set the changes in to it and submit it to the Database using the DAO
     * will return to the homepage 
     * @param request
     * @param response
     * @return RequestDispatcher
     */
    private RequestDispatcher editItemAction(HttpServletRequest request, HttpServletResponse response)
    {
    	String subject = request.getParameter("subject");
    	String date = request.getParameter("date");
    	
    	//input validation
    	if(!isAlphaNumericAndSpace(subject) || !isAlphaNumericAndSpace(date))
    	{
    		request.setAttribute(errorMsg, "Hold it...! letters and numbers only!");
    		return editItemPage(request, response);
    	}
    	
    	//get hidden parameter "id"
    	Integer id = Integer.parseInt(request.getParameter("id"));
    	
    	//change item's values and merge it back to database using the DAO
    	try
    	{
    		TodoItem item = dao.getItemByID(id);
			item.setSubject(subject);
			item.setDate(date);
    		dao.replaceItem(item, id);
    		request.setAttribute(msg, "Task was modified");
    		return homepage(request, response);
    	}
    	catch(TodoPlatformException e)
    	{
    		e.printStackTrace();
			request.setAttribute(msg, e.getMessage());
			return errorPage(request, response);
    	}
    }
    
    /**
     * will read the item ID inside the URL
     * ask the DAO for the Item related
     * attach it to the request attribute "item".
     * navigate to the editItem Page
     * @param request
     * @param response
     * @return RequestDispatcher
     */
    private RequestDispatcher editItemPage(HttpServletRequest request, HttpServletResponse response)
    {
    	Integer id = Integer.parseInt(request.getParameter("id"));
    	try
    	{
    		TodoItem item = dao.getItemByID(id);
    		if (item != null)
    		{
        		request.setAttribute("item", item);
        		return getServletContext().getRequestDispatcher("/editItem.jsp");
    		}
    		else
    		{
    			request.setAttribute(errorMsg, "item id=" + id + " was not found");
    			return errorPage(request, response);
    		}
    	}
    	catch(TodoPlatformException e)
    	{
    		e.printStackTrace();
			request.setAttribute(errorMsg, e.getMessage());
			return errorPage(request, response);
    	}
    }
    
    /**
     * check if a user exists by the given username parameter and ask the DAO for related User object if it does else return null
     * check if the password parameter received is equal to the User's password in the DB.
     * return the User object if it's a match, otherwise return null;
     * @param username
     * @param password
     * @return User
     * @throws TodoPlatformException
     */
    private User authentication(String username, String password, HttpServletRequest request) throws TodoPlatformException
    {
    		//if username does not exists getUserByUsername will return null
    		//TodoPlatformException should be handled by authentication() caller
    		User user = dao.getUserByUsername(username);
        	
    		//check if a User object was retrieved by the username input
    		if(user == null)
    		{
        		request.setAttribute(errorMsg, "Oh... can't find this username");
        		return null;
    		}
    		
    		//get the hash value for the input password
        	String sessionHashPassword = User.hashFunction(password);
        	
        	//verify if hash input password is equal to user's hash password in the database
        	if(sessionHashPassword.equals(user.getPassword()) == false)
        	{
        		request.setAttribute(errorMsg, "Oops! that password is incorrect");
            	return null;
        	}
        	
        	//if all went through okay return the User object
        	return user;
    }
    
    /**
     * if "username" and "password" parameters were left empty attach a message and return the login page Dispatcher
     * ask authentication method for User object by sending the "username" and "password" parameters.
     * if a User object was received attach it to the session attribute "user", set a cookie with user ID
     * attach a welcome message to attribute msg and return dispatcher to the homepage
     * otherwise, attach an error message to attribute msg and return a Dispatcher to login page 
     * @param request
     * @param response
     * @return RequestDispatcher
     */
    private RequestDispatcher loginAction(HttpServletRequest request, HttpServletResponse response){
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	//input validation
    	if(!isAlphaNumeric(username))
    	{
    		request.setAttribute(errorMsg, "Hold it...! letters and numbers only!");
    		return loginPage(request, response);
    	}
    	
    	try
    	{
    		//authentication() throws TodoPlatformException
    		User user = authentication(username, password, request);
    		if(user != null){
    			addNewCookie(response, String.valueOf(user.getId()));
    		
    			request.getSession().setAttribute("user", user);
    			request.setAttribute(msg, "What be happenin' " + user.getFirstName() + "?");
    		
    			return homepage(request, response);
    		}
    		//error message attribute is set within the authentication method
    		return loginPage(request, response);
    	}
    	catch(TodoPlatformException e)
    	{
    		e.printStackTrace();
    		request.setAttribute(errorMsg, e.getMessage());
    		return errorPage(request, response);
    	}
    }
    
    /**
     * check if username already exists by asking the DAO for a userID by the given username parameter
     * if username is exists attach appropriate meaage to msg attribute
     * otherwise create a new User Object with the given parameters
     * ask DAO to add it to the Database
     * attach it to the Session attribute "user", and attach "Registration Success" message to the msg attribute
     * return a Dispatcher to the homepage
     * @param request
     * @param response
     * @return RequestDispatcher
     */
    private RequestDispatcher registrationAction(HttpServletRequest request, HttpServletResponse response)
    {
    	String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
    	
    	//input validation
    	if(!isAlphaNumeric(firstname) || !isAlphaNumeric(lastname) || !isAlphaNumeric(username) || !isAlphaNumeric(password))
    	{
    		request.setAttribute(errorMsg, "Hold it...! letters and numbers only!");
    		return getServletContext().getRequestDispatcher("/registration.jsp");
    	}
    	
    	try
    	{	
    		//getUserIdByUsername(username) will return null if "username" doesn't exist
    		Integer userID = dao.getUserIdByUsername(username);
    		
    		//if no such user exists then reload the page and present appropriate message
    		if(userID != null)
    		{
    			request.setAttribute(errorMsg, "Sorry... username is already in use");
    			return getServletContext().getRequestDispatcher("/registration.jsp");
    		}
    		
    		//create a new User object by the given values
    		User user = new User(firstname, lastname, username, password);
		
    		//create the new user in database
			dao.createUser(user);
			
			//add new cookie with the user ID
			addNewCookie(response, String.valueOf(user.getId()));
			
			//attach new user to the current session
			request.getSession().setAttribute("user", user);
			request.setAttribute(msg, "Hurray! " + user.getFirstName() + ", you're now a registered user, add your first task!");
			
			//navigate to homepage
			return getServletContext().getRequestDispatcher("/homepage.jsp");
		}
		catch(TodoPlatformException e){
			e.printStackTrace();
			request.setAttribute(errorMsg, e.getMessage());
			return getServletContext().getRequestDispatcher("/errorPage.jsp");
		}
    }
    
    /**
     * private method which configure what sort of cookie is being save
     * and also save the cookie in the browser 
     * @param response
     * @param userid
     */
    private void addNewCookie(HttpServletResponse response, String userid){
    	Cookie cookie = new Cookie("TodoProjectUserid", userid);
    	cookie.setPath("/TasksProjectB/");
    	cookie.setMaxAge(30*60); //half an hour
    	response.addCookie(cookie);
    }
    
    /**
     * checks if the provided String contains only AlphaNumeric characters
     * @param string
     * @return true/false
     */
    private Boolean isAlphaNumeric(String str)
    {
        for (int i=0; i<str.length(); i++)
        {
            char c = str.charAt(i);
            if (!Character.isDigit(c) && !Character.isLetter(c))
                return false;
        }

        return true;
    }
    
    /**
     * checks if the provided String contains only AlphaNumeric characters or/and space character
     * @param string
     * @return true/false
     */
    private Boolean isAlphaNumericAndSpace(String str)
    {
        for (int i=0; i<str.length(); i++)
        {
            char c = str.charAt(i);
            if (!Character.isDigit(c) && !Character.isLetter(c) && !Character.isSpaceChar(c))
                return false;
        }

        return true;
    }
}
