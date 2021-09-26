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

2. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

* **`list`** : Lists all contacts.

* **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

* **`delete`**`3` : Deletes the 3rd contact shown in the current list.

* **`clear`** : Deletes all contacts.

* **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

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

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

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