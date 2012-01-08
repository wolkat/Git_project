package com.kat.bookstore.events;

import java.util.*;

import com.kat.bookstore.domain.*;

public class EventMgr {

	private Book book;
	private List<IBookProcesses> processes = new ArrayList<IBookProcesses>();

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public synchronized void addProcess(IBookProcesses process) {
		processes.add(process);
	}

	public synchronized void removeProcess(IBookProcesses process) {
		processes.remove(process);
	}

	public synchronized void executeProcesses(List<Book> list) {
		for (Book book : list) {
			setBook(book);
			BookEvent event = new BookEvent(this, book);
			Iterator<IBookProcesses> proc = processes.iterator();
			while (proc.hasNext()) {
				((IBookProcesses) proc.next()).processBook(event);
			}
		}
	}
}
