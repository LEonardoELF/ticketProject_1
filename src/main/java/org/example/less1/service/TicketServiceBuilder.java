package org.example.less1.service;

/**
 * Интерфейс строитель экземпляров тикетсервис
 */
public interface TicketServiceBuilder {
    Integer produceDigit();
    default TicketService build(){
        return new TicketCounterServiceImpl(produceDigit());
    }
}
