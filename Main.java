class Payment {
    final double amount;

    Payment(double amount) {
        this.amount = amount;
    }
}

class Coin extends Payment {
    Coin(double amount) { super(amount); }
}

class Card extends Payment {
    Card(double amount) { super(amount); }
}

// GENERIC CLASS with a CONSTRAINT.
// P is the kind of payment this machine accepts. "P extends Payment" means
// only Payment or its subclasses are allowed, so we can safely use payment.amount.
class Machine<P extends Payment> {
    private final String name;
    private double balance;

    Machine(String name) {
        this.name = name;
    }

    void insert(P payment) {
        balance += payment.amount;
    }

    double getBalance() { return balance; }
    String getName() { return name; }
}

public class Main {

    // LOWER-BOUNDED WILDCARD: "? super Coin" means a machine for Coin or any
    // SUPERCLASS of Coin (Payment). Inserting a Coin is safe in all of them.
    static void feedCoins(Machine<? super Coin> m) {
        m.insert(new Coin(0.5));
        m.insert(new Coin(1.0));
    }

    // UPPER-BOUNDED WILDCARD: any machine of Payment or a subclass, read-only use.
    static void showBalance(Machine<? extends Payment> m) {
        System.out.println(m.getName() + ": " + m.getBalance());
    }

    public static void main(String[] args) {
        Machine<Coin> snacks = new Machine<Coin>("Snacks");       // coins only
        Machine<Card> coffee = new Machine<Card>("Coffee");       // cards only
        Machine<Payment> mixed = new Machine<Payment>("Mixed");   // any payment

        feedCoins(snacks);    // Ok: Coin
        feedCoins(mixed);     // Ok: Payment is a superclass of Coin
        // feedCoins(coffee); // Error: Card is not a superclass of Coin

        coffee.insert(new Card(3.5));
        mixed.insert(new Card(5));
        // snacks.insert(new Card(2));   // Error: a Machine<Coin> only accepts Coins

        showBalance(snacks);  // Snacks: 1.5
        showBalance(coffee);  // Coffee: 3.5
        showBalance(mixed);   // Mixed: 6.5

        // Machine<String> bad = new Machine<String>("X");   // Error: String is not a Payment
    }
}
