package com.glasstrainer.utils;

import com.glasstrainer.entity.Pulse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Serhat CAN on 09.05.2015.
 */

@Component
public class PulseDataCorrection {

    final private static int queueSize = 5;
    private Queue pulseQueue;
    private Integer lastRate;

    PulseDataCorrection() {
        pulseQueue = new ArrayBlockingQueue<Integer>(queueSize);
    }

    public Pulse fix(Pulse pulse) {

        Integer rate = Integer.parseInt(pulse.getRate());
        Boolean isBetween50And150 = rate > 60 && rate < 100 ? true : false;
        int listSize = pulseQueue.size();

        if (listSize > 0) {
            for (int i = 0; i < listSize; i++) {
                if (isBetween50And150 && (Math.abs(rate - lastRate)) <= 20) {
                    if (pulseQueue.size() != queueSize) {
                        pulseQueue.add(rate);
                    } else {
                        pulseQueue.poll();
                        pulseQueue.add(rate);
                    }
                }
            }
            lastRate = rate;

            pulse.setRate(getAverage(pulseQueue).toString());

        } else if (isBetween50And150) {
            lastRate = rate;
            pulse.setRate(lastRate.toString());

        } else {
            lastRate = null;
        }

        return pulse;
    }

    private Integer getAverage(Queue<Integer> queue) {
        Integer total = 0;
        int size = queue.size();

        if(size == 0) {
            return null;
        }

        for (int i = 0; i < size; i++) {
            total = total + queue.poll();
        }
        return total / size;
    }

}
