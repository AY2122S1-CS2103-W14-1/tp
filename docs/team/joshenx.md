---
layout: page
title: Joshen's Project Portfolio Page
---

### Project: Doc'it

Doc’it provides a centralised platform for authorised staff from small family clinics to view, update, and onboard
patient records, solving the inefficient paper records and files used today. With Doc’it, small family clinics can
reduce man-hours in managing paper files, translating this ‘saved’ time into better frontline care services
Doc'IT is a desktop address book application. The user interacts with it using a CLI, and it has a GUI created
with JavaFX. It is written in Java, and has about 10 kLoC.

**Summary of Contributions**

Given below are my contributions to the project:

- **New Feature**: Added `ArchivedAppointmentBook` class to store details of archived appointments
    * What it does: stores details of archived appointments, but prevents internal modification of data.
    * Justification: This feature allows the user to archive and view archived appointments, to look at previous patient
      prescriptions and past appointments.
    * Highlights: The implementation requires careful restriction of access for the new classes
      to prevent misuse and inappropriate modification of archived data.
    * Credits: Implementation details of `AppointmentBook` by teammate Gabriel Goh
    * Relevant PR: [\#69](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/69)


- **New Feature**: Integrate `Appointment` and `Prescriptions` into the GUI
    * What it does: display appointment information on the GUI.
    * Justification: This feature extends the functionality of the CLI application as a user can immediately view upcoming appointments at a glance.
    * Highlights:  A "TODAY" tag is displayed on appointment cards with appointments scheduled today. Integration of appointments on the GUI was
      challenging as many considerations had to be made with regards to how appointments should be handled when patients
      were removed from the system. 
    * Relevant PRs: [\#152](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/152), [\#91](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/91)


- **New Feature**: Auto-Archive feature
    * What it does: auto-archives expired (24-hour past) appointments.
    * Justification: This feature improves the archiving process significantly because 
      this saves the user trouble of archiving many (e.g. >20) past appointments when
      they are already over.
    * Highlights: This feature uses a thread pool to schedule the archive command at a specific timing each day. It was challenging to implement testcases due to the time-sensitive nature
      of the feature.
    * Credits: Implementation adapted from https://stackoverflow.com/a/20388073
    * Relevant PR: [\#155](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/155)


- **New Feature**: Sort Appointments feature
    * What it does: sorts appointments in order of whether its today, followed by dateTime, and patient name.
    * Justification: This feature allows the user to view the most urgent appointments first, and assign prescriptions to the most important
      appointments (those happening today).
    * Highlights: This feature required use of the Comparable interface for the `Patient` and `Appointment` classes, and
      checks with the current system time to verify if an appointment is deemed 'expired'.
    * Relevant PR: [\#155](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/155)


- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=totalCommits&sortWithin=title&timeframe=commit&mergegroup=AY2122S1-CS2103T-W10-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103-W14-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T12-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W15-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T11-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T10-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T17-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W17-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103-T16-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T13-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103-T16-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103-T14-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W10-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103-T16-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T12-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103-F10-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W13-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-F11-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-F11-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103-F10-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W12-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W10-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103-T14-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W10-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103-F09-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103-T14-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T10-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W17-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W12-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103-F09-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T09-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-F12-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T09-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-F12-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T13-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103-W14-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103-F10-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W16-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W17-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-F11-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W15-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W12-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-F12-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W08-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W17-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W11-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T13-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-F12-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T15-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103-F09-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W12-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-F11-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103-F10-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T11-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W16-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W08-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T17-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W08-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T15-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T15-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T10-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T12-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103-F09-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W15-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T09-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W11-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W08-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W16-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-F13-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T09-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T17-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-F13-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W13-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-F13-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W11-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W15-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T11-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T10-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T13-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W11-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W13-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T12-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103-W14-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W13-4%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-F13-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103-T16-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T11-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103-T14-3%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T17-1%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-W16-2%2Ftp%5Bmaster%5D~AY2122S1-CS2103T-T15-1%2Ftp%5Bmaster%5D&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=joshenx&tabRepo=AY2122S1-CS2103-W14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


- **Project management**:
    * Managed releases `v1.1` - `v1.5` (3 releases) on Github


- **Enhancements to existing features**:
    * Completely revamped the GUI with modern UI trends [\#121](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/121)
    * Wrote additional tests for existing features
    * Handled most of the UI changes, UI testing, and UI debugging


- **Documentation**:
    * User Guide:
        * Added documentation for the features Sort Command, Archive Command
    * Developer Guide:
        * Added documentation for the UI.
        * Updated PUML diagrams for UI and Storage classes. [\#96](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/96)
        * Added implementation details and PUML diagrams for the Auto-Archive feature and Archive Command. [\#114](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/114), [\#180](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/180), [\#296](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/296)


- **Community**:
    * Detailed PRs made: [\#88](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/88), [\#121](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/121), [\#152](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/152), [\#155](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/155), [\#175](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/175)
    * PRs reviewed (with non-trivial review comments): [\#120](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/120), [\#128](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/128), [\#136](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/136), [\#174](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/174)

- **Debugging**:
    * Bugs fixed: [\#97](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/97), [\#114](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/114), [\#130](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/130), [\#167](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/167), [\#173](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/173), [\#229](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/229), [\#230](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/230), [\#238](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/238), [\#262](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/262)

<div style="page-break-after: always;"></div>

### Contributions to the Developer Guide:

#### Archiving an Appointment

**Overview**

A user is able to archive an appointment when the appointment is _expired_, i.e. the patient has either missed his/her appointment
or already attended the scheduled appointment. In this case, the appointment should be archived, so that clinic staff
are able to view what medicine was prescribed to the patient during previous appointments.

**Implementation Details**

![Sequence diagram of Archive Command](../diagrams/ArchiveCommandSequenceDiagram.png)


Users may archive specific appointments manually to remove visual clutter. This is done through the `ArchiveAppointmentCommand`.

The above sequence diagram displays how the archive command works. The parsing mechanism has been abstracted out from the above diagram as it
has been covered in previous diagrams. An example input can be viewed in our [User guide](UserGuide.md). It first retrieves the Appointment to archive from the
appointment index parsed through the user input, removes the appointment from upcoming appointments, and adds it to archived appointments.

Archiving is facilitated by the `ArchivedAppointmentBook`. As opposed to the regular `AppointmentBook`, it does not allow
users to directly modify the data of appointments as archived data should not be edited. Hence, the following operations
have the `private` access modifier:

- `ArchivedAppointmentBook#setAppointment(Appointment target, Appointment editedAppointment)` - edits the `target` Appointment
  to be replaced with `editedAppointment`.
- `ArchivedAppointmentBook#removeAppointment(Appointment key)` - removes the target Appointment `key`.

The reason these methods exist in the class is so to support the methods `ArchivedAppointmentBook#updatePatient(Patient target, Patient editedPatient)`
and `ArchivedAppointmentBook#removePatient(Patient target)`, which are called to accurately reflect any updates/removals of patient
details.

![Class diagram of Archive Storage](../diagrams/ArchivedStorageClassDiagram.png)

In the `Storage` component, the addition of `ArchivedAppointmentBook` also necessitates the implementation of a separate storage system
for archived appointments. This forms under `ArchivedAppointmentBookStorage`, alongside `AddressBookStorage` and `AppointmentBookStorage`.
The .json file for archived appointments is named 'archivedappointmentbook.json'.

**Design Considerations**

| Alternative Considered | Current implementation | Rationale for current implementation |
| ---------- | ------------------------ | ------------------------ |
| Implementing archived appointments as a second `UniqueAppointmentList` attribute under the `AppointmentBook` class | Have a separate class `ArchivedAppointmentBook` | Having a separate class `ArchivedAppointmentBook` separates the two types of appointments better to facilitate data management. It ties in better with our Storage framework, and archivedappointmentbook.json files can be easily used by the user, instead of having to split one appointmentbook.json files into two segments. |


#### Auto-Archiving Feature

The archiving implementation involves scanning through all appointments in a day and comparing it to
the current date and time of the user system. If the current date and time is 24 hours ahead of the scheduled
appointment time (24-hour buffer), i.e. by our definition, _expired_, the appointment is automatically archived. This auto-archiving implementation is handled
by the `ModelManager` class in two ways.

![Sequence diagram of Auto-Archive Feature](../diagrams/AutoArchiveSequenceDiagram.png)

1. Upon initialisation of the application, the application automatically archives expired appointments (24-hours past their
   scheduled time). This is called through `ModelManager#archivePastAppointments()`.


2. A `ScheduledExecutorService` object schedules the task `AutoArchiveApmts` which implements the `Runnable` interface.
   Every day at the `ModelManager.UPDATE_HOUR`th hour, the `Runnable` object executes the `ModelManager#archivePastAppointments()`
   method.


In the case where there are many scheduled appointments, this saves the user trouble of archiving past appointments when
they are already over.
