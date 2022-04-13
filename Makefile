classpath := .:libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar
runner := org.junit.runner.JUnitCore

autograder: MyMinHeap.class MyPriorityQueue.class Ticket.class MyAutograder.class
	java MyAutograder

test: MyMinHeap.class PublicTester.class
	javac MyMinHeap.class
	java -cp $(classpath) $(runner) PublicTester

MyMinHeap.class: MyMinHeap.java
	javac -cp $(classpath) PublicTester.java

PublicTester.class: PublicTester.java
	javac -cp $(classpath) PublicTester.java
MyMinHeap.class: MyMinHeap.java
	javac MyMinHeap.java

MyPriorityQueue.class: MyPriorityQueue.java
	javac MyPriorityQueue.java

Ticket.class: Ticket.java
	javac Ticket.java

MyAutograder.class: MyAutograder.java
	javac MyAutograder.java

clean:
	rm *.class