package pl.edu.agh.iisg.to.javafx.cw3.command;

import pl.edu.agh.iisg.to.javafx.cw3.model.Account;
import pl.edu.agh.iisg.to.javafx.cw3.model.Transaction;

import java.util.List;

public class RemoveTransactionCommand implements Command {
    private List<Transaction> transactionToRemove;
    private Account account;

    public RemoveTransactionCommand(List<Transaction> transactionToRemove, Account account) {
        this.transactionToRemove = transactionToRemove;
        this.account = account;
    }

    @Override
    public String getName() {
        return "Removed " + transactionToRemove.size() + " removed.";
    }

    @Override
    public void execute() {
        for (Transaction toRemove : transactionToRemove) {
            account.removeTransaction(toRemove);
        }
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
