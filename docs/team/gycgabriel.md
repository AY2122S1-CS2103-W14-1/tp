---
layout: page
title: Gabriel's Project Portfolio Page
---

### Project: Doc'IT

Doc’it provides a centralised platform for authorised staff from small family clinics to view, update, and onboard
patient records, solving the inefficient paper records and files used today. With Doc’it, small family clinics can
reduce man-hours in managing paper files, translating this ‘saved’ time into better frontline care services
Doc'IT is a desktop address book application. The user interacts with it using a CLI, and it has a GUI created
with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Create `Appointment` class to represent a patient's appointment with the clinic.
  * What it does: Allows clinic staff to record a patient's appointment after a patient is created.
  * Justification: This feature improves the product significantly because clinic staff can represent multiple appointments of patient with individual date and times.
  * Highlights: The implementation of `Appointment` class required changes in both commands, parsers and storage, to be listed in a separate panel from `Patient` class, yet can be linked to the patient that has the appointment. It required an in-depth analysis of design alternatives. The final design chosen was to build `Appointment` class to compose `Patient` and a `LocalDateTime` to represent the date and time of the appointment. This design required the use of OOP composition and polymorphism principles. The implementation required careful thought in how the `Appointment` class will be loaded and stored to memory as a list of appointments yet referencing a patient object. To achieve this result, the design choice was to use `Index` of a `Patient` at the point of save to store the appointment instead of a `Patient` object for easier loading from and storing to JSON by reference of the `Patient`'s position in the `AddressBook`.  Command types and `AppointmentBook` similar to AB3's `Person` class structure also needed to be created. New test cases also have to be created for coverage of appointment related classes.

* **New Feature**: Implement Add, Edit, Delete operations for `Appointment` class.
* What it does: Allows clinic staff to modify a patient's appointment after a patient is created.
* Justification: This feature improves the product significantly because clinic staff can modify appointments that they have created if they have entered in the wrong patient or date, or if the appointment timing was changed.
* Highlights: The implementation of `apmt add`, `apmt edit`, `apmt delete` required changes in `AddressBookParser` class and creation of respective `XYZCommandParser` classes, `XYZCommand` classes to conduct operations on `Appointment` objects. The `AppointmentBook` had to be created as well and have methods to conduct CRUD operations.

* **New Feature**: Implement LocalDateTime and date-time parsing for `Appointment` class.
* What it does: Allows clinic staff to enter a computer-comprehensible date and time, so that further functionality can be added that use computer-comprehensible date and time.
* Justification: This feature improves the product significantly because clinic staff can enter a computer-comprehensible date and time that can be formatted in the UI, and can be used to create sort appointment by date, and archive functionality.
* Highlights: The implementation of `LocalDateTime` required parsing to be implemented when receiving input, documentation changes and incorrect format handling.

* **New Feature**: Implement more restrictive parsers under `ParserUtil`.
* What it does: Change validation regexes to be more strict in Datetime, Name, Address, MedicalHistory
* Justification: This feature improves the product significantly because clinic staff cannot succeed in entering incorrectly formatted dates, name, address and medical history. Prevents fully numerical inputs for Name, Address and Medical History where it does not make sense.
* Highlights: The implementation of stricter validation regexes required an understanding of regex.

* **New Feature**: Change command result box to wrap text
* What it does: Wraps text overflow within the box so user does not have to scroll
* Highlights: The implementation in UI property removes the need to include a newline in command results in multiple places.

* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
