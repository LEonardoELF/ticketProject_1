package org.example.less1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис для подсчета счастливых билетов
 */
public class TicketCounterServiceImpl implements TicketService{
    private final Map<Integer, List<Integer>> summs=new HashMap<>();
    private int maxNumber;
    private boolean workDone=false;
    /**
     *
     * @param digitLen
     */
    public TicketCounterServiceImpl(int digitLen) {
        if (digitLen%2!=0 || digitLen <=0) throw new IllegalArgumentException("Передан неверный аргумент "+digitLen);
        this.maxNumber = (int) (Math.pow(10,digitLen/2)-1);
    }

    /**
     * Вывод результата
     */
    @Override
    public void printResult(){
        if (!workDone) throw new IllegalStateException("нечего выводить");
        System.out.println("Билетов счастливы "+ this.getTicketQuantity());
}

    @Override
    public TicketCounterServiceImpl doWork() {
        if(workDone) throw new IllegalStateException("Все уже посчитано до нас");
        for (int num = 0; num <= maxNumber; num++) {
            processingNumber(num);
        }
        workDone=true;
        return  this;
    }


    private int getTicketQuantity() {
        int res=0;
        for(Map.Entry<Integer, List<Integer>> entry : summs.entrySet()){
            res+=Math.pow(entry.getValue().size(),2);
        }
        return res;
    }

    private void processingNumber(int num) {
        int sum = getSum(num);
        if(!summs.containsKey(sum)){
            summs.put(sum,new ArrayList<>());
        }
        summs.get(sum).add(num);
    }

    private int getSum(int num) {
        int sum=0;
        for (int nextNumber = num; nextNumber >0 ; nextNumber/=10) {
            sum+=nextNumber%10;
        }
        return sum;
    }
}
