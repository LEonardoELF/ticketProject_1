package org.example.less1;

import org.example.less1.service.NearestTickets;
import org.example.less1.service.TicketCounterServiceImpl;
import org.example.less1.service.TicketService;
import org.example.less1.service.TicketServiceBuilder;


public class TicketMap {
    //private static TicketService service =new TicketCounterServiceImpl(6);
    public static void main(String[] args) {
        TicketServiceBuilder builder =()->6;
        builder.build().doWork().printResult();

        NearestTickets nearestTickets=new NearestTickets(builder.produceDigit());
        nearestTickets.doWork().printResult();
    }

}
