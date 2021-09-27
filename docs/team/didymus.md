---
layout: page
title: Didymus' Project Portfolio Page
---

### Project: Doc'it

Doc’it provides a centralised platform for authorised staff from small family clinics to view, update, and onboard
patient records, solving the inefficient paper records and files used today. With Doc’it, small family clinics can
reduce man-hours in managing paper files, translating this ‘saved’ time into better frontline care services
Doc'IT is a desktop address book application. The user interacts with it using a CLI, and it has a GUI created 
with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project:

- **New Feature**: Added AppointmentBook class to store and manage appointments.
  * What it does: allows the user to store and manage multiple appointments across different Date and Time. 
  * Justification: This feature improves the product significantly because a user can avoid clashing appointments and track the upcoming appointments from patients.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

- **New Feature**: Added Appointment class to store details of an appointment
  * What it does: allows the user to store and manage details of an appointment.
  * Justification: This feature improves the product significantly because a user can find specifics about details of an appointment, catering to the patient's needs.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

- **Code contributed**: [RepoSense link]()

- **Project management**:
  * Managed releases `v1.1` - `v1.5` (3 releases) on Github

- **Enhancements to existing features**:
  * Updated the GUI color scheme
  * Wrote additional tests for existing features

- **Documentation**:
  * User Guide:
    * Added documentation for the features Patient Records
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

- **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

- **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo
