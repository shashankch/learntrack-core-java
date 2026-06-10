# JVM Basics (Simple)

## JDK vs JRE vs JVM
- **JVM (Java Virtual Machine)**: Runs Java **bytecode**. It is the engine that executes `.class` files.
- **JRE (Java Runtime Environment)**: JVM + libraries needed to **run** Java programs.
- **JDK (Java Development Kit)**: JRE + tools needed to **build** Java programs (like `javac`).

## What is Bytecode?
When you compile a `.java` file, Java generates a `.class` file. This `.class` file contains **bytecode**,
which is not tied to one specific operating system like Windows/macOS/Linux.

## “Write Once, Run Anywhere (WORA)”
Because Java compiles to bytecode, and the JVM exists for different platforms, the same `.class` file can run on any OS
(as long as a compatible JVM is available).

WORA means that a program can be written a single time and then run on different operating systems or hardware platforms without changing the source code. This idea is most commonly associated with Java, where code is compiled into bytecode that runs on the Java Virtual Machine (JVM) rather than directly on the operating system.

Because each platform (Windows, Linux, macOS, etc.) has its own JVM implementation, the same compiled program behaves consistently everywhere. In practice, this greatly reduces platform-specific bugs, lowers development effort, and makes software easier to distribute and maintain across diverse systems.