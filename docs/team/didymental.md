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
  * Highlights: This feature enhances the `Patient` class. It required an in-depth analysis of design alternatives. The final design chosen was to build `MedicalHistory` class to compose multiple `MedicalEntry` subclasses, which required the use OOP composition and polymorphism principles. The implementation also introduced wrapper classes, `Entry` and `EntryList` for the purpose of defensive programming, so that every Patient with an empty `MedicalHistory` will reference the same `EMPTY_MEDCIAL_HISTORY` object. It also required changes to the Json serialisation for storage of the new `MedicalHistory` information. Storage was particularly challenging given the need to record the `date` of recording, and reboot the `date` according to what was saved. CRUD operations that were inherited from Ab3's `Person` class, now `Patient`, also needed to be modified to accommodate the new `MedicalHistory` attribute. Existing test cases for `parser` and the CRUD operations inherited from AB3 also needed modification.


- **New Feature**: Add `MedicalEntry` to the `MedicalHistory` of a Patient Record.
  * What it does: allows clinic staff to add medical histories to an existing patient, with an automatic record of the date the description of the medical history was entered.
  * Justification: This feature improves the product significantly because clinic staff can record important medical details about the patient to have a stronger medical understanding. The automatic date of entry also ensures the medical history can be checked and updated.
  * Highlights: This features requires a new `Command` to be created. It required an in-depth analysis of design alternatives. The implementation was challenging as it required modifications to existing commands, test cases and the `Parser` set of class.
  * Credits: *The design of the code was modelled after existing AB3 command codes, due to the existing `Parser` and `Command` associations.*


- **New Feature**: Delete `MedicalEntry` from the `MedicalHistory` of a Patient Record.
  * What it does: allows clinic staff to delete a specified medical entry from the medical history of an existing patient.
  * Justification: This feature improves the product significantly because clinic staff can delete medical details about the patient that were wrongly entered or no longer relevant.
  * Highlights: This features requires a new `Command` to be created. It required an in-depth analysis of design alternatives. The implementation was challenging as it required modifications to existing commands, test cases and the `Parser` set of class.
  * Credits: *The design of the code was modelled after existing AB3 command codes, due to the existing `Parser` and `Command` associations.*


- **New Feature**: Modified GUI for `Prescription`.
  * What it does: allows clinic staff see `Prescription` added after executing command to add prescription to an appointment.
  * Justification: This feature improves the product significantly because clinic staff can visually view and record the prescriptions prescribed after an appointment.
  * Highlights: This features required a strong understanding of `ObservableList` and how `ObservableList` worked with the GUI. As `Prescription` was a class encapsulated by the `Appointment` class, merely changing the `Prescription` would not trigger an event for GUI to change as the `ObservableList` only observes `Appointment` objects. Thus, for every modification to `Prescription`, we needed to update the `Appointment` object by creating a copy of the `Appointment` object.


- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=didymental&tabRepo=AY2122S1-CS2103-W14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


- **Project management**:
  * Managed releases `v1.1` - `v1.4` (4 releases) on Github


- **Enhancements to existing features**:
  * **`FIND PATIENT` feature:**
    * Enhanced `pt find` to be able to find a list of patients based on their medical history description. This enhancement allows clinic staff to find patients by their `name` and any `description` of their medical history.
  * Justification: clinic staff can find a list of patients not only by their name but by their medical history, so it is easier to track patients with similar past conditions.   
  * Wrote additional tests for existing features


- **Documentation**:
  * User Guide:
    * Added documentation for `MedicalHistory` under Patient Records
  * Developer Guide:
    * Added implementation details of the `delete` feature.
    * Drew the UML diagram which captures the associations between `MedicalHistory`, `MedicalEntry` and `Patient` classes.
    * Drew the UML diagram which captures the associations between `AddMedicalEntry`, `DeleteMedicalEntry` and `PatientCommand` classes.
    * Updated UML diagram of `ParserUtil` to include related `MedicalHistory` and `MedicalEntry` classes.


- **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

- **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo
