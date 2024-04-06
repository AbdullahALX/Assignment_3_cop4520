# Problem one (The Birthday Presents Party):
In this problem, I tried to test the run time using two test cases , first with 10 presents and the second with 500,000 presents.
The time efficiency of the solution is evident from the following test cases:

## Test Case 1:

Number of Presents = 10

Output:
```
Worker #2 is writing a Thank You note.
Worker #2 is writing a Thank You note.
Worker #4 is writing a Thank You note.
Worker #4 is writing a Thank You note.
Worker #2 is adding a present.
Worker #3 is writing a Thank You note.
Worker #4 is checking for the presence of gift #5
Worker #1 is checking for the presence of gift #5
Worker #2 added present #3
Worker #4 is writing a Thank You note.
Worker #1 is adding a present.
Worker #2 is adding a present.
Worker #3 wrote a Thank You note for present #3
Worker #3 is checking for the presence of gift #10
Worker #3 is writing a Thank You note.
Worker #3 is writing a Thank You note.
Worker #3 is adding a present.
Worker #3 added present #9
Worker #3 is checking for the presence of gift #9
Worker #3 is writing a Thank You note.
Worker #3 wrote a Thank You note for present #9
Worker #3 is adding a present.
Worker #3 added present #8
Worker #3 is adding a present.
Worker #3 added present #5
Worker #3 is adding a present.
Worker #3 added present #10
Worker #3 is adding a present.
Worker #3 added present #2
Worker #3 is checking for the presence of gift #6
Worker #3 is checking for the presence of gift #4
Worker #3 is writing a Thank You note.
Worker #4 wrote a Thank You note for present #5
Worker #4 is adding a present.
Worker #4 added present #1
Worker #4 is adding a present.
Worker #4 added present #6
Worker #4 is checking for the presence of gift #3
Worker #4 is adding a present.
Worker #4 added present #7
Worker #4 is writing a Thank You note.
Worker #1 added present #4
Worker #1 is checking for the presence of gift #7
Worker #1 is adding a present.
Worker #2 is checking for the presence of gift #8
Worker #3 wrote a Thank You note for present #7
Worker #2 is checking for the presence of gift #6
Worker #4 wrote a Thank You note for present #2
Worker #3 is writing a Thank You note.
Worker #1 is adding a present.
Worker #2 is writing a Thank You note.
Worker #4 is adding a present.
Worker #3 wrote a Thank You note for present #6
Worker #3 is checking for the presence of gift #6
Worker #1 is checking for the presence of gift #2
Worker #2 wrote a Thank You note for present #1
Worker #3 is checking for the presence of gift #6
Worker #1 is checking for the presence of gift #9
Worker #2 is checking for the presence of gift #1
Worker #4 is writing a Thank You note.
Worker #3 is checking for the presence of gift #7
Worker #1 is adding a present.
Worker #2 is writing a Thank You note.
Worker #4 wrote a Thank You note for present #4
Worker #3 is adding a present.
Worker #4 is writing a Thank You note.
Worker #1 is checking for the presence of gift #4
Worker #2 wrote a Thank You note for present #8
Worker #1 is adding a present.
Worker #3 is adding a present.
Worker #1 is adding a present.
Worker #4 wrote a Thank You note for present #10
Worker #2 is adding a present.
Worker #3 is writing a Thank You note.
10 Thank you notes were written!
Execution time: 21 ms
```

## Test Case 2:

Number of Presents = 500000

Output(snippet):
```
Worker #3 is checking for the presence of gift #125939
Worker #3 is writing a Thank You note.
Worker #4 is adding a present.
Worker #1 wrote a Thank You note for present #365148
Worker #1 is writing a Thank You note.
Worker #1 wrote a Thank You note for present #10934
Worker #1 is adding a present.
Worker #1 is checking for the presence of gift #218021
Worker #1 is adding a present.
Worker #2 is writing a Thank You note.
Worker #3 wrote a Thank You note for present #199568
Worker #3 is checking for the presence of gift #308693
Worker #1 is adding a present.
Worker #4 is writing a Thank You note.
Worker #4 wrote a Thank You note for present #265243
Worker #3 is checking for the presence of gift #499828
Worker #2 wrote a Thank You note for present #333488
Worker #2 is writing a Thank You note.
Worker #2 wrote a Thank You note for present #279932
Worker #1 is adding a present.
Worker #1 is checking for the presence of gift #5440
Worker #1 is writing a Thank You note.
Worker #4 is writing a Thank You note.
Worker #3 is writing a Thank You note.
Worker #2 is adding a present.
Worker #1 wrote a Thank You note for present #293475
500000 Thank you notes were written!
Execution time: 33550 ms
```

## Conclusion:
These tests show how a team works together to say "Thank You" for gifts. With just 10 gifts, they finished really fast, in only 21 milliseconds. But when they had to handle 500,000 gifts, it took them a bit longer, about 33.55 seconds. This shows that the multithreading can work well, whether the number is big or small.



