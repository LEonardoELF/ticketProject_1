package org.example.less1.service;

import java.util.Arrays;

public class NearestTickets implements TicketService {
    private int maxNumber;
    private boolean workDone=false;
    int[] digits;
    private int ticket;
    private int distance;

    public NearestTickets(int digitLen) {
        if (digitLen%2!=0 || digitLen <=0) throw new IllegalArgumentException("Передан неверный аргумент "+digitLen);
        this.maxNumber = (int) (Math.pow(10,digitLen)-1);
        this.ticket=0;
        this.distance=maxNumber;
        digits=new int[digitLen];
    }

    @Override
    public void printResult() {
        if (!workDone) throw new IllegalStateException("нечего выводить");

        System.out.printf("Минимальное расстояние %d\t%0"+this.digits.length+ "d\t%0"+this.digits.length+"d",distance,ticket,ticket-distance);
    }



    @Override
    public TicketService doWork() {
        for (int currentTicket = 1; currentTicket < maxNumber; currentTicket++) {
            if(!isLucky(currentTicket)) continue;

            int currentDistance=currentTicket-ticket;
            if(currentDistance<distance){
                distance=currentDistance;
                ticket=currentTicket;
            }
        }
        workDone=true;
        return this;
    }

    private boolean isLucky(int ticket){
        Arrays.fill(digits,0);
        for (int i = 0,nextNum=ticket;nextNum>0;nextNum/=10, i++) {
            this.digits[i]=nextNum%10;
        }
        int firstSumm=0,lastNum=0;
        for (int i = 0; i < this.digits.length; i++) {
            if(i<this.digits.length/2) {
                firstSumm+=this.digits[i];
                continue;
            }
            lastNum+=this.digits[i];
        }
        if(firstSumm==lastNum) return true;
        return false;
    }
}
