package com.kat.bookstore.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.kat.bookstore.domain.Client;
import com.kat.bookstore.services.ClientDB;

@SessionScoped
@Named("clientBean")
public class ClientFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Client c = new Client(null,null);

	private ListDataModel<Client> clientsList = new ListDataModel<Client>();

	@Inject
	private ClientDB cdb;

	public Client getClient() {
		return c;
	}

	public void setClient(Client c) {
		this.c = c;
	}

	public ListDataModel<Client> getAllClients() {
		clientsList.setWrappedData(cdb.getAllClients());
		return clientsList;
	}

	// Actions
	public String addClient() {
		cdb.addClient(c);
		return "showClients";
		//return null;
	}

	public String deleteClient() {
		Client deleteClient = clientsList.getRowData();
		cdb.deleteClient(cdb.findClientByName(deleteClient.getName()));
		return null;
	}
}
