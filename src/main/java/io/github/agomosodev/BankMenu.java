package io.github.agomosodev;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class BankMenu {

    public static void main(String[] args) {
        Bank bank = createSampleBank();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Mini Bank CLI - Menu demo");
        boolean running = true;
        while (running) {
            clearConsole();
            printMenu();
            System.out.print("Select option: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    listClients(bank);
                    break;
                case "2":
                    listAccounts(bank);
                    break;
                case "3":
                    doDeposit(bank, sc);
                    break;
                case "4":
                    doWithdraw(bank, sc);
                    break;
                case "5":
                    doTransfer(bank, sc);
                    break;
                case "6":
                    doCreateAccount(bank, sc);
                    break;
                case "7":
                    doCreateClient(bank, sc);
                    break;
                case "8":
                    running = false;
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Unknown option. Try again.");
            }
            System.out.println();
            pause(sc);
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Bank Menu ---");
        System.out.println("1) List clients");
        System.out.println("2) List accounts");
        System.out.println("3) Deposit");
        System.out.println("4) Withdraw");
        System.out.println("5) Transfer");
        System.out.println("6) Create account for client");
        System.out.println("7) Create client");
        System.out.println("8) Exit");
    }

    private static Bank createSampleBank() {
        Bank bank = new Bank();
        Client c1 = new Client(1, "Alice", "Smith", "alice@example.com", "+34123456789", null);
        Client c2 = new Client(2, "Bob", "Jones", "bob@example.com", "+34987654321", null);
        Account a1 = new Account("ES6621000418401234567891", 100.0, c1, true, LocalDate.now());
        Account a2 = new Account("ES6000491500051234567892", 50.0, c2, true, LocalDate.now());
        c1.addCuenta(a1);
        c2.addCuenta(a2);
        bank.addClient(c1);
        bank.addClient(c2);
        return bank;
    }

    private static void listClients(Bank bank) {
        System.out.println("--- Clients ---");
        for (Client c : bank.getClients()) {
            System.out.println(c);
        }
    }

    private static void listAccounts(Bank bank) {
        System.out.println("--- Accounts ---");
        for (Account a : bank.getAllAccounts()) {
            System.out.println(a);
        }
    }

    private static void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception ex) {
            // fallback: print several new lines
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    private static void pause(Scanner sc) {
        System.out.println("\nPress Enter to continue...");
        sc.nextLine();
        try {
            Thread.sleep(150);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    private static Optional<Account> findAccount(Bank bank, String inputIban) {
        if (inputIban == null) return Optional.empty();
        String normalized = inputIban.replaceAll("\\s+", "").toUpperCase();
        for (Account a : bank.getAllAccounts()) {
            if (normalized.equals(a.getAccountNumber())) return Optional.of(a);
        }
        return Optional.empty();
    }

    private static void doDeposit(Bank bank, Scanner sc) {
        try {
            System.out.print("Account IBAN: ");
            String iban = sc.nextLine().trim();
            Optional<Account> opt = findAccount(bank, iban);
            if (!opt.isPresent()) {
                System.out.println("Account not found: " + iban);
                return;
            }
            Account account = opt.get();
            System.out.print("Amount to deposit: ");
            String s = sc.nextLine().trim();
            double amount = Double.parseDouble(s);
            AccountService.deposit(account, amount);
            System.out.println("Deposit successful. New balance: " + account.getBalance());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number format: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid input: " + ex.getMessage());
        } catch (RuntimeException ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
        }
    }

    private static void doWithdraw(Bank bank, Scanner sc) {
        try {
            System.out.print("Account IBAN: ");
            String iban = sc.nextLine().trim();
            Optional<Account> opt = findAccount(bank, iban);
            if (!opt.isPresent()) {
                System.out.println("Account not found: " + iban);
                return;
            }
            Account account = opt.get();
            System.out.print("Amount to withdraw: ");
            String s = sc.nextLine().trim();
            double amount = Double.parseDouble(s);
            AccountService.withdraw(account, amount);
            System.out.println("Withdraw successful. New balance: " + account.getBalance());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number format: " + ex.getMessage());
        } catch (InsufficientFundsException ex) {
            System.out.println("Insufficient funds: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid input: " + ex.getMessage());
        } catch (RuntimeException ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
        }
    }

    private static void doTransfer(Bank bank, Scanner sc) {
        try {
            System.out.print("From account IBAN: ");
            String fromIban = sc.nextLine().trim();
            Optional<Account> ofrom = findAccount(bank, fromIban);
            if (!ofrom.isPresent()) {
                System.out.println("Source account not found: " + fromIban);
                return;
            }
            Account from = ofrom.get();

            System.out.print("To account IBAN: ");
            String toIban = sc.nextLine().trim();
            Optional<Account> oto = findAccount(bank, toIban);
            if (!oto.isPresent()) {
                System.out.println("Target account not found: " + toIban);
                return;
            }
            Account to = oto.get();

            System.out.print("Amount to transfer: ");
            String s = sc.nextLine().trim();
            double amount = Double.parseDouble(s);

            AccountService.transfer(from, to, amount);
            System.out.println("Transfer successful. New balances: from=" + from.getBalance() + ", to=" + to.getBalance());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number format: " + ex.getMessage());
        } catch (InsufficientFundsException ex) {
            System.out.println("Insufficient funds: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid input: " + ex.getMessage());
        } catch (RuntimeException ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
        }
    }

    private static void doCreateClient(Bank bank, Scanner sc) {
        try {
            int nextId = bank.getClients().stream().mapToInt(Client::getId).max().orElse(0) + 1;
            System.out.println("Assigned client id: " + nextId);
            System.out.print("First name: ");
            String name = sc.nextLine().trim();
            System.out.print("Last name: ");
            String last = sc.nextLine().trim();
            System.out.print("Email: ");
            String email = sc.nextLine().trim();
            System.out.print("Phone: ");
            String phone = sc.nextLine().trim();

            Client client = new Client(nextId, name, last, email, phone, null);
            bank.addClient(client);
            System.out.println("Client created: " + client);
            // show updated list
            listClients(bank);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid id format: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid input: " + ex.getMessage());
        } catch (RuntimeException ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
        }
    }

    private static void doCreateAccount(Bank bank, Scanner sc) {
        try {
            System.out.print("Owner client id: ");
            String idStr = sc.nextLine().trim();
            int id = Integer.parseInt(idStr);
            Client owner = null;
            for (Client c : bank.getClients()) {
                if (c.getId() == id) {
                    owner = c;
                    break;
                }
            }
            if (owner == null) {
                System.out.println("Client with id " + id + " not found.");
                return;
            }
            System.out.print("Account IBAN: ");
            String iban = sc.nextLine().trim();
            System.out.print("Initial balance: ");
            String bal = sc.nextLine().trim();
            double balance = Double.parseDouble(bal);

            Account account = new Account(iban, balance, owner, true, LocalDate.now());
            owner.addCuenta(account);
            System.out.println("Account created: " + account);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number format: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid input: " + ex.getMessage());
        } catch (RuntimeException ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
        }
    }
}
