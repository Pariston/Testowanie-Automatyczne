Given a messenger

When set variables to ab and inf.ug.edu.eu
Then the output connection status should be 1
Then the wrong output sending a message status should be 2

When set variables to some message and inf.ug.edu.pl
Then the output connection status should be 0
Then the output sending a message status should be 0 or 1

When set variables to x and  
Then the output connection status should be 1