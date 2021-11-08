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
  * What it does: Allows users to record a patient's appointment after a patient is created.
  * Justification: This feature allows users to represent multiple appointments of patients with each appointment having their own date and time.
  * Highlights:
    * The implementation of `Appointment` class required creation of `AppointmentBook`, `ReadOnlyAppointmentBook` and `UniqueAppointmentList` in `Storage` to support, Parsing of `Appointment`-related Commands, and Storing and Loading of `Appointment`. 
    * Appointments had to be listed in a separate list from `Patient` class, yet have a link to the patient that has the appointment. This feature required an in-depth analysis of design alternatives.
    * The final design chosen was to build `Appointment` class to compose `Patient` and a `LocalDateTime` to represent the date and time of the appointment. This design required the use of OOP composition and polymorphism principles. The implementation required careful thought in how the `Appointment` class will be loaded and stored to memory as a list of appointments, while at the same time referencing a patient object.
    * To achieve this result, the design choice was to use `Index` of a `Patient` at the point of save to store the appointment instead of a `Patient` object for easier loading from and storing to JSON by reference of the `Patient`'s position in the `AddressBook`. `AppointmentBook` similar to AB3's `Person` class structure also needed to be created. 
    * Changes required in `ModelManager` to instantiate `AppointmentBook` with `AddressBook`, i.e. to instantiate list of appointments with list of patient.
  * Relevant PRs: [\#58](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/58), [\#85](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/85), [\#87](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/87)

* **New Feature**: Implement Add, Edit, Delete operations for `Appointment` class.
  * What it does: Allows users to add, edit and delete a patient's appointment after the appointment is created.
  * Justification: This feature allows users to create, modify and delete appointments. They can modify wrongly-entered appointments or appointments to match changes in appointment date and time.
  * Highlights: 
    * This implementation required the creation of `Parser` and `Command` classes to support `Appointment`-related commands and addition of methods to do modifications on `AppointmentBook` class.
    * Parser:
      * Modified `AddressBookParser` class to accept `Appointment` commands
      * Created `XYZCommandParser` classes (e.g. `AddAppointmentCommandParser`)
    * Command:
      * Created `XYZCommand` classes to create, modify or delete the `Appointment` as commanded (e.g. `AddAppointmentCommand`)
    * Storage:
      * Added methods in `AppointmentBook` to update the list of appointments. 
  * Relevant PRs: [\#135](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/135), [\#85](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/85)

* **New Feature**: Implement date-time parsing for `Appointment` class.
  * What it does: Allows users to enter a computer-comprehensible date and time
  * Justification: This feature improves the product significantly because users can enter a computer-comprehensible date and time that can be reformatted in the UI, and can be used to implement sort and archive features.
  * Highlights: 
    * This implementation required implementing `ParserUtil#parseDateTime` for adding, editing appointments, and loading appointments from JSON. 
    * Implemented related documentation changes and incorrect format handling.
  * Relevant PR: [\#132](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/132)

* **New Feature**: Implement more restrictive parsers under `ParserUtil`.
  * What it does: Change validation regexes to be more strict in `Datetime`, `Name`, `Address`, `MedicalHistory`
  * Justification: This feature improves the product significantly because users cannot enter incorrectly formatted information.
  * Highlights: 
    * The implementation of stricter validation regexes required a strong understanding of regex.
    * Prevented fully numerical inputs for `Name`, `Address` and `Medical History` where it does not make sense.
    * Implemented parsing to restrict: 
      * Datetime to years 2000-2999, disallow 2400 hour input. Datetime to use Strict parsing to disallow incorrect date and times.
      * Name to alphabetical characters.
      * Address to alphanumerical and hash, dash, comma characters, cannot be numerical only.
      * Medical History to alphanumerical, dash, comma characters, cannot be numerical only.
  * Relevant PRs: [\#236](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/236), [\#261](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/261)

* **New Feature**: Add Logging for `parseDateTime` in `ParserUtil`
  * What it does: Prints log warning message in console on exception when parsing date and time
  * Highlights: Allows easier debugging of test cases and inputs, to know what caused the parsing date and time exception.
  * Relevant PR: [\#261](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/261)
  
* **New Feature**: Change command result box to wrap text
  * What it does: Wraps text overflow within the box so user does not have to scroll
  * Highlights: The implementation in UI property removes the need to include a newline in command results in multiple places.
  * Relevant PR: [\@236](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/236)

* **New Feature**: Wrote tests for `Appointment`-related classes to increase coverage by 4% in total
* Justification: New test cases also had to be created for coverage of appointment-related classes.
* Relevant PRs: [\#265](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/265), [\#286](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/286)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=gycgabriel)

* **Project management**:
  * Ensured scheduling and tracking, including setting up labels for issues for the team's use, and beginning milestones
  * Managed `Appointment` component and gave technical advice regarding `Appointment`-related classes and tests
    * Gave advice for tests for EditAppointmentCommand [\#287](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/287)
    * Fixed tests for DeleteAppointmentCommand [\#286](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/286)
    * Made suggestions regarding renaming methods to better suit its purpose in `Appointments` [\#237](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/237), [\#98](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/98)
    * Suggested enhancements [\#92](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/92)
  * Kept track of deadlines, updating team member's of current progress and reminding the team of upcoming deadlines

* **Documentation**:
  * User Guide:
    * Added documentation for the commands `apmt add`, `apmt list`, `apmt edit`, `apmt delete` [\#36](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/36/)
    * Did cosmetic tweaks to `Appointment` documentation of features to include 💡 and ℹ icons to improve readability: [\#162](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/162)
    * Added Table Of Contents links: [\#162](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/162)
  * Developer Guide:
    * Added implementation details of the `Appointments with valid Patient` feature.
      - [Appointment composed of a Valid Patient when added, loaded and stored](https://ay2122s1-cs2103-w14-1.github.io/tp/DeveloperGuide.html#appointment-composed-of-a-valid-patient-when-added-loaded-and-stored)
        - [Appointment composed of a Valid Patient when added, loaded and stored](https://ay2122s1-cs2103-w14-1.github.io/tp/DeveloperGuide.html#appointment-composed-of-a-valid-patient-when-added-loaded-and-stored)
        - [How Appointment is implemented](https://ay2122s1-cs2103-w14-1.github.io/tp/DeveloperGuide.html#how-appointment-is-implemented)
        - [Add a new Appointment](https://ay2122s1-cs2103-w14-1.github.io/tp/DeveloperGuide.html#add-a-new-appointment)
        - [Load Appointments on App Launch](https://ay2122s1-cs2103-w14-1.github.io/tp/DeveloperGuide.html#load-appointments-on-app-launch)
        - [Save Appointments after every command](https://ay2122s1-cs2103-w14-1.github.io/tp/DeveloperGuide.html#save-appointments-after-every-command)
        - [Delete Patient that has made an Appointment](https://ay2122s1-cs2103-w14-1.github.io/tp/DeveloperGuide.html#delete-patient-that-has-made-an-appointment)
      * Relevant PRs: [\#93](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/93), [\#317](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/317/files)
    * Added [Adding an Appointment](https://ay2122s1-cs2103-w14-1.github.io/tp/DeveloperGuide.html#adding-an-appointment) and 'Deleting a patient that has an appointment' test cases under Instructions for Manual Testing and made note of the similar test case for Edit Appointments. 
    * Added 'Deleting an Appointment' test cases
    * Updated Appointment Use Cases to v1.4
      * Remove `User lists commands` because Doc'it displays full list by default
      * Added `Use case: UC06 - Edit an appointment`
    * Added [Glossary](https://ay2122s1-cs2103-w14-1.github.io/tp/DeveloperGuide.html#glossary), Glossary credits to User Guide.
    * Relevant PRs: [\#322](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/322), [\#319](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/319)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#165](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/165), [\#262](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/262), [\#91](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/91), [\#97](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/97))
  * Contributed to forum discussions by raising good questions (examples: [\#58](https://github.com/nus-cs2103-AY2122S1/forum/issues/58))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/gycgabriel/ped/issues/8), [2](https://github.com/gycgabriel/ped/issues/1), [3](https://github.com/gycgabriel/ped/issues/6))
