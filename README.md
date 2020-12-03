# Mini_Twitter

In this programming assignment, you will build a Java-based Mini Twitter with graphical user interface (GUI) using ​Java Swing​. This is going to be a pure desktop program, without web or mobile components. The goal of this assignment is to let you experience how to apply design patterns to build extensible software systems.
The basic functions of the system include:
1. There is a centralized admin control panel to create ​users​ and ​user groups​.
2. A ​user has 1) an unique ID; 2) a list of user IDs that are following this user (followers); 3) a list of user IDs being followed by this user (followings); 4) a news feed list containing a
list of Twitter messages.
3. A ​user group has an unique ID, which can be used to group users. A user group can
contain any number of users. The same user can only be included in one group. Of course, a user group can contain other user groups recursively. There is always a root group called ​Root​ to include everything.
4. Users can choose to follow other users (not user groups) by providing the target user ID. Unfollow is not required.
5. Users can also post a short Tweet message (a String), so that all the followers can see this message in their news feed lists. Of course, the user can also see his or her own posted messages.
6. A few analysis features are needed in the admin control panel: 1) output the total number of users; 2) output the total number of groups; 3) output the total number of Tweet messages in all the users’ news feed; 4) output the percentage of the positive Tweet messages in all the users’ news feed (the message containing positive words, such as good, great, excellent, etc.) Free free to decide the positive words.
You need to build a GUI for the functions above. The suggested UIs are shown below. The figures should be self-explaining. A few notes are:
1. All the UIs and layouts are just the suggested design for you to use. You can re-design any of these as long as the functions are supported.
  
2. The Admin Control Panel is the main UI you will see by running the program. This should be the entrance to the program.
3. You can add users/groups with Buttons and TextAreas. Of course, the TreeView should be updated as well whenever new users/groups are being added.
4. For groups, you should display them with a different notation (maybe a folder icon or something else just to distinguish it from the users).
5. Deleting users/groups is not required.
6. Clicking on the 4 buttons at the bottom-right will output the correspondent information.
You can simply popup a dialog to display the value, or use some other widgets.
7. When you select a user in the tree, clicking on the Open User View button will open the
User View as shown in Figure 2. You can open multiple User Views for different users.
8. In the User View, it will display the current users you are following in a ListView (followings). You can add new users to follow by using the TextArea and Button.
Unfollow is not required. Displaying your followers is not required.
9. The User View also shows the current news feed list for this user in a ListView.
10. You can post a new Tweet with the TextArea and Button. Once you click the Post button. It will add the message to all your followers’ news feed list, as well as your own news feed list.
11. Whenever a new message is posted, all the followers’ news feed list view should be updated and refreshed automatically.
PART 2
In this programming assignment, you will be upgrading your Mini Twitter to add the following features to it:
1. User/Group ID verification. One more button should be added to the main Admin UI to validate if all the IDs used in the users and groups are valid, based on the following two criteria: 1) all the IDs must be unique - there should not be duplicated IDs; 2) all the IDs should not contain spaces. You need to show a dialog (or print in console if GUI is not available) to tell whether all the IDs are valid or not. Note: you only need to output the validation result. You do NOT need to fix or prevent the invalid inputs.
2. Add creation time attribute to User and Group. Both User and Group should include a new attribute - ​creationTime​. The type should be ​long​. And its value should be given whenever the object is created. You can call ​System.currenttimemillis() to get the current system timestamp. When you open the User View, this time value should be display (or printed out) somewhere in the UI (or console).
3. Add last update time attribute to User. Similar to feature 2 listed above, an extra attribute - ​lastUpdateTime should be included in User, with the type to be ​long​. This attribute should be updated whenever a new tweet is posted, for both the user and the all the followers (in other words, whenever the news feed gets updated). Similar to 2, you also need to display the time in the User View UI (or console).
4. Find the last updated User. One more button should be added to the main Admin UI to output the ID of the user who made the last update. This should be based on the time attribute you added in feature 3. When users share the same ​lastUpdateTime​, you do not need to sort them. Just outputting one of them is fine. Again, output the ID in your UI or console.
