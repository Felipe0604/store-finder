package com.jumbo.store.finder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PreDestroy;

/**
 * Store Finder Application
 * @author Felipe Gonzalez
 */
@Slf4j
@SpringBootApplication
public class FinderApplication {

	public static final String STARTUP_MESSAGE = "\n\n****************************************" +
												 "\n*                                      *" +
												 "\n*          Successfully Started        *" +
												 "\n*                                      *" +
												 "\n****************************************\n";

	public static final String SHUTTING_DOWN_MESSAGE = "\n\n****************************************" +
													   "\n*                                      *" +
												       "\n*             Shutting Down            *" +
													   "\n*                                      *" +
												       "\n****************************************\n";


	public static void main(String[] args) {
		SpringApplication.run(FinderApplication.class, args);
	}

	/**
	 * On Start Up Event
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void onStartup() {
		log.info(STARTUP_MESSAGE);
	}


	/**
	 * On Destroy Event
	 */
	@PreDestroy
	public void onDestroy() {
		log.info(SHUTTING_DOWN_MESSAGE);
	}

}
