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

* **New Feature**: Create `Appointment` class to represent a patient's appointment.
  * What it does: Allows users to record a patient's appointment after a patient is created.
  * Justification: This feature allows users to represent multiple appointments of patients with each having their own date and time.
  * Highlights: The implementation of `Appointment` class required creation of `AppointmentBook`, `ReadOnlyAppointmentBook` and `UniqueAppointmentList` in `Storage` to support saving and loading of `Appointment`. Changes required in `ModelManager` to instantiate `AppointmentBook` with `AddressBook`, i.e. to instantiate list of appointments with list of patient. Appointments had to be listed in a separate list from `Patient` class, yet have a link to the patient that has the appointment. This feature required an in-depth analysis of design alternatives. The design choice was to build `Appointment` class to compose `Patient` and a `LocalDateTime` to represent the date and time of the appointment. This design required the use of OOP composition and polymorphism principles. The implementation required careful thought in how the `Appointment` class will be loaded and stored to memory as a list of appointments, while referencing a patient object. The design choice was to use `Index` of a `Patient` to store the appointment instead of a `Patient` object for easier loading from and storing to JSON. ([\#58](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/58), [\#85](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/85), [\#87](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/87))

* **New Feature**: Implement Add, Edit, Delete operations for `Appointment` class.
  * What it does: Allows users to add, edit and delete a patient's appointment after the appointment is created.
  * Justification: Allows users to create, modify and delete appointments. Users can modify appointments to reflect changes in appointment details.
  * Highlights: This implementation required the creation of `Parser` and `Command` classes to support `Appointment`-related commands and addition of methods in `AppointmentBook` class. Modified `AddressBookParser` class to accept `Appointment` commands. Created `XYZCommandParser` classes (e.g. `AddAppointmentCommandParser`). Created `XYZCommand` classes to create, modify or delete the `Appointment` as commanded (e.g. `AddAppointmentCommand`). Added methods in `AppointmentBook` to update the list of appointments. ([\#135](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/135), [\#85](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/85))

* **New Feature**: Implement date-time parsing for `Appointment` class.
  * What it does: Allows users to enter a computer-comprehensible date and time
  * Justification: Allows date and time that to be reformatted in the UI, and be used to implement sort and archive features.
  * Highlights: Required implementing `ParserUtil#parseDateTime` for adding, editing appointments, and loading appointments from JSON. Implemented related documentation changes and incorrect format handling. ([\#132](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/132))

* **New Feature**: Implement more restrictive parsers under `ParserUtil`.
  * What it does: Change regexes to be stricter in `Datetime`, `Name`, `Address`, `MedicalHistory`
  * Justification: Prevents users from entering incorrectly formatted information.
  * Highlights: Required a strong understanding of regex. Prevented fully numerical inputs for `Name`, `Address` and `Medical History` where it does not make sense. ([\#236](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/236), [\#261](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/261))

* **New Feature**: Add Logging for `parseDateTime` in `ParserUtil`
  * Highlights: Allows easier debugging of test cases and inputs, to know what caused the parsing date and time exception. [\#261](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/261)
  
* **New Feature**: Change command result box to wrap text
  * Highlights: The implementation in UI property removes the need to include a newline in command results in multiple places. [\@236](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/236)

* **New Feature**: Wrote tests for `Appointment`-related classes to increase coverage by 4% in total [\#265](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/265), [\#286](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/286)

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
