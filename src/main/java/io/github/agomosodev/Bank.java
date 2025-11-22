package io.github.agomosodev;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Bank {
    private final List<Client> clients = new ArrayList<>();

    public void addClient(Client client) {
        if (client == null) throw new IllegalArgumentException("client cannot be null");
        clients.add(client);
    }

    public List<Client> getClients() {
        return Collections.unmodifiableList(clients);
    }

    public List<Account> getAllAccounts() {
        List<Account> out = new ArrayList<>();
        for (Client c : clients) {
            out.addAll(c.getAccounts());
        }
        return Collections.unmodifiableList(out);
    }

    public void clear() {
        clients.clear();
    }
}
