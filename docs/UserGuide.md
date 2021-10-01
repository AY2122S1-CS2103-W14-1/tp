---
layout: page
title: User Guide
---

Doc’it provides a centralised platform for authorised staff from small family clinics to view, update, and onboard
patient records, solving the inefficient paper records and files used today. With Doc’it, small family clinics can
reduce man-hours in managing paper files, translating this ‘saved’ time into better frontline care services.
* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start
---to be completed---

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `docit.jar` from [here](https://github.com/AY2122S1-CS2103-W14-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Doc'it.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

* **`list -p`** : Lists all patients.

* **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Overview of Features

<div markdown="block" class="alert alert-info">

This section provides a brief overview of Doc’It. The intention is for users to gain a better
understanding of basic functionalities of Doc’It, before diving into specific commands.
</div>

1. Managing Patient Records

   a. Create a new patient record

   b. View details of patient records

   c. Delete patient records


2. Managing Patient Appointments

   a. Create a new appointment

   b. View appointments

   c. Delete appointments

   d. Archive appointments

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`  after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]… ` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a patient: `add -p`

Creates a new patient record.

**Format:** `add -p f/FAMILY_NAME n/GIVEN_NAME m/[MEDICAL_HISTORY]`

- `MEDICAL_HISTORY` is optional; if `MEDICAL_HISTORY` is not given, an empty text will be used

**Examples:**
- `add -p f/Lim n/Joshen`
- `add -p f/Lim n/Joshen m/lovesick`

**Expected Outcome:**
New patient created: Lim, Joshen; Patient ID: 0001

---

### Listing all patients : `list`

Shows a list of all patients in the record system.

Format: `list`

---

### Editing a patient : `[coming in v1.2]`

_Details coming soon ..._

---

### Locating patients by name: `view -p`

Views an existing patient record.

**Format:** `view -p id/PATIENT_ID` or `view -p name/FULL_NAME`

**Examples:**
- `view -p id/0001`
- `view -p name/Joshen`
- `view -p name/Lim Joshen`
- `view -p name/Joshen Lim`

**Expected Outcome:**
Patient Name: Lim, Joshen
Patient ID: 0001
Appointment List: 2021-10-05, 2021-09-04
Medical History: lovesick
Prescription: panadol

---

### Deleting a patient : `delete -p`

Deletes a patient record, including all information about the patient.

**Format:** `delete -p id/PATIENT_ID`

- Deletes the patient with the specified `PATIENT_ID`.

**Examples:**
- `delete -p id/0001`

**Expected Outcome:**
Deleted the following patient from records:
Patient Name: Lim, Joshen
Patient ID: 0001

---

### Exiting the program : `exit`

Exits the program.

Format: `exit`

---

### Clearing all entries : `[coming in v1.2]`

_Details coming soon ..._

---

### Saving the data `[coming in v1.2]`

_Details coming soon ..._

---

### Editing the data file `[coming in v1.2]`

_Details coming soon ..._

---

### Archiving data files `[coming in v1.2]`

_Details coming soon ..._


## Appointments
A patient in our patient record may have appointments to visit the family clinic. In the appointment view, each appointment on the appointment list indicates an upcoming visit to the clinic. To help small family clinics manage their upcoming appointments for its patients, Doc’It records the following attributes for appointment:

* Patient’s Name: The patient’s name matching in the patient record.
* Appointment Date: The date of the appointment


## Listing all appointments: `list`
Shows a list of all appointments.

Format: `list -a`

Example:
* `list -a`  Lists all appointments.

Expected Outcome:
1. Patient Name: Lim, Joshen  Appointment Date: 2021-10-05
2. Patient Name: Yong, Ian Appointment Date: 2021-10-06


## Adding an appointment: `add`
Adds an appointment for the patient of the specified patient id.

Format: `add -a n/PATIENT_ID d/DATE`

Examples:
* `list`  List all patients.
* `add -a n/1 d/2021-10-05`  Adds appointment to patient of ID 1.

Expected Outcome:  
New appointment added:  
Patient Name: Lim, Joshen  Appointment Date: 2021-10-05


## Deleting an appointment: `delete`
Deletes the appointment at the specified index.

Format: `delete -a INDEX`
* Deletes the appointment at the specified INDEX.
* The index refers to the index number shown in the displayed appointment list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `list -a`  Lists all appointments.
* `delete -a 1`  Deletes appointment at index 1.

Expected Outcome:  
Appointment deleted:  
Patient Name: Lim, Joshen  Appointment Date: 2021-10-05


## Archiving an appointment:  `archive`
Archives an old appointment that is already past its date.

Format: `archive -a INDEX`
* Archives the appointment at the specified INDEX.
* The index refers to the index number shown in the displayed appointment list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `list -a`  Lists all appointments.
* `archive -a 1`  Archives appointment at index 1.

Expected Outcome:  
Old appointment archived:  
Patient Name: Lim, Joshen  Appointment Date: 2021-10-05


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Doc'it home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]… ` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]… `<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**List Appointment** | `list -a`
**Add Appointment** | `add -a n/PATIENT_ID d/DATE` <br> e.g.,  `add -a n/1 d/2021-10-05`
**Delete Appointment** | `delete -a INDEX` <br> e.g., `delete -a 1`
**Archive Appointment** | `archive -a INDEX` <br> e.g., `archive -a 1`

--------------------------------------------------------------------------------------------------------------------

## Glossary

Term | Definition
----- | ----------
**Appointment** | A scheduled consult between a patient and the clinic's doctor.
**Archive** | Storage for data that is non-urgent, e.g. appointment records that are past their date.
**Patient Record** | A record of a patient's details, medical history, medication, appointment list, and name.
**Prescription** | The issued medication/treatment for a patient.
