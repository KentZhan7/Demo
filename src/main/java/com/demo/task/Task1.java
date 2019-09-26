package com.demo.task;

import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.demo.model.UserMessage;

//@Lazy(false)
//@Component
//@EnableScheduling
public class Task1 implements SchedulingConfigurer {
	private static Logger log = LoggerFactory.getLogger(Task1.class);
	
	@Autowired
    private SimpMessagingTemplate template;
	
	public static String cron;
	
	public Task1() {
		cron = "0/5 * * * * ?";
		
		// 開啟新執行緒模擬外部更改了任務執行週期
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(15 * 1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				cron = "0/10 * * * * ?";
//				System.out.println("cron change to: " + cron);
//			}
//		}).start();
	}
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		
		// Format for input
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd hh:mm:ss");
		taskRegistrar.addTriggerTask(new Runnable() {
			@Override
			public void run() {
				// 任務邏輯
				UserMessage um = new UserMessage();
				um.setUserName("System");
				um.setMessage("訊息1");
				um.setCreateDate(LocalDateTime.now().toString(fmt));
				template.convertAndSendToUser("1","/message", um);
				log.info(LocalDateTime.now().toString(fmt) + ":Task1 is running...".toString());
			}
		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				// 任務觸發，可修改任務的執行週期
				CronTrigger trigger = new CronTrigger(cron);
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
			}
		});
	}
}
