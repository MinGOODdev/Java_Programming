package com.study.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class FixedThreadApplication {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    IntStream.range(0, 10)
            .mapToObj(value -> executorService.submit(() -> {
              try {
                if (value % 2 == 0) {
                  System.out.println(value + "쉬는 중");
                  Thread.sleep(1000L);
                }
                return String.valueOf(value * 2);

              } catch (InterruptedException e) {
                return "";
              }
            }))
            .forEach(action -> {
              try {
                System.out.println("# Thread Info: " + executorService.toString() + " # Result Value: " + action.get());

              } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
              }
            });
  }

}
