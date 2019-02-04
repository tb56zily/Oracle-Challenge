                                        ********************************
                                                    Bowling Game
                                        ********************************

The project Bowling-Game is developed to calculate score of a bowling game by receiving 'pins-down' for each ball.
In a bowling game there are maximum of ten frames where each frame has maximum of two balls to strike the pins, 
except the bonus ball. Bonus ball is a third ball in the last frame of a bowling game. Bonus ball is added only 
when the player strikes down all 10 pins either in first ball or second ball.

Each frame of a game can be one of the three types. a) MISS - the frame with maximum pins down are less than 10, 
b) SPARE - the frame where ball 2 has downed all pins remained after ball 1 and c) STRIKE - the frame where 
ball 1 downed all 10 pins and no second ball is allowed. For a MISS frame, the frame score is calculated right away 
by adding total pins down by ball 1 and ball 2. For SPARE, frame the score is calculated by adding pins downed by
next one ball. For STRIKE frame, the score is calculated by adding pins downed by next two ball. 


----------------------------
Specification and Execution:
----------------------------

The application is developed using Java version 8 and Maven Build in IntelliJ IDE. Maven dependicies are added 
for log4j and Junit. The implementation is tested using Junit Test cases. To execute the project, open the project 
in IntelliJ IDE and build the project by executing the command 'mvn clean install'. Run the Test class for checking 
different inputs.


---------------
APIs Provided:
---------------

Below are the APIs provided in the application.

1. void roll(int pinsDown): Rolling a ball which strikes the pins. The pins down are input to this API.
2. int getFrameScore(int frameNumber) : Retrieves score for a frame with input frameNumber.
3. int[] getAllFrameScore(): Retrieves score for all frames.
4. int getTotalScore(): Retrieves final score of a game.
5. String displayScoreBoard(): Displays score board with Frame details, Ball details in String format.
6. save(); Saves the game into a text file.


------
Tests:
------

Run the Class BowlingGameTest for test cases.
