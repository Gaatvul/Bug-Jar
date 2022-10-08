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

### View Bug Report
