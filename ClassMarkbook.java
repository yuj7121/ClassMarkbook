/*
name: Yujin Bae
date: October 27,2020
teacher: Mr. guglielmi
description: this program will load, save, update, and display the marks for a class.
*/

// The "ClassMarkbook" class.
import java.awt.*;
import hsa.Console;
import java.io.*;

public class ClassMarkbook
{
    //instance varaiable declaration section
    Console c;              // The output console
    String[] studentName;   // The names of students in a class
    int[] studentMark;   // The marks of students in a class

    //the constructor
    public ClassMarkbook ()
    {
	c = new Console ("Class File Editor");
    }


    //this method will open a file based on the file name given by the user and load the data into arrays.
    public void loadFile ()
    {
	//variable declaration section
	BufferedReader b; //defines a buffered reader object
	String fileName;
	String line;    //this variable will store the reading data from the file
	String str; //this variable will store untrimmed data
	int size;     //this variable is the size of the class. (number of students)

	//the title
	c.println ("You have chosen to load a file. ");
	c.println ();

	//gets the file name from the user
	c.println ("Please enter the file name. ");
	fileName = c.readLine ();
	c.println ();

	//tries to open the file
	try
	{
	    b = new BufferedReader (new FileReader (fileName));
	    c.println ("Loading the file...");

	    line = b.readLine ();
	    if (!line.equals ("yaya"))
	    {
		c.println ("Not a compatible file. Please Try Again");
	    }

	    //these are file formats that needs to be skipped
	    str = b.readLine ();
	    size = Integer.parseInt (str.substring (21));
	    b.readLine ();
	    b.readLine ();

	    //defining arrays size
	    studentName = new String [size];
	    studentMark = new int [size];

	    //loop that loads the data into the arrays.
	    for (int count = size ; count > 0 ; count--)
	    {

		line = b.readLine ();
		str = line.substring (7, 29);
		studentName [size - count] = str.trim ();

		str = line.substring (30);
		studentMark [size - count] = Integer.parseInt (str.trim ());
	    }
	    c.println ("File loaded successfully!");
	}
	catch (IOException e)
	{
	    c.println ("Something went wrong. Please try again. ");
	}
	c.println ();

    }


    // This method will writes data from the arrays to a pre-existing or newly created file.
    public void writeFile ()
    {
	PrintWriter p; //defines a printwriter object

	//title
	c.println ("You have chosen to save to a file");
	c.println ();

	//gets the file name from the user
	c.println ("Please enter the file name. ");
	String fileName = c.readLine ();

	//tries to open the file
	try
	{
	    //tries to open the file
	    p = new PrintWriter (new FileWriter (fileName));
	    c.println ("Saving to the file...");

	    //the format of the file
	    p.println ("yaya");
	    p.print ("File size(students): ");
	    p.println (studentName.length);
	    p.println ();
	    p.println ("Index  Student Name           Student Mark");

	    //this loop writes the data in the array to the file
	    for (int i = 0 ; i < studentName.length ; i++)
	    {
		p.print (i + 1);
		//the spacing after the index number
		if (i < 10)
		{
		    p.print ("      ");
		}
		else
		{
		    p.print ("     ");
		}
		//the name
		p.print (studentName [i]);
		//the spacing after the name
		for (int s = 23 ; s > studentName [i].length () ; s--)
		{
		    p.print (' ');
		}

		//the mark
		p.println (studentMark [i]);

	    }

	    p.close ();
	    c.println ("File saved successfully!");
	}
	catch (IOException e)
	{
	    c.println ("Something went wrong. Please try again. ");
	}
	catch (NullPointerException e)
	{
	    c.println ("Therer is no data to save. ");
	}
	c.println ();
    }


