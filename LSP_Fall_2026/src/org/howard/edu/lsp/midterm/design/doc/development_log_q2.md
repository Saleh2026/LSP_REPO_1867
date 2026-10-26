## External Resources Used
1. **AI Assistance (Google Gemini)**:
   - **How it helped**: Assisted in structuring the evaluation of `CourseRegistration` against Arthur Riel's Object-Oriented Design Heuristics (specifically Heuristics 2.1, 2.8, 2.9, 3.1, and 3.2), formatting the refactored CRC cards in `design.md`, and articulating how polymorphic interfaces (`RegistrationRepository` and `NotificationService`) satisfy the evolving storage and notification requirements in `design_rationale.md`.
2. **Textbook / Reference Material**:
   - **Citation**: Arthur J. Riel, *Object-Oriented Design Heuristics*, Addison-Wesley, 1996.
   - **How it helped**: Referenced specific heuristics on information hiding, avoiding God classes, horizontal distribution of system intelligence, and keeping related data and behavior together.
3. **Course Lecture Notes (CSCI 363: Large-Scale Programming)**:
   - **How it helped**: Reviewed class lectures on CRC card modeling, cohesion vs. coupling, and refactoring monolithic classes.

   ## Complete AI Conversation Transcript
> **Note**: Paste the full prompt and response from your Gemini session below before committing and pushing to GitHub.

### User Prompt:
Good evening, Gemini. Explain Arthur Riel's Object-Oriented Design Heuristics 2.1, 2.8, 2.9, 3.1, and 3.2
   
### AI Response:
In his foundational 1996 book, Object-Oriented Design Heuristics, Arthur J. Riel cataloged more than 60 practical rules of thumb to guide developers toward maintainable, low-coupling, and high-cohesion architectures.

Heuristics 2.1, 2.8, and 2.9 focus on class cohesion, encapsulation, and interface cleanliness, while 3.1 and 3.2 govern the relationship between classes and the behavioral distribution across an object model.

Heuristic 2.1
"All data should be hidden within its class."

The Core Problem: Exposing public fields invites external classes to read and mutate state directly. This binds callers to internal representation details, breaking encapsulation and creating shotgun surgery whenever internal data structures change.

The Rule: Declare all instance variables private (or protected only when strictly necessary for inheritance). External access must go through explicit accessors or, better yet, behavioral methods.

Practical Impact: If a BankAccount stores balance as int cents instead of double dollars, private data ensures external clients never break when you change the underlying data representation. It also prevents invalid state transitions (such as setting an account balance to a negative number without validation).

Heuristic 2.8
"Do not put implementation details such as common-code private functions into the public interface of a class."

The Core Problem: When utility routines, helper functions, or intermediate parsing steps are declared public, the class’s published interface becomes cluttered. Callers can accidentally invoke functions meant only for internal orchestration, complicating versioning and backwards compatibility.

The Rule: The public interface represents an explicit, stable contract (the what). Helper methods, internal sanity checks, and algorithmic sub-steps (the how) must remain strictly non-public (private or file-scoped).

Practical Impact: Keeps client interactions intuitive by surfacing only domain-meaningful operations while preserving the developer's freedom to refactor internal subroutines without breaking downstream code.

Heuristic 2.9
"Do not spread related data across multiple classes. If data items always change together or belong to the same concept, group them into a single class."

The Core Problem: Known in modern refactoring terms as Data Clumps or Primitive Obsession, scattering interrelated attributes across separate classes (or passing them as loose primitive parameters) results in duplicated logic and validation leaks across the codebase.

The Rule: When two or more pieces of data share identical lifecycles, validations, or conceptual boundaries (e.g., latitude and longitude, street and zip_code, start_date and end_date), coalesce them into their own coherent Value Object or domain class.

Practical Impact: Simplifies parameter lists, reduces duplication, and localizes boundary validation inside a single constructor or class definition.

Heuristic 3.1
"Distribute system intelligence horizontally as uniformly as possible, that is, the top-level classes in a design should share the work uniformly."

The Core Problem: A common anti-pattern in object-oriented systems is the God Object (or Blob). One monolithic "controller" or "manager" class orchestrates all logic, while surrounding classes degrade into passive data containers.

The Rule: Responsibilities, decision-making, and state transitions should be distributed across domain entities rather than centralized inside a single procedural orchestrator.

Practical Impact: Prevents 5,000-line controller classes, promotes modular testing, and ensures classes act as autonomous actors communicating via high-level messages rather than passive data structures manipulated from the outside.

Heuristic 3.2
"Do not create god classes/god objects in your system. Be very suspicious of a class whose name contains words like 'Manager', 'Controller', or 'System'."

The Core Problem: A direct companion to Heuristic 3.1, this heuristic explicitly targets the naming conventions and architectural traps that lead to centralization. Classes named TransactionManager, OrderController, or AppSystem frequently become magnets for unrelated operations, violating the Single Responsibility Principle.

The Rule: If a class requires a vague suffix like Manager or Coordinator to describe what it does, its scope is likely too broad. Break it down into concrete entities that own their specific behaviors (e.g., split an AccountManager into AccountTransferService, StatementGenerator, and AccountValidator).

Practical Impact: Keeps class sizes manageable, limits cyclomatic complexity, and makes concurrency and dependency injection straightforward by eliminating shared global bottlenecks.
   