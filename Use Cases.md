# **Use Cases for bug tracking software**

### Log In
1. User clicks on link from portfolio website.
   1. User enters URL directly into web browser.
      1. User enters incorrect URL into web browser.
      2. User is directed to a 404 page.
      3. User clicks link to redirect to log in page.
2. User is redirected to bug tracker website log in page.
3. User enters correct email and password.
4. User presses 'Log In' button.
5. Request sent to database for credentials matching email address entered by user.
   1. No credentials matching email are found.
   2. Message is displayed indicating user credentials are incorrect.
   3. User re-enters credentials.
   4. User presses 'Log In' button.
   5. Request is resent to database with new credentials.
6. Database returns matching credentials.
7. User entered credentials are verified against existing ones fetched from database.
   1. User entered incorrect password.
   2. Message is displayed indicating user credentials are incorrect.
   3. User re-enters credentials.
   4. User presses 'Log In' button.
   5. Credentials are reverified
8. User is redirected to account dashboard page.
9. Request sent to database for reports assigned to user account.
   1.  No reports are assigned to user account.
   2.  No pie chart widgets appear on dashboard.
   3.  Message indicating no reports assigned to account is displayed.
10. Database returns reports assigned to the account.
11. Assigned reports are sent to user's dashboard.
12. Dashboard is populated with pie charts of tickets assigned to the account.

### Create a New Bug Report
1. User clicks a link to create a new bug report.
2. User is redirected to the Create New Bug Report page.
3. A new report view is created.
4. All fields are empty.
5. User fills in the reqired fields; title, description, criticality and category fields.
   1. User adds to an optional field; attachments or comments.
6. User presses 'Save' button.
   1. Any of the required fields are not filled in.
   2. Unfilled required fields are highlighted in red.
   3. A popup is created requesting that the user fill in all fields.
7. Report is sent to database.
   1. Attachments are saved to a folder (named bug id + title).

### View Bug Report
1. User clicks on link to view a report.
2. Report data is sent to front end.
   1. Report attachments are loaded.
3. User is redirected to View Report page.
4. Report fields are populated with selected report's data.

### Edit an existing Bug Report
1. User opens a report.
2. User alters information in any of the report's fields.
   1. User leaves