# Fully Functional paint

This repository is a test to practise stuff about I/O Monad and functional programming

## Libraries

It has been implemented with the following languages and libraries:

- Language: Scala 2.12
- Build tool: SBT
- External libraries:
  - Scalaz: Scala library for functional programming. Used to provide IO Monad.
  - Scalatests: Scala library for testing. This is used in the test section.

## CODE DESCRIPTION

### Functional programming

I have implemented an application trying to follow the principles of functional programming. This has a deep influence both on the code and the performance. Here I'll describe some particularities and decisions that I have taken.

First, there are no variables or any kind of mutable data. This is very different to the "traditional" approach of saving the state of the application in a set of variables and making them evolve along the execution. Here the result of an expression is always a different object, what makes some operations more costly. In particular, any change of the canvas does not update an internal structure but creates a new updated one. Although this increments the cost of the operations and Scala has support for variables, I decided to be strictly adhered to functional programming. Anyway this is all encapsulated in a single class (CanvasState), so it is trivial to change it if at some point the overhead is too high.

The exceptions and errors are here modeled as classes. With this approach and the extensive use of Try monad, errors and exception have been incorporated in the normal application flow, becoming an acceptable result and never breaking the referential transparency. Also, I have implemented every kind of exception as a different case class, getting a further control over them. This allows things as pattern matching over classes, a technique used in the ExecutionController to determine what to do after an exception.

I have decided to implement the "quit" command as an exception. The idea behind that is that it does not operate over a Canvas as all the others do, and instead has an influence on the execution flow.

As we will be reading input from the user and then writing things, it is impossible to  avoid side effects. In order to make these operations functional I have used IO Monad from Scalaz, encapsulating I/O and not breaking referential transparency.

Our program must run until the user desires to exit. I have implemented this on a functional way by creating a "executeWhileNotQuit" function in ExecutionController which performs a single step and then calls itself again. Note that it employs tail recursion, so this has no influence on the execution stack or heap.

Besides these decisions I think that the code is quite simple and clear to understand.


#### Code organization
I have organized the code in the following modules:

- src: source files
  - model: contains the data models.
  - exceptions: classes for the exceptions.
  - controller: tools to control the execution: read the user input, create the desired action, and perform it. I have implemented each action on a different class to provide modularity
  - configuration: configuration files. It includes the initialization of the controller (so if a different one is implemented, it only needs to be changed here) and declaration of constants. I have included a guard on the length of the user input as a simple security measure to avoid buffer overflows.
  - command: every possible user command. They all inherit from Command to provide polymorphism.

- test: test files
  - unit: unit testing for each command and controller, making sure that they work as expected under different conditions (happy path and a wide set of errors)
  - integration: integration tests.
  - fixtures: tools employed in the tests.

### Scalability and resource consumption

The memory consumption of every operation is O(NxM), being NxM the size of the canvas

The time scalability of the commands is as follows:
- creating a canvas: O(NxM). This is not O(1) as one could expect because the canvas is initialized, which takes a non negligible time for large input values.
- modifying a pixel of the canvas: O(NxM). As it has been previously explained this operation creates a full copy of the canvas
- drawing a line/rectangle: O(NxMxL) being L the length of the line/rectangle. These operations calls the previous one for each pixel to draw.
- bucket fill: O((NxM)^2). We create a copy for every pixel, and in the worst case we have to change all the pixels in the canvas   

### Testing

I have included both unit and integration tests.
