# Mini-Bank-CLI

A simple console-based Mini Bank application built in Java. It allows users to create clients, manage bank accounts, and perform basic transactions like deposits, withdrawals, and transfers.

---

## Features

* Create clients and bank accounts
* Deposit and withdraw money
* Check account balances
* Transfer money between accounts
* Assign unique IDs to clients and accounts automatically
* Console-based user interface

---

## Requirements

* Java JDK 8 or higher
* A terminal or IDE (IntelliJ, Eclipse, VS Code, etc.)

---

## Installation and Setup

1. **Clone the repository**

```bash
git clone https://github.com/agomosodev/mini-bank-cli.git
cd mini-bank-cli
```

2. **Compile the project**

```bash
javac -d out src/io/github/agomosodev/*.java
```

* This compiles all `.java` files into the `out` directory.

3. **Run the application**

```bash
java -cp out io.github.agomosodev.Main
```

4. **Using the program**

* The menu will appear in the console:

```
======= Mini Bank CLI =======
---------------
1) List clients
2) List accounts
3) Deposit
4) Withdraw
5) Transfer
6) Create account for client
7) Create client
8) Exit
============================
Choose an option:
```

---

## Notes

* Ensure your Java version is at least 8. Check by running:

```bash
java -version
```

* Input validation should handle:

  * Negative balances
  * Invalid accounts
  * Invalid client IDs

* Future improvements:

  * Add multithreading to simulate concurrent transfers
  * Exception handling for race conditions and insufficient funds

---

## License

This project is open-source and free to use.

---

## Author

Antonio Jesús Gómez Osorio ([agomosodev](https://github.com/agomosodev))

---
