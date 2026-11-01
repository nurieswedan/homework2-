# Java Generics: Vending Machine Example

A small Java program that demonstrates **generic classes**, **constraining a type parameter**, and **wildcards** (upper and lower bounds).

## The idea

A vending machine accepts one kind of payment. The type of payment is a generic type parameter.

| Machine | Accepts |
|---|---|
| `Machine<Coin>` | coins only |
| `Machine<Card>` | cards only |
| `Machine<Payment>` | any payment |

## Concepts shown

| Concept | Where | What it does |
|---|---|---|
| Generic class | `class Machine<P ...>` | `P` is a placeholder type chosen when the object is created |
| Constraint | `P extends Payment` | Only `Payment` and its subclasses are allowed, so `Machine<String>` is a compile error |
| Lower-bounded wildcard | `Machine<? super Coin>` in `feedCoins` | Accepts a machine for `Coin` or any superclass, so inserting a `Coin` is always safe |
| Upper-bounded wildcard | `Machine<? extends Payment>` in `showBalance` | Accepts a machine of any payment type, for reading |

## How to run

Requires Java 11 or newer.

```
javac Main.java
java Main
```

Expected output:

```
Snacks: 1.5
Coffee: 3.5
Mixed: 6.5
```

## Compile-time errors (commented out in `Main.java`)

Uncomment any of these lines to see the compiler reject them:

- `feedCoins(coffee);` because `Card` is not a superclass of `Coin`
- `snacks.insert(new Card(2));` because a `Machine<Coin>` only accepts coins
- `Machine<String> bad = new Machine<String>("X");` because `String` is not a `Payment`

## Files

- `Main.java` contains all the classes and the demo
- `.github/workflows/java.yml` compiles and runs the program automatically on every push
