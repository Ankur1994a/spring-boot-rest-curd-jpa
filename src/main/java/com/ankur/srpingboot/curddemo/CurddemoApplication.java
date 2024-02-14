package com.ankur.srpingboot.curddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
public class CurddemoApplication {
	public static LinkedList<Double> queue = new LinkedList<>();
	public static void main(String[] args) {
		//SpringApplication.run(CurddemoApplication.class, args);

		//solution("");

	}


		public  int solution(String s) {
			// write the solution here..

			ExecutorService service = Executors.newFixedThreadPool(2);

			Thread producer = new Thread(() -> {
				while(true) {
					if (queue.size() < 3) {
						producer();
						notify();
					} else {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
				}
			});


			Thread consumer = new Thread(() -> {
				while(true) {
					if (queue.size() != 0) {
						consumer();
						notify();
					} else {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
				}
			});

			service.execute(producer);
			service.execute(consumer);

			return 0;
		}

		 void producer() {

			queue.add(Math.random() % 100);

		}

		 void consumer() {
		synchronized (this){
			System.out.println(queue.removeFirst());
			//queue.remove(0);

		}

		}

	}
	

