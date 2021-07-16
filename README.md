# Quiz Management System College Project

This is the quiz management system which takes in sample files of quiz questions and options, and processes those text files to display quiz questions and respective options in a GUI Java Swing.

## Initial Setup

### Compile the Java Files

To compile all java files to respective `.class` files:

```sh
javac OutputFile.java Sample1.java Sample2.java Sample3.java Sample4.java QuizGUI.java MainQuiz.java
```

The java files should be compiled in the above given order, or else it may give some error.

## Sample Files Structure

All the sample files are stored in `/src/files/` folder.

The files are named as:
* Sample-1-Input.txt
* Sample-2-Input.txt
* Sample-3-Input.txt
* Sample-4-Input.txt

These files contain different formats in which the samples can be given.

All the screenshots of outputs are provided in `/src/output-ss/` folder.

To avoid any errors, follow the above *conventions*. 

**If you want to give your questions for running the app, follow the format given in each sample file and select accordingly which format you like to provide questions in.**

## Usage

After all the initial setup is done, keep your questions in the `/src/files/Sample-x-Input.txt` file. 

Here, x can be 1, 2, 3 or 4.

```
Example:

If you want to give questions in txt file in the format same as Sample-1-Input.txt then keep your questions in /src/files/Sample-1-Input.txt file.
```

### Run the App

In the project root folder, run the following commands:

```sh
java MainQuiz
```

In the runtime execution, it wants the command to run for the file you kept your questions in.

```sh
qconvert <filename>
```

Or,

```sh
qconvert -f <filename>
```

**Example:**
If I have kept my questions as per format of sample 2, then I would keep my questions in `/src/Sample-2-Input.txt` and would run:

```sh
java MainQuiz.java

# In Runtime, would type:
qconvert sample2.txt

# Or,

qconvert -f sample2.txt
```

| Original File Names | Command File Arguments
| :-------------: |:-------------:
| Sample-1-Input.txt | sample1.txt
| Sample-2-Input.txt | sample2.txt
| Sample-3-Input.txt | sample3.txt
| Sample-4-Input.txt | sample4.txt

The command will output cleaned version of the questions you provided in the Sample file, to the `/src/output.txt` file. 

It will also display the questions with their respective options in the **GUI version using Java Swing**.

## Presentation for the App

The Presentation for this app is present in `Presentation_Quiz_System.pptx` file.