    //this method will display display the students in the class with their index location, name, and mark.
    public void displayStudents ()
    {
	try
	{
	    c.println ("You have chosen to display the students. ");
	    c.println ();

	    c.println ("The size of this class is: " + studentName.length);
	    c.println ();
	    c.println ("Index  Student Name           Student Mark");

	    //this loop outputs the data in the array to the screen
	    for (int i = 0 ; i < studentName.length ; i++)
	    {
		c.print (i + 1);
		//the spacing after the index number
		if (i < 10)
		{
		    c.print ("      ");
		}
		else
		{
		    c.print ("     ");
		}
		//the name
		c.print (studentName [i]);
		//the spacing after the name
		for (int s = 23 ; s > studentName [i].length () ; s--)
		{
		    c.print (' ');
		}

		//the mark
		c.println (studentMark [i]);
	    }
	}
	catch (NullPointerException e)
	{
	    c.println ("There is no data to display");
	}
	c.println ();
    }


    //this method will call the class average from calculateAverage and display it.
    public void displayAverage ()
    {
	try
	{
	    //title
	    c.println ("You have chosen to see the average of the class. ");
	    c.println ();
	    c.println ("The average mark of this class rounded to the nearest digit is: ");
	    c.println (calculateAverage (studentMark));  //calls the calculateAverage method
	}
	catch (NullPointerException e)
	{
	    //if the user did not load any data before
	    c.println ("There is no mark to be calculated");
	}
	c.println ();
    }


    //this method will updtae a mark of a specific student indicated by the user.
    public void updateStudent ()
    {
	//variable declaration
	PrintWriter p;            //a printwriter
	String fileName;          //the name of the file
	String student;           //the name of the student
	String str;               //for errortrapping the mark input
	int mark = -100;                 //the new mark to be updated
	boolean found = false;    //wether the student has been found in the array
	int index = 0;            //the index location search is going on

	c.println ("You have chosen to update the mark of a student");
	c.println ();

	try
	{
	    //gets the name of the file from the user
	    c.println ("Please confirm the name of the file you wish to edit");
	    fileName = c.readLine ();
	    c.println ();
	    p = new PrintWriter (new FileWriter (fileName));

	    do
	    {
		//sets the index back to 0 every time the loop executes again
		index = 0;
		//gets the name of the student fromt he user
		c.println ("Please enter the full name of the student");
		student = c.readLine ();
		c.println ();

		//this loop will find the index of the student
		while (!found && index < studentName.length)
		{
		    c.println ("Finding the student.....");
		    if (studentName [index].equals (student))
		    {
			found = true;
			c.println ("Student found successfully!");
		    }
		    else
		    {
			index++;
		    }
		}
		if (!found)
		    c.println ("Student not found. Please try again");
		c.println ();
	    }
	    while (!found);

	    //this loop will take the mark input
	    do
	    {
		//prompt
		c.println ("Please enter the new mark. ");
		str = c.readLine ();
		//error trap
		try
		{
		    mark = Integer.parseInt (str);
		}
		catch (NumberFormatException e)
		{
		    c.println ("That is not an integer. ");
		}
		studentMark [index] = mark;
	    }
	    while (mark <= 0 || mark >= 100);

	    //writes the whole file again
	    p.println ("yaya");
	    p.print ("File size(students): ");
	    p.println (studentName.length);
	    p.println ();
	    p.println ("Index  Student Name           Student Mark");

	    //this loop writes the data in the array to the file
	    for (int i = 0 ; i < studentName.length ; i++)
	    {
		p.print (i + 1);
		//the spacing after the index number
		if (i < 10)
		{
		    p.print ("      ");
		}
		else
		{
		    p.print ("     ");
		}
		//the name
		p.print (studentName [i]);
		//the spacing after the name
		for (int s = 23 ; s > studentName [i].length () ; s--)
		{
		    p.print (' ');
		}

		//the mark
		p.println (studentMark [i]);

	    }
	    p.close ();
	    c.println ("Mark updated successfully!");
	}
	catch (IOException e)
	{
	    c.println ("Something went wrong. Please Try again");
	}
	catch (NullPointerException e)
	{
	    c.println ("There is no data to edit to.");
	}
	c.println ();
    }


