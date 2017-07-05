package com.todo.model;

import java.util.List;

public interface IToDoListDAO {
	
	/** 
	 * TodoItem DAO API 
	 */
	public Integer createItem(TodoItem item) throws TodoPlatformException;
	public List<TodoItem> readItemList() throws TodoPlatformException;
	public List<TodoItem> getItemListByUsername(String username) throws TodoPlatformException;
	public List<TodoItem> getItemListByUserId(int userId) throws TodoPlatformException;
	public Boolean replaceItem(TodoItem item, Integer itemID) throws TodoPlatformException;
	public Boolean deleteItem(Integer id) throws TodoPlatformException;
	public TodoItem getItemByID(Integer id) throws TodoPlatformException;
	public Boolean deleteAllItems() throws TodoPlatformException;
	
	/** 
	 * User DAO API
	 */
	public Integer createUser(User user) throws TodoPlatformException;
	public List<User> readUserList() throws TodoPlatformException;
	public Boolean replaceUser(User user, Integer id) throws TodoPlatformException;
	public Boolean deleteUser(Integer id) throws TodoPlatformException;
	public User findUserByID(Integer id) throws TodoPlatformException;
	public void deleteAllUsers() throws TodoPlatformException;
	public User getUserByUsername(String username) throws TodoPlatformException;
	public Integer getUserIdByUsername(String username) throws TodoPlatformException;
}
