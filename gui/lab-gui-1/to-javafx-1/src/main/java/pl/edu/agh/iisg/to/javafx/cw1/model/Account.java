package pl.edu.agh.iisg.to.javafx.cw1.model;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private StringProperty name;

	private ObjectProperty<BigDecimal> balance;

	private ObservableList<Transaction> transactions;

	public Account(String name) {
		this.name = new SimpleStringProperty(name);
		this.balance = new SimpleObjectProperty<>(BigDecimal.ZERO);
		this.transactions = FXCollections.observableArrayList();
	}

	public final String getName() {
		return name.getValue();
	}

	public final StringProperty getNameProperty() {
		return name;
	}

	public final BigDecimal getBalance() {
		return balance.getValue();
	}

	public final ObjectProperty<BigDecimal> getBalanceProperty() {
		return balance;
	}

	public final ObservableList<Transaction> getTransactions() {
		return transactions;
	}

	public final void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
		
}
