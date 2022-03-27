# vote-counting-app.service
> Application that helps people enter their votes on to a virtual ballot paper, then counts the votes to select a winner.

## Table of Contents
* [Features](#features)
* [Technologies Used](#technologies-used)
* [How to setup the project?](#setup)
* [How to use this application?](#usage)
* [Room for Improvement](#room-for-improvement)

## Features
- Select the winner from the given list of candidates using preferential vote counting scheme.

## Technologies Used
This is a standalone java application.
- JDK - version 15
- Maven - version 3.0
- JUnit - version 4

## Setup
How to start/run this project?
- You can use an IDE like NetBeans, Intellij IDEA, or Eclipse to run this project from main method.
- You can also run this from commandline -  _mvn exec:java -Dexec.mainClass="app.main.Application"_
- You may also use mvn lifeCycle commands to compile test install etc. For more details https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html 

## Usage
- The application will use the 'src/main/resources/candidates.txt' file provided to list the options that the team members can choose from.
- At start up, the list of options will be displayed on the screen with a unique letter prefix.
- Users can then vote for their choices in preference order by typing in a letter sequence on a single line.
- Follow the instruction in the commandline and complete the voting process.

## Room for Improvement
- TODO 
