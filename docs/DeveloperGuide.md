# conTActs Developer Guide

## Target User Profile
**Primary Users:**
- Teaching Assistants (TAs) managing multiple students.

**Secondary Users:**
- Students accessing their records (future feature consideration).

## Value Proposition
conTActs helps TAs efficiently organize and manage student information, reducing administrative workload and improving accessibility. The app provides a centralized platform for tracking student details, communication, and academic progress.

## User Stories
### Must have
1. **As a TA, I can delete the contact details of a student, so that I can update my list of students.**
2. **As a new TA, I can import a list of students and professors, so that I don’t have to manually enter all the contacts.**
3. **As a TA, I can add the contact details of a student, so that I can keep track of my students.**
4. **As a TA, I can list the contact details of all my students, so that I can display and view my list of students.**
5. **As a TA, I can find a student’s contact information quickly, so that I can contact them conveniently.**

### Nice-to-Must-Have
6. **As a TA, I can filter contacts by role (e.g., student, professor, admin), so that I can find relevant contacts easily.**
7. **As a TA, I want an easily accessible contact list that I can divide into different folders based on different classes that I take over**
8. **As a new TA, I want to send a message to multiple students at once, so that I can make announcements efficiently.**

### Nice-To-Have
9. **As a veteran TA, I want to receive auto-suggestions for contacts based on past searches, so that I don’t have to type the full name.**
10. **As a veteran TA, I want a way to randomly group up students**

### Excluded from Initial Release
- Student self-service portal.
- Mobile application version.

## Use Cases
### Adding a Tag to a Student
1. TA logs into the system.
2. TA searches for the student using the search bar.
3. TA selects the student from the results.
4. TA clicks on "Edit Profile".
5. TA adds a tag (e.g., "Needs Assistance") to the profile.
6. TA saves the changes.
7. System updates the student profile with the new tag.

### Assigning a Student to a Course
1. TA logs into the system.
2. TA searches for the student.
3. TA selects the student’s profile.
4. TA clicks on "Assign Course".
5. TA selects the appropriate course from a dropdown list.
6. TA confirms the selection.
7. System updates the student’s profile with the assigned course.

## Non-Functional Requirements
- **Performance:** The system should handle up to 10,000 student records efficiently.
- **Usability:** The interface should be simple and intuitive, requiring minimal training.
- **Scalability:** The system should support multiple TAs accessing student records concurrently.
- **Security:** Student data must be encrypted and accessible only to authorized users.
- **Availability:** The system should have 99.9% uptime to ensure reliability.

## Glossary
- **TA (Teaching Assistant):** An academic assistant helping professors with student management.
- **Student Profile:** A record containing a student's personal, academic, and contact information.
- **Tag:** A keyword or label assigned to a student for categorization.
- **Course Assignment:** The process of linking a student to a specific course in the system.
