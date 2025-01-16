package com.example.fetch_employees.model;

import org.springframework.stereotype.Component;

/*
 * Without using synchronized key word output is as below
 * 
 * 2025-01-16T10:24:33.890+05:30  INFO 4136 --- [fetch-employees] [nio-8082-exec-2] c.e.f.aspect.LoggingAspect               : Method learningMultiThreading is about to be called with arguments: []
User 1 is booking 2 tickets...
User 2 is booking 2 tickets...
User 3 is booking 1 tickets...
2025-01-16T10:24:33.906+05:30  INFO 4136 --- [fetch-employees] [nio-8082-exec-2] c.e.f.aspect.LoggingAspect               : Method learningMultiThreading executed successfully with result: <200 OK OK,S,[]>
User 1 successfully booked 2 tickets.
User 2 successfully booked 2 tickets.
Tickets remaining: -2
User 3 successfully booked 1 tickets.
Tickets remaining: -2
Tickets remaining: -2
 */

@Component
public class TicketBooking {
	
    private int availableTickets = 3;

    public void bookTicket(String name, int numberOfTickets) {
        if (availableTickets >= numberOfTickets) {
            System.out.println(name + " is booking " + numberOfTickets + " tickets...");
            try {
                Thread.sleep(100); // Simulate time taken to process the booking
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            availableTickets -= numberOfTickets;
            System.out.println(name + " successfully booked " + numberOfTickets + " tickets.");
            System.out.println("Tickets remaining: " + availableTickets);
        } else {
            System.out.println("Sorry " + name + ", not enough tickets available.");
        }
    }
}