    //this method will create a new class with the size, student names and marks given by the user.
    public void createClass ()
    {
	//variable declaration section
	int size = 0;       //the size of the new class inputed by the user
	String str;     //a variable fpor errortrapping the size
	boolean error = true;   //variable for errortrappings

	//title
	c.println ("You have chosen to create a new class. ");
	c.println ();

	//this loop will get the size of the class from the user
	do
	{
	    //prompt for class size
	    c.println ("Please enter the number of students in the new class. ");
	    str = c.readLine ();
	    c.println ();

	    try
	    {
		size = Integer.parseInt (str);
		studentName = new String [size];
		studentMark = new int [size];
		error = false;
	    }
	    catch (NumberFormatException e)
	    {
		c.println ("Not a number. Please try again. ");
	    }
	}
	while (error);

	//this loop will get iinput fromt he user for the student names and marks
	for (int i = 0 ; i < size ; i++)
	{
	    //prompt for student name
	    c.println ("Please enter the name of the " + (i + 1) + "th student. ");
	    studentName [i] = c.readLine ();

	    //this loop gets the mark form the user
	    do
	    {
		//prompt for student mark
		c.println ("Please enter the mark of the student " + studentName [i]);
		str = c.readLine ();

		//this block checks if the input is correct
		try
		{
		    studentMark [i] = Integer.parseInt (str);
		    error = false;
		}
		catch (NumberFormatException e)
		{
		    c.println ("Not a number. Please try again");
		    error = true;
		}
	    }
	    while (error);
	}
	c.println ();
	c.println ("A new class was made successfully!");
	c.println ();
    }


    //this method will calculate the averafe mark of the class and return it.
    //return type: int
    //parameter: array with the marks
    private int calculateAverage (int[] a)
    {
	//variable declaration section
	int average = 0;     //the average mark that will be calculated and returned
	double total = 0;    //sum of all the marks
	int num = a.length;  //the number of students

	//adds all the marks
	for (int n = num - 1 ; n >= 0 ; n--)
	{
	    total += a [n];
	}


	//divides it by the number of students
	total /= num;
	//rounds it to an integer
	average = (int) (total + 0.5);

	return average;
    }


    //this method will control the main menue for the program
    public void mainMenu ()
    {
	boolean exit = false;
	int choice = 0;

	do
	{
	    c.clear ();
	    c.print (' ', 33);
	    c.println ("Class Markbook");

	    c.println ("Menu Options: ");
	    c.println ("1 - Load a file");
	    c.println ("2 - Save to a file");
	    c.println ("3 - Display Students");
	    c.println ("4 - Display Class Average");
	    c.println ("5 - Update Student Mark");
	    c.println ("6 - Create a new class");
	    c.println ("7 - Exit the program");
	    c.println ();

	    try
	    {
		String str = c.readLine ();
		choice = Integer.parseInt (String.valueOf (str));
		c.println ();

	    }
	    catch (NumberFormatException e)
	    {
		c.println ("That is not a number. Please try again. ");
		c.println ();
	    }

	    switch (choice)
	    {
		case 1:
		    c.clear ();
		    loadFile ();
		    c.println ("Press any key to continue");
		    c.getChar ();
		    break;

		case 2:
		    c.clear ();
		    writeFile ();
		    c.println ("Press any key to continue");
		    c.getChar ();
		    break;

		case 3:
		    c.clear ();
		    displayStudents ();
		    c.println ("Press any key to continue");
		    c.getChar ();
		    break;

		case 4:
		    c.clear ();
		    displayAverage ();
		    c.println ("Press any key to continue");
		    c.getChar ();
		    break;

		case 5:
		    c.clear ();
		    updateStudent ();
		    c.println ("Press any key to continue");
		    c.getChar ();
		    break;

		case 6:
		    c.clear ();
		    createClass ();
		    c.println ("Press any key to continue");
		    c.getChar ();
		    break;

		case 7:
		    exit = true;
		    break;

		default:
		    c.println ("Press any key to continue");
		    c.getChar ();
	    }
	}


	while (!exit);

	//gooodbye message
	c.println ("This program was created by Yujin Bae. ");
	c.println ("Thanks for using my program. ");
	c.println ("Goodbye!");

	c.println ("Press any key to exit the program. ");
	c.getChar ();
	System.exit (0);
    }


    public static void main (String[] args)
    {
	ClassMarkbook m = new ClassMarkbook ();

	m.mainMenu ();
	
    } // main method
} // ClassMarkbook class


