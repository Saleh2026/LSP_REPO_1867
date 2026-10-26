# Part 1 – Object-Oriented Design Evaluation of `CourseRegistration`


The provided `CourseRegistration` class exhibits multiple foundational object-oriented design flaws that violate encapsulation, the Single Responsibility Principle (SRP), and **Arthur Riel's Object-Oriented Design Heuristics**. Below is a detailed evaluation of the specific design issues and how they degrade structure, maintainability, extensibility, and correctness.


---


## 1. Violation of Information Hiding and Public Data Members
- **Observed Issue**: All five attributes (`studentId`, `studentName`, `email`, `courseCode`, and `creditHours`) are declared with `public` visibility, and the class lacks a constructor or validation methods.
- **Arthur Riel Heuristic Violated**: **Heuristic 2.1 — "All data should be hidden within its class."**
- **Impact on Correctness and Maintainability**:
  - Because fields are `public`, any external class can directly read or mutate them at any time without validation (for example, setting `creditHours = -10`, leaving `studentId` as `null`, or changing `courseCode` mid-registration).
  - There is no invariant enforcement. If the internal representation of student data or credit hours changes in the future, every external class that accesses these public fields directly will break, creating tight coupling across the system.


---


## 2. "God Class" / Monolithic Responsibility Allocation & Low Cohesion
- **Observed Issue**: The single `CourseRegistration` class and its single `registerStudent()` method perform six unrelated responsibilities:
  1. Storing student identity and contact attributes (`studentId`, `studentName`, `email`).
  2. Storing course metadata (`courseCode`, `creditHours`).
  3. Calculating tuition and applying discount business rules (`creditHours * 750.0` and the 10% discount for `creditHours >= 12`).
  4. Formatting and printing console output (`System.out.println(...)`).
  5. Performing low-level file I/O persistence (`FileWriter("registrations.txt", true)`).
  6. Sending confirmation emails and logging system timestamps (`new Date()`).
- **Arthur Riel Heuristics Violated**:
  - **Heuristic 3.1 — "Distribute system intelligence horizontally as uniformly as possible, that is, the top-level classes in a design should share the work uniformly."**
  - **Heuristic 3.2 — "Do not create god classes/objects in your system. Be very suspicious of a class whose name contains Driver, Manager, System, or Subsystem."**
  - **Heuristic 2.8 — "A class should capture one and only one key abstraction."**
- **Impact on Structure and Maintainability**:
  - `CourseRegistration` acts as a procedural controller disguised as a class. A change to the tuition rate, discount threshold, persistence medium (file vs. database), notification format (email vs. SMS), or logging mechanism requires modifying the exact same class and method. This violates the Single Responsibility Principle and increases the risk of introducing regression bugs whenever any single rule changes.


---


## 3. Keep Related Data and Behavior in One Place (Missing Domain Abstractions)
- **Observed Issue**: Student attributes (`studentId`, `studentName`, `email`) and course attributes (`courseCode`, `creditHours`) are flattened into `CourseRegistration` rather than encapsulated within dedicated `Student` and `Course` domain classes.
- **Arthur Riel Heuristics Violated**:
  - **Heuristic 2.9 — "Keep related data and behavior in one place."**
  - **Heuristic 3.3 — "Beware of classes that have many accessor methods defined in their public interface."** (or exposed public fields representing distinct entities).
- **Impact on Reusability and Correctness**:
  - Because `Student` and `Course` do not exist as independent abstractions, a student registering for multiple courses requires duplicating `studentId`, `studentName`, and `email` across multiple `CourseRegistration` instances, leading to data redundancy and update anomalies.


---


## 4. Hardcoded Business Constants and Tight Coupling to Infrastructure
- **Observed Issue**:
  - Business constants (`750.0` per credit hour, `12` credit hours threshold, `0.90` discount multiplier) are hardcoded directly inside `registerStudent()`.
  - Persistence is tightly coupled to `java.io.FileWriter` and the hardcoded filename `"registrations.txt"`, without `try-with-resources` (risking file handle leaks if an exception occurs before `writer.close()`).
  - Presentation (`System.out.println`), notification, and logging are hardcoded to standard output.
- **Arthur Riel Heuristic Violated**:
  - **Heuristic 2.2 — "Users of a class must be dependent on its public interface, but a class should not be dependent on its users."** (and decoupling domain classes from specific I/O and UI mechanisms).
- **Impact on Extensibility and Testability**:
  - It is impossible to unit-test tuition calculation without simultaneously writing to `"registrations.txt"` and printing to the console.
  - Supporting new storage backends (such as a relational database) or new notification channels (such as SMS text messaging) is impossible without rewriting `registerStudent()`, violating the Open/Closed Principle (OCP).
