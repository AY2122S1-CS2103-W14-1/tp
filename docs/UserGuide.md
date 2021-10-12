---
layout: page
title: User Guide
---

Doc’it provides a centralised platform for authorised staff from small family clinics to view, update, and onboard
patient records, solving the inefficient paper records and files used today. With Doc’it, small family clinics can
reduce man-hours in managing paper files, translating this ‘saved’ time into better front-line care services.

#### Table of Contents

1. Quick Start
2. Overview of Features
    1. Patient-related Features
        - Help
        - Add a patient
        - List all patients
        - Edit a patient
        - Delete a patient
        - Exit the program
    2. Appointment-related Features
        - List all appointments
        - Add an appointment
        - Delete an appointment
        - Archive an appointment
    3. Upcoming Commands
        - Clear all entries
        - Saving all data
        - Editing data files
        - Archiving data files
3. FAQ
4. Command Summary
5. Glossary

--------------------------------------------------------------------------------------------------------------------

## Quick start
1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `docit.jar` from [here](https://github.com/AY2122S1-CS2103-W14-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Doc'it.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

* **`list -p`** : Lists all patients.

* **`exit`** : Exits the app.

1. Refer to the **Features** section below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Overview of Features

<div markdown="block" class="alert alert-info">

This section provides a brief overview of Doc’It. The intention is for users to gain a better
understanding of basic functionalities of Doc’It, before diving into specific commands.
</div>

1. **Managing Patient Records**
   1. Create a new patient record
   2. View details of patient records
   3. Delete patient records
2. **Managing Patient Appointments**
   1. Create a new appointment
   2. View appointments
   3. Delete appointments
   4. Archive appointments

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

### Help : `help`

Shows the user manual for `Doc'it` explaining how to access the help page.

Format: `help`

---

### Add a patient: `pt add`

Creates a new patient record.

**Format:** `pt add n/FULL_NAME m/[MEDICAL_HISTORY]`

- `MEDICAL_HISTORY` is optional; if `MEDICAL_HISTORY` is not given, an empty text will be used

**Examples:**
- `pt add n/Joshen Lim`
- `pt add n/Joshen Lim m/lovesick`

**Expected Outcome:**
```
New patient created: Joshen Lim; Patient ID: 0001
```

---

### List all patients : `pt list`

Shows a list of all patients in the record system.

Format: `pt list`

---

### Clear all records : `clear`

Clears all patient records and information. This is an irreversible operation.

Format: `clear`

---

### Edit a patient : `pt edit`

Edits the details of a specified patient.

**Format:** `pt edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG] ...`
- All fields are optional but if stated, must not be null or empty
- `INDEX` is compulsory when making an edit to patient details

**Examples:**
- `pt edit 1 n/Joshen Tan m/Heartbreak`

**Expected outcome:** <br>
```
Successfully edited patient details:
Before:
Index: 1
Name: Joshen Lim
Medical History: Lovesick

After:
Index: 1
Name: Joshen Tan
Medical History: Heartbreak
```
---

### Delete a patient : `pt delete`

Deletes a patient record, including all information about the patient.

**Format:** `pt delete INDEX`

- Deletes the patient at the specified `INDEX` (one-indexed).

**Examples:**
- `pt delete 1`

**Expected Outcome:**
```
Deleted the following patient from records:
Patient Name: Joshen Lim
Patient ID: 1
```

---

### Exit the program : `exit`

Exits the program.

Format: `exit`

---

## Appointments
A patient in our patient record may have appointments to visit the family clinic. In the appointment view, each appointment on the appointment list indicates an upcoming visit to the clinic. To help small family clinics manage their upcoming appointments for its patients, Doc’It records the following attributes for appointment:

* Patient’s `INDEX`: The index of the patient in the list, starting from 1.
* Appointment Date: The date of the appointment.


## List all appointments: `appt list`
Shows a list of all appointments.

**Format:** `appt list`

**Examples:**
* `appt list`  Lists all appointments.

**Expected Outcome:**
```
1. Patient Name: Joshen Lim | Appointment Date: 2021-10-05
2. Patient Name: Ian Yong | Appointment Date: 2021-10-06
```

## Add an appointment: `appt add`
Adds an appointment for the patient of the specified patient id.

**Format:** `appt add INDEX d/DATE`

**Examples:**
* `list` List all patients.
* `appt add 1 d/2021-10-05`  Adds appointment to patient of ID 1.

**Expected Outcome:**
```
New appointment added:
Patient Name: Joshen Lim |  Appointment Date: 2021-10-05
```

## Delete an appointment: `appt delete`
Deletes the appointment at the specified index.

**Format:** `appt delete INDEX`
* Deletes the appointment at the specified INDEX.
* The index refers to the index number shown in the displayed appointment list.
* The index must be a positive integer 1, 2, 3, ...

**Examples:**
* `appt list`  Lists all appointments.
* `appt delete 1`  Deletes appointment at index 1.

**Expected Outcome:**
```
Appointment deleted:
Patient Name: Joshen Lim | Appointment Date: 2021-10-05
```

## Archive an appointment:  `appt archive`
Archives an old appointment that is already past its date.

**Format:** `appt archive INDEX`
* Archives the appointment at the specified INDEX.
* The index refers to the index number shown in the displayed appointment list.
* The index must be a positive integer 1, 2, 3, ...

**Examples:**
* `appt list`  Lists all appointments.
* `appt archive 1`  Archives appointment at index 1.

**Expected Outcome:**
```
Old appointment archived:
Patient Name: Joshen Lim | Appointment Date: 2021-10-05
```

---

## Upcoming commands
1. Saving the data `[coming in v1.3]`
2. Editing the data file `[coming in v1.3]`
3. Archiving data files `[coming in v1.3]`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Doc'it home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

### General Commands
| Command     | Format        |
|-------------|---------------|
| User Manual | `help`        |
| Clear       | `cleat`       |
| Exit        | `exit`        |

### Patient-related Commands
| Command | Format                                                                  | Sample                                                                                                |
|---------|-------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| Add     | `pt add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]`                | `pt add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| Delete  | `pt delete INDEX`                                                       | `pt delete 3`                                                                                         |
| Edit    | `pt edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]` | `pt edit 2 n/James Lee e/jameslee@example.com`                                                        |
| Find    | `pt find n/NAME`                                                        | `pt find /nJames Jake`                                                                                |
| List    | `pt list`                                                               | -                                                                                                     |

### Appointment-related Commands
| Command | Format                  | Sample                    |
|---------|-------------------------|---------------------------|
| Add     | `appt add INDEX d/DATE` | `appt add 1 d/2021-10-05` |
| Delete  | `appt delete INDEX`     | `appt delete 1`           |
| Archive | `appt archive INDEX`    | `appt archive 1`          |
| List    | `appt list`             | -                         |

--------------------------------------------------------------------------------------------------------------------

## Glossary
| Term           | Definition                                                                                |
|----------------|-------------------------------------------------------------------------------------------|
| Appointment    | A scheduled consult between a patient and the clinic's doctor.                            |
| Archive        | Storage for data that is non-urgent, e.g. appointment records that are past their date.   |
| Patient Record | A record of a patient's details, medical history, medication, appointment list, and name. |
| Prescription   | The issued medication/treatment for a patient.
