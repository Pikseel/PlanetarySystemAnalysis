# How to run
Got help when writing makefile, wanted it to not re-link, also added test case commands:
make run    (run with keyboard input)
make run1   (run the test1.txt test case) (all succeeds)
make run2   (run the test2.txt test case) (all fails)

# AI Decleration
Got help with the test cases from ChatGPT, wanted to not left anything uncovered.
Node and SensorData classes are completelly my product.
Main and PlanetarySystem classes are a mix of my own thinking and coding with X's Grok LLM.
Many exceptions from those classes are created with the help of Grok following my prompt (What exceptions should I cover in this class?)
The method getPathTo's fail case was challenging, using boolean to check if the path is valid or not came out from Grok aswell.
Got plenty of help when designing the handling of input. (.nextline(), .trim(), .split()).

# Where program will fail (what i found but didn't cover)
Some edge cases which this program will fail:
--> run the program with "make run"
--> input "Ctrl+D"
--> program will crash

--> run the program with "make run"
--> input "Ctrl+C"
--> program will crash

Learnt that's because the scanner that i use in Main method is not fully covered. (Not in a try/catch block).
Yet i left it as is.