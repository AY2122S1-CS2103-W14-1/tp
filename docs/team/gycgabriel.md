---
layout: page
title: Gabriel's Project Portfolio Page
---

### Project: Doc'IT

Doc’it provides a centralised platform for authorised staff from small family clinics to view, update, and onboard
patient records, solving the inefficient paper records and files used today. With Doc’it, small family clinics can
reduce man-hours in managing paper files, translating this ‘saved’ time into better frontline care services
Doc'IT is a desktop address book application. Users key in CLI-themed commands in the command box, and the results of the commands are dislayed in the GUI created with JavaFX. It is written in Java, and has about 10 kLOC. 

Given below are my contributions to the project.

* **New Feature**: Create `Appointment` class to represent a patient's appointment with the clinic.
  * What it does: Allows clinic staff to record a patient's appointment after a patient is created.
  * Justification: This feature improves the product significantly because clinic staff can represent multiple appointments of patient with individual date and times.
  * Highlights: 
    * The implementation of `Appointment` class required changes and creation of new classes in Commands, Parsers and Storage related classes. (Pull requests [\#58](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/58), [\#85](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/85))
    * Appointments had to be listed in a separate list from `Patient` class, yet have a link to the patient that has the appointment. This feature required an in-depth analysis of design alternatives. 
    * The final design chosen was to build `Appointment` class to compose `Patient` and a `LocalDateTime` to represent the date and time of the appointment. This design required the use of OOP composition and polymorphism principles. The implementation required careful thought in how the `Appointment` class will be loaded and stored to memory as a list of appointments, while at the same time referencing a patient object. 
    * To achieve this result, the design choice was to use `Index` of a `Patient` at the point of save to store the appointment instead of a `Patient` object for easier loading from and storing to JSON by reference of the `Patient`'s position in the `AddressBook`. `AppointmentBook` similar to AB3's `Person` class structure also needed to be created. New test cases also had to be created for coverage of appointment-related classes. (Pull request [\#87](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/87))

* **New Feature**: Implement Add, Edit, Delete operations for `Appointment` class.
  * What it does: Allows clinic staff to modify a patient's appointment after a patient is created.
  * Justification: This feature improves the product significantly because clinic staff can enter commands to add, edit and delete appointments. They can also modify appointments that they have created if they have entered in the wrong patient or date, or if the appointment timing was changed.
  * Highlights: The implementation of `apmt add`, `apmt edit`, `apmt delete` required changes in `AddressBookParser` class and creation of respective `XYZCommandParser` classes, `XYZCommand` classes to conduct operations on `Appointment` objects. The `AppointmentBook` had to be created as well and have methods to conduct CRUD operations. (Pull requests [\#135](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/135), [\#85](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/85))

* **New Feature**: Implement LocalDateTime and date-time parsing for `Appointment` class.
  * What it does: Allows clinic staff to enter a computer-comprehensible date and time, so that further functionality can be added that use computer-comprehensible date and time.
  * Justification: This feature improves the product significantly because clinic staff can enter a computer-comprehensible date and time that can be formatted in the UI, and can be used to create sort appointment by date, and archive functionality.
  * Highlights: The implementation of `LocalDateTime` required parsing to be implemented when receiving input, documentation changes and incorrect format handling. (Pull request [\#132](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/132))

* **New Feature**: Implement more restrictive parsers under `ParserUtil`.
  * What it does: Change validation regexes to be more strict in Datetime, Name, Address, MedicalHistory
  * Justification: This feature improves the product significantly because clinic staff cannot succeed in entering incorrectly formatted dates, name, address and medical history. Prevents fully numerical inputs for Name, Address and Medical History where it does not make sense.
  * Highlights: The implementation of stricter validation regexes required an understanding of regex. (Pull requests [\#236](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/236), [\#261](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/261))

* **New Feature**: Change command result box to wrap text 
  * What it does: Wraps text overflow within the box so user does not have to scroll
  * Highlights: The implementation in UI property removes the need to include a newline in command results in multiple places.

* **New Feature**: Wrote tests for Appointment-related classes to increase coverage by 4% in total (Pull requests [\#265](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/265), [\#286](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/286))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=gycgabriel)

* **Project management**:
  * Ensured scheduling and tracking, including setting up labels for issues for the team's use, and beginning milestones 1.1 and 1.2
  * Managed `Appointment` component and gave technical advice regarding `Appointment`-related classes and tests
    * Gave advice for tests for EditAppointmentCommand [\#287](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/287)
    * Fixed tests for DeleteAppointmentCommand [\#286](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/286)
    * Made suggestions regarding renaming methods to better suit its purpose in `Appointments` [\#237](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/237), [\#98](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/98)
    * Suggested enhancements [\#92](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/92)
  * Kept track of deadlines, updating team member's of current progress and reminding the team of upcoming deadlines

* **Documentation**:
  * User Guide:
    * Added documentation for the features `apmt add`, `apmt list`, `apmt edit`, `apmt delete` [\#36](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/36/)
    * Did cosmetic tweaks to `Appointment` documentation of features to include 💡 and ℹ️  to improve readability: [\#162](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/162)
    * Added Table Of Contents links
  * Developer Guide:
    * Added implementation details of the `Appointments with valid Patient` feature. [\#93](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/93)
    * Included sequence diagrams for adding and saving appointments. 

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#165](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/165), [\#262](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/262), [\#91](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/91), [\#97](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/97))
  * Contributed to forum discussions by raising good questions (examples: [\#58](https://github.com/nus-cs2103-AY2122S1/forum/issues/58))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/gycgabriel/ped/issues/8), [2](https://github.com/gycgabriel/ped/issues/1), [3](https://github.com/gycgabriel/ped/issues/6))

