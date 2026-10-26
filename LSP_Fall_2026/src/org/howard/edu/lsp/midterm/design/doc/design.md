# Part 2 – Proposed Improved Design Using CRC Cards


Below is the refactored object-oriented design using CRC (Class–Responsibility–Collaborator) cards. Each class captures a single cohesive abstraction and distributes system intelligence horizontally in accordance with Arthur Riel's heuristics.


---


Class: Student
Responsibilities:
- Store and protect student identity and contact details (studentId, studentName, email, phoneNumber)
- Validate student attributes upon construction
- Provide controlled read access to student information
Collaborators:
- None


---


Class: Course
Responsibilities:
- Store and protect course information (courseCode, courseTitle, creditHours)
- Validate that creditHours is positive and courseCode is well-formed
- Provide controlled read access to course metadata
Collaborators:
- None


---


Class: TuitionCalculator
Responsibilities:
- Calculate base tuition from a student's enrolled credit hours and the per-credit rate
- Apply eligibility rules for heavy course-load discounts (e.g., 10% discount when creditHours >= 12)
- Return the final computed tuition amount without performing I/O or persistence
Collaborators:
- Course


---


Class: Registration
Responsibilities:
- Represent the domain association between a Student, one or more Courses, the calculated tuition, and the registration timestamp
- Coordinate the registration workflow by delegating tuition calculation, persistence, notification, and logging to specialized collaborators
- Provide registration summary details
Collaborators:
- Student
- Course
- TuitionCalculator
- RegistrationRepository
- NotificationService
- RegistrationLogger


---


Class: RegistrationRepository
Responsibilities:
- Define the abstraction (interface) and implementation for persisting registration records to a storage medium (such as a local file or database)
- Format and safely store registration data using proper resource management
Collaborators:
- Registration


---


Class: NotificationService
Responsibilities:
- Define the abstraction (interface) and implementation for sending registration confirmations to a student (such as via email or text message)
- Format confirmation messages using student and course registration details
Collaborators:
- Student
- Registration


---


Class: RegistrationLogger
Responsibilities:
- Record timestamped audit logs of processed registration events and any system errors
Collaborators:
- Registration
