package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email";

    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    //@Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 1000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "Currently in database you got: " + size + " tasks",
                        null
                )
        );
    }
    private String numbOfTasks(int size) {
        switch (size) {
            case 0:
                return "You don't have any tasks";
            case 1:
                return "You have one task";
            default:
                return "You have " + size + " tasks";
        }
    }
}