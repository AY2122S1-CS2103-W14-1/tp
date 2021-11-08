---
layout: page
title: Didymus' Project Portfolio Page
---

### Project: Doc'it

#### Overview
Doc’it provides a centralised platform for authorised staff from small family clinics to view, update, and onboard
patient records and schedule appointments, solving the inefficient paper records and files used today. With Doc’it, small family clinics can
reduce man-hours in managing paper files, translating this ‘saved’ time into better frontline care services.

#### Summary of Contributions
Given below are my contributions to the project:
- **New Feature**: Create `MedicalHistory` class to store the medical history of Patient Records.
  * What it does: allows clinic staff to record medical histories when a patient is created.
  * Justification: This feature improves the product significantly because clinic staff can have more medical information about a Patient, which allows for better diagnosis.
  * Highlights: This feature was challenging and required an in-depth analysis of design alternatives. The final design chosen was to build `MedicalHistory` class to compose multiple `MedicalEntry` subclasses, which required the use OOP composition and polymorphism principles. The implementation also introduced wrapper classes, `Entry` and `EntryList` for the purpose of defensive programming. It also required changes to Json serialisation for storage of the new `MedicalHistory` information. Storage was particularly challenging given the need to record the `date` of recording, and reboot the `date` according to what was saved. CRUD operations  inherited from Ab3's `Person` class, now `Patient`, also needed to be modified to accommodate the new `MedicalHistory` attribute. Existing test cases for `parser` and CRUD operations inherited from AB3 needed modification.
- **New Feature**: Add and Delete `MedicalEntry` to the `MedicalHistory` of a Patient Record.
  * What it does: allows clinic staff to add and delete specified medical histories for an existing patient, with an automatic record of the date the description of the medical history was entered (for adding).
  * Justification: This feature improves the product significantly because clinic staff can record important medical details about the patient to have a stronger medical understanding. The automatic date of entry also ensures the medical history can be checked and updated. They can also delete wrong or irrelevant entries.
  * Highlights: This features requires a new `Command` to be created. It required some analysis of design alternatives. The implementation required modifications to existing commands, test cases and the `Parser` set of class.
  * Credits: *The design of the code was modelled after existing AB3 command codes, due to the existing `Parser` and `Command` associations.*
- **New Feature**: Modified GUI for `Prescription`.
  * What it does: allows clinic staff see `Prescription` added after executing command to add prescription to an appointment.
  * Justification: This feature improves the product significantly because clinic staff can visually view and record the prescriptions prescribed after an appointment.
  * Highlights: This features was challenging. It required a strong understanding of `ObservableList` and how `ObservableList` worked with the GUI. As `Prescription` was a class encapsulated by `Appointment`, merely changing the `Prescription` would not trigger an event for GUI to change as the `ObservableList` only observes `Appointment` objects. Thus, for every modification to `Prescription`, we needed to update the `Appointment` object by creating a copy of the `Appointment` object.
- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=didymental&tabRepo=AY2122S1-CS2103-W14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
- **Enhancements to existing features**:
  * **`FIND PATIENT` feature:**
    * Enhanced `pt find` to be able to find a list of patients based on their medical history description. This enhancement allows clinic staff to find patients by their `name` and any `description` of their medical history.
  * Justification: clinic staff can find a list of patients not only by their name but by their medical history, making it easier to track patients with similar past conditions.
  * Wrote additional tests for existing features and new features, specifically add medical entry, delete medical entry
- **Project management**:
  * Managed releases `v1.1` - `v1.4` (4 releases) on Github
- **Coordinating and Maintaining Issue Tracker**:
  * Coordinated sprint issues by dividing tasks and assigning tasks to group members (examples: [\#54](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/54), [\#55](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/55), [\#56](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/56), [\#57](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/57), [\#67](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/67), [\#75](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/75), [\#76](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/76), [\#77](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/77), [\#78](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/78), [\#79](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/79), [\#80](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/80), [\#81](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/81), [\#102](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/102), [\#108](https://github.com/AY2122S1-CS2103-W14-1/tp/issues/108), and [more](https://github.com/AY2122S1-CS2103-W14-1/tp/issues?q=is%3Aissue+author%3Adidymental+is%3Aclosed))
- **Documentation**:
  * User Guide:
    * Added documentation for `MedicalHistory` and all `MedicalHistory` related features expected outcomes
  * Developer Guide:
    * Added implementation details of the `add medical history`, `delete medical history` feature.
    * Drew the UML class diagram which captures the associations between `MedicalHistory`, `MedicalEntry` and `Patient` classes.
    * Drew the sequence and activity diagram which captures the associations between `Logic` and `Model` components for `AddMedicalEntry`, `DeleteMedicalEntry` features
- **Community**:
  * PRs reviewed (with non-trivial review comments): [\#71](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/71), [\#29](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/29), [\#18](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/18), [\#61](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/61)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/237), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/306))
  * Reported bugs and suggestions for other teams in the class (example: [1](https://github.com/didymental/ped))
