package io.github.agomosodev;

import java.util.ArrayList;
import java.util.List;

public class Client {
    // Attributes
    private int id;
    private String name;
    private String last_name;
    private String email;
    private String phone;
    private List<Account> accounts;

    // Constructor
    public Client(int id, String name, String last_name, String email, String phone, List<Account> accounts) {
        this.id = id;
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Client first name cannot be null or empty");
        }
        this.name = name.trim();

        if (last_name == null || last_name.trim().isEmpty()) {
            throw new IllegalArgumentException("Client last name cannot be null or empty");
        }
        this.last_name = last_name.trim();

        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;

        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        this.phone = phone;

        // copy provided accounts if any
        this.accounts = new ArrayList<>();
        if (accounts != null) {
            this.accounts.addAll(accounts);
        }
    }
    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Client first name cannot be null or empty");
        this.name = name.trim();
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        if (last_name == null || last_name.trim().isEmpty()) throw new IllegalArgumentException("Client last name cannot be null or empty");
        this.last_name = last_name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!isValidEmail(email)) throw new IllegalArgumentException("Invalid email format");
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!isValidPhone(phone)) throw new IllegalArgumentException("Invalid phone number format");
        this.phone = phone;
    }
    // Method to add account
    public void addCuenta(Account account) {
        accounts.add(account);
    }

    // Getter for accounts list
    public java.util.List<Account> getAccounts() {
        return java.util.Collections.unmodifiableList(accounts);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Client{");
        sb.append("id: ").append(id);
        sb.append(", name: ").append(name);
        sb.append(", last name: ").append(last_name);
        sb.append(", email: ").append(email);
        sb.append(", phone: ").append(phone);
        sb.append(", accounts: [");
        for (int i = 0; i < accounts.size(); i++) {
            Account a = accounts.get(i);
            sb.append(a == null ? "null" : a.getAccountNumber());
            if (i < accounts.size() - 1) sb.append(", ");
        }
        sb.append("]");
        sb.append('}');
        return sb.toString();
    }

    // Methods validate email and phone can be added here
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$");
    }

    private boolean isValidPhone(String telefono) {
        return telefono != null && telefono.matches("^\\+?\\d{7,15}$");
    }

}
