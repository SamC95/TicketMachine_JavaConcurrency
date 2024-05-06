# Java & Finite State Process (FSP) Concurrency Project
## Ticket Machine
By [Sam Clark](https://github.com/SamC95)

This project was coursework for my third year module: Concurrent Systems -- at the University of Westminster 

## Contents
* [Project Brief](https://github.com/SamC95/TicketMachine_JavaConcurrency#project-brief)
* [Technologies](https://github.com/SamC95/TicketMachine_JavaConcurrency#technologies)
* [Responsibilities](https://github.com/SamC95/TicketMachine_JavaConcurrency#responsibilities)
* [Key Learnings](https://github.com/SamC95/TicketMachine_JavaConcurrency#key-learnings)
* [Achievements](https://github.com/SamC95/TicketMachine_JavaConcurrency#achievements)
* [Challenges](https://github.com/SamC95/TicketMachine_JavaConcurrency#challenges)
* [Conclusions](https://github.com/SamC95/TicketMachine_JavaConcurrency#conclusions)

## Project Brief
* Develop FSP Processes to model the Ticket Machine, Passengers, and Technicians
* Utilize the FSP Process Composition Analysis & Design Form for the FSP process composition of the complete parallel system
* Incrementally develop the system using the [LTSA](https://www.doc.ic.ac.uk/ltsa/) tool
* Translate the abstract FSP program into a multi-threaded Java program
* Use threads, thread groups, and monitors for concurrency. Not Semaphores or other explicit synchronziation mechanisms.

## Technologies

<p float="left">
  <img src="https://raw.githubusercontent.com/yurijserrano/Github-Profile-Readme-Logos/042e36c55d4d757621dedc4f03108213fbb57ec4/programming%20languages/java.svg" width="100" />
  <img src="https://raw.githubusercontent.com/yurijserrano/Github-Profile-Readme-Logos/042e36c55d4d757621dedc4f03108213fbb57ec4/ides/intellij.svg" width="100" />
</p>

* [Java](https://dev.java/)
* [Labelled Transition System Analyser (LTSA)](https://www.doc.ic.ac.uk/ltsa/)
* [IntelliJ](https://www.jetbrains.com/idea/)

## Responsibilities

### LTSA Phase

The initial requirements involved developing the Finite State Processes to define the behaviour that the system should have, this involved using the LTSA tool to define the expected behaviour of the Passengers, Toner Technician, Paper Technician and the Ticket Machine itself. 

Below shows the code in the LTSA tool: 

![image](https://github.com/SamC95/TicketMachine_JavaConcurrency/assets/132593571/078ee3d4-fb24-41d2-b59d-b8eb4a98f706)

Below shows an example of the animated version of the Finite State Processes showing the steps: 

![image](https://github.com/SamC95/TicketMachine_JavaConcurrency/assets/132593571/32918ec3-4dde-4b34-9ba4-838d274b329b)


### Java Implementation Phase

After the LTSA tool had been used to develop the finite state processes, the next step was to translate it into a fully functional multi-threaded Java program. Below shows some example code of how the Toner Technician is implemented: 

```java
public synchronized void run() {
        final int REFILL_ATTEMPTS = 3;
        int refillsComplete = 1;
        int loopsSpentWaiting = 0;

        while (refillsComplete <= REFILL_ATTEMPTS && !terminateThread) {
            try {
                int sleepTime = new Random().nextInt(5000) + 2000;
                this.wait(sleepTime);

                if (ticketMachine.getTonerLevel() == 0) {
                    ticketMachine.refillToner();
                    System.out.println("Toner technician refilled toner (Refill " + refillsComplete + ")");
                    refillsComplete++;
                    loopsSpentWaiting = 0;
                }
                else if (loopsSpentWaiting == 4) {
                    System.out.println("Passengers finished printing tickets, toner refill attempts stopped.");
                    stopThread();
                }
                else {
                    System.out.println("No toner refill needed... waiting.");
                    loopsSpentWaiting++;
                    this.wait(sleepTime);
                    this.notifyAll();
                }
            }
            catch (InterruptedException error) {
                throw new RuntimeException(error);
            }
        }

        System.out.println("Toner technician finished toner refills");
    }
```

The toner technician and paper technician function essentially in the same fashion, they will check the levels and if it is 0 then they will refill and the refillsComplete counter will increase. If the levels are not 0 then they will wait and notify other threads. loopsSpentWaiting is used to prevent deadlocks in the event that all tickets are printed before the technician has completed their three refills, if it checks four times in a row without the levels being 0 then it will break out of the loop and stop the thread.

## Key Learnings

* Knowledge of modelling concurrent systems using FSP & LTSA.
* Understanding of how to implement concurrent code in Java using Threads, ThreadGroups and Monitors.
* Allowed for me to further develop my general skills in Java.

## Achievements

* Effectively managed to implement the concurrency features of Java.
* Translated the design from the FSP to a multi-threaded Java application.

## Challenges

The most challenging aspects of the project were the use of the LTSA tool to model the FSP, as I found that it took more time than I expected to be able to correctly implement the behaviour that I wanted from the system.

I also feel that whilst my implementation achieved the design goals set out in the brief, I could have implemented the process of passengers printing their tickets more effectively. Rather than having the passenger print one ticket at a time, I feel it would have been better to instead have the machine check if the necessary paper or toner was available for the full amount of tickets they wanted to print and then just printed them all at once; as this is more in-line with real world usage of a system like this.

## Conclusions

This project allowed me to gain a basic understanding of concurrent design in a Java application, I would like to expand this knowledge in future projects by implementing more complex requirements using concurrency, as well as better implementing ThreadGroups into the design than I did in this project. It also taught me that the planning aspects around the use of the LTSA tool to model the Ticket Machine system before starting on the actual implementation in Java, was an extremely beneficial process that made the implementation significantly more manageable.
