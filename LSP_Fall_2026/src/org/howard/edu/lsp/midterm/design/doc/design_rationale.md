# Part 3 – Evolving Requirements and Design Rationale


## 1. How the Proposed CRC-Based Design Accommodates Evolving Requirements


Our Part 2 CRC design already isolates persistence into `RegistrationRepository` and confirmation messaging into `NotificationService`. To support **two storage options (local file or database)** selected by deployment environment and **two confirmation channels (email or text message)**, the design accommodates these changes cleanly through polymorphism and dependency injection without modifying domain logic (`Student`, `Course`, `TuitionCalculator`, or `Registration`):


1. **Polymorphic Storage Options (`RegistrationRepository`)**:
   - `RegistrationRepository` serves as an interface declaring `save(Registration registration)`.
   - Two concrete collaborator classes implement this interface: `FileRegistrationRepository` (which writes records to `registrations.txt`) and `DatabaseRegistrationRepository` (which persists records to a relational database).
   - At application startup, the deployment configuration injects the appropriate `RegistrationRepository` implementation into `Registration` (or a registration service).
2. **Polymorphic Confirmation Channels (`NotificationService`)**:
   - `NotificationService` serves as an interface declaring `sendConfirmation(Student student, Registration registration)`.
   - Two concrete collaborator classes implement this interface: `EmailNotificationService` (which uses `student.getEmail()`) and `SmsNotificationService` (which uses `student.getPhoneNumber()`).
   - `Registration` invokes `notificationService.sendConfirmation(...)` polymorphically without knowing whether an email or text message is being sent.


---


## 2. Refinements to the Part 2 CRC Cards


To make the polymorphic specializations explicit, we refine the Part 2 CRC cards by adding concrete subtype cards for `RegistrationRepository` and `NotificationService`, and ensuring `Student` stores `phoneNumber` for text messaging:


```text
Class: FileRegistrationRepository (implements RegistrationRepository)
Responsibilities:
- Persist registration records to a local flat file (registrations.txt) using safe resource handling
Collaborators:
- Registration


Class: DatabaseRegistrationRepository (implements RegistrationRepository)
Responsibilities:
- Persist registration records to a database table according to the deployment environment
Collaborators:
- Registration


Class: EmailNotificationService (implements NotificationService)
Responsibilities:
- Format and send registration confirmation messages to the student's email address
Collaborators:
- Student
- Registration


Class: SmsNotificationService (implements NotificationService)
Responsibilities:
- Format and send registration confirmation text messages (SMS) to the student's phone number
Collaborators:
- Student
- Registration
