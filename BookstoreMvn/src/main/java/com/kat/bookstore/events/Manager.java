package com.kat.bookstore.events;

import java.util.*;

import com.kat.bookstore.domain.*;


public class Manager {

	private Book b;

	private List processes = new ArrayList();

	public Book getBook() {
		return b;
	}

	public void setBook(Book b) {
		this.b = b;
	}

	public synchronized void addProcess(IBookProcesses process) {
		processes.add(process);
	}

	public synchronized void removeProcess(IBookProcesses process) {
		processes.remove(process);
	}

	public synchronized void executeProcesses(List<Book> list) {
		for (Book b : list) {
			setBook(b);
			BookEvent event = new BookEvent(this, b);
			Iterator proc = processes.iterator();
			while (proc.hasNext()) {
				((IBookProcesses) proc.next()).processBook(event);
			}
		}
	}

}
