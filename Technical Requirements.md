# **Technical Requirements for bug tracking software**

| User Story | Tasks |
| --- | --- |
| Log In | Create log in page. |
| | Add button to log in. |
| | Pass user credentials to OAuth 2.0 for authentication. |
| | Create log in element with fields for user email and password. |
| View reports | Create page to view a bug report. |
| | SQL scripts to fetch data. |
| | Code to request, receive, hold and send data to front end. |
| | Add section for comments. |
| | Add permissions for viewing and editing information fields based on account role.
| Track bug lifecycle | Create 'History' section on bug report page. |
| | Code to track changes to title, status, criticality, assignee and priority. |
| | Prepare SQL schema and table for tracked changes. |
| Assign Criticality to Bug Report | Prepare SQL schema and table for criticality levels |
| | Create dropdown field in bug report to assign criticality. |
| | Code to record criticality level. |
| Create new bug report | Create popup card page for creating new bug report. |
| | Add fields for title, description, project name, category, criticality and priority. |
| | Add field to upload documents. |
| | Add button to save changes. |
| | Code to pass fields to bug report class and database. |
| Create User Account | Create page for account creation. |
| | Create account class. Add name, email address, password and role fields. |
| | Prepare SQL schema and table for account information. |
| | Create method for adding profile picture. |
| Sort reports by date, criticality and priority | Add sorting options for date. |
| | Add sorting options for criticality. |
| | Add sorting options for priority. |
| Filter reports by assignee and category | Add filtering options for assignee. |
| | Add filtering options for category. |
| Bug report Information | Prepare SQL schema and table for bug report information. |
| | Create bug report class. |
| | Code to request, receive, hold and send bug report data to front end. |
| User Account roles | Prepare SQL schema and table for roles. |
| | Populate table with Administrator, manager, developer and tester roles. |
| | Research how to implement permissions and roles in software |
| User landing page | Create user landing page. |
| | Add criticality pie chart widget. |
| | Add priority pie chart widget. |
| | Add category pie chart widget. |
| | Add assigned bug reports widget. |
| | Add code to populate widgets with relevant data. |
| Navigation Menu | Create navigation menu on left of screen. |
| | Add code to change options for admin, manager, dev and test roles. |
| | Add menu entry and link to dashboard. |
| | Add menu entry and link to bug report search. |
| | Add menu entry and link to new bug report. |
| | Add menu entry and link to account management. |
| | Add menu entry and link to log out. |
| | Add menu entry and link to Administrator Management Page. |
| Bug Report Search | Create page for searching bug reports. |
| | Code to search ticket by name or id. |
| Account Management Page | Create page for managing account. |
| | Add field to change password. |
| | Add field to change email. |
| | Add field to change profile picture. |
| Administrator Management page | Create page for adminstrators |
| | Add section to create new account. |
| | Add section to view existing accounts |
| | Add section to edit an account |
| | Add section to create a new project. |
| | Add section to view existing projects. |
| Manager Management page | Create page for managers |
| | Add section to view existing accounts |
| | Add section to create a new project. |
| | Add section to view existing projects. |
| Responsive Design | Add viewports for desktop, tablet and phone. |
| | Change navigation menu from permanent to hamburger menu for tablet and phone views. |
| Demo Accounts | Create demonstration accounts for each of the roles. |
| | Add button and link to demo account selection page. |
| Demo Account data | Populate database with data for demonstration account. |
| | Code to ensure any changes made by a demo account are NOT saved. |
| Notifications | Create popup notification layout. |
| | Code to trigger notification. |
| Assign Categories to bug report | Prepare SQL schema and table for categories. |
| | Create dropdown field in bug report to assign category. |
| Assign Priority to bug report | Prepare SQL schema and table for priority level. |
| | Create dropdown field in bug report to assign priority level. |
| Assign Status to bug report | Prepare SQL schema and table for status'. |
| | Create dropdown field in bug report to assign status. |