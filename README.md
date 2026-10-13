# Object-Oriented Programming in Java: Lab Solutions

Solutions to the OOP in Java lab manual (Labs 1 to 11), written and tested with OpenJDK 21.

## Contents

| Folder | Topic |
|--------|-------|
| `lab01` | Classes and objects |
| `lab02` | Constructors, `this` and `static` members |
| `lab03` | Encapsulation and access modifiers |
| `lab04` | Inheritance |
| `lab05` | Polymorphism |
| `lab06` | Abstraction, abstract classes and interfaces |
| `lab07` | Exception handling |
| `lab08` | Generics and the Collections Framework |
| `lab09` | Nested types, enums, records, `equals`/`hashCode` |
| `lab10` | File I/O, lambdas and streams |
| `lab11` | Mini-project: Library Management System |

Each file in `lab01` to `lab10` is a stand-alone program named `LabNNExK.java` (lab NN, exercise K).

## Requirements

- JDK 17 or newer (check with `javac -version`)

## Running an exercise

```bash
cd lab01
javac Lab01Ex1.java
java Lab01Ex1
```

Notes:
- `Lab04Ex4_Broken.java` and `Lab08Ex5_Broken.java` are **deliberately wrong**. They demonstrate compiler errors, so skip them when compiling a whole folder.
- `Lab07Ex1` and `Lab10Ex1` read input from the keyboard.

## Lab 11: Library Management System

A console application that combines encapsulation, inheritance, polymorphism, interfaces, custom exceptions, collections, streams and CSV file persistence.

**Features**
- Add books and DVDs (books are loaned for 14 days, DVDs for 7)
- Register members (maximum 3 loans each)
- Borrow and return items, with custom exceptions for unavailable items and loan limits
- List overdue items and search by title
- Data is saved to CSV on exit and reloaded on start

**Run it**

```bash
cd lab11
javac *.java
java LibraryApp        # the menu application
java LibraryTests      # console tests (16 checks)
```

To test overdue behaviour without waiting, simulate a date:

```bash
java -Dlibrary.today=2026-10-20 LibraryApp
```

A class diagram is in `lab11/class_diagram.png`.

## Author

Chowa Munyaradzi R, Harare Institute of Technology
