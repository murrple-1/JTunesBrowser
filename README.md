JTunesBrowser
=============
Created by Murray Christopherson  
Updated February 2014

About the Program
-----------------
This program is was a school project that I have cleaned up for portfolio
purposes. It was a early attempt to write a GUI interface using the Swing library.  
It scans through a directory of music files, parses the title and artist from the file,
and then allows you to search and filter through them in a GUI.  
Please note that due to a caveat of the original project, the music files are not actually
MP3s, but are TXT files, where the metadata is parsed out of the file name. In a future
version, I may try to actually read MP3 ID3 tags for the data.  
It is recommended you read the source code to see exactly what the code is
doing.  
Please visit my portfolio at http://people.scs.carleton.ca/~mchristo/

If you discover any bugs or unclear/inconsistent documentation, please contact me
at murraychristopherson@gmail.com with a description of what happened, and I
will try and have a fixed version up shortly.

Installation
------------
This archive comes packaged with the binaries, since they are platform-independent.
In order to run it, you must have a Java VM installed. To recompile the program, 
you must have  a Java compiler installed. Either the offical Oracle/Sun
version, or the GCC variant is recommended.  
I recommend also using Eclipse (http://www.eclipse.org) for viewing, editing and
compiling.

Running the Program
-------------------
The program is run through a command line interface. The general form is 
```
$> java JTunesBrowser
```
Alternatively, you can run the program from within Eclipse.
