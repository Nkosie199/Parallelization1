JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

java.class:
	$(JC) $(JFLAGS) *.java

CLASSES = MainUI.java \
	ParallelSolution.java \
	NaiveSolution.java \
	ParallelUI.java
	
default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
