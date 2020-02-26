# Challenge_Question_SQLite
csvToSQLite

Short 6 mins explaination
Link: https://youtu.be/J5_vQmtyXmI

1. Summary

This code works on reading CSV file, and it will generate three result files.

The result files including:

Good_result.db : This file saves all the good records from source CSV file. 

	Good records mean each column are match to the head with non-empty inputs.
				 
Bad_result.csv : This file saves all the bad records from source CSV file.

	Bad records mean inputs miss some colomn information, and they are not match to the head.
				 
Journal.log : This file saves total of records, number of successful and number of failed.


2. How to run my code?

My main class name is "ChallengesSqlite.Java". You can start to run my code at this class.
Frist, when you run the code, it will ask you to type the source CSV file absolute path.
Second, it will ask you an other absolute path for where you want to save the result files.

For Example: 

---Please type absolute path from source files---

/MyPath/resources/Entry Level Coding Challenge Page 2.csv

---Please type absolute path for saving files---

/MyPath/resources/



3. Approach, Design chioces, Assumptions

Open library choiced: OpenCSV and sqlite-jdbc

The project requires the code generate three result files after it finish reading the source CSV file.
Because the code only read source file for once, the time complexity should be linear time which it is O(n).  
* n equals to total of the rows from the source CSV file. 

Special case handling:

(1) Verify Qualifiable Rows
	
	In case row' length < head' length, the row misses some information, and it is not qualify
	
	In case row' length == head' length, the row needs to check each column information is not empty, if it has empty column, it is not qualify.
	
	In case row' length > head' length, the row will ignore the extra column, and it goes back to == case to check it has empty column or not.

(2) Create Dynamic Header

	The head' columns is not fixed, and it based on given source CSV file.

(3) Read the file line by line

	The CSV file can be bigger like 1GB or 20GB, and memory is limited.
	Here has two options, using 'iterator' or 'read line by line', I choose the second one.

(4) End line does not count

	Read CSV file has end line which is length == 1 and empty.
	My result file will not include this line.

