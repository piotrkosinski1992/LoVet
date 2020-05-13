package com.project;

import com.project.auth.domain.BaseEntity;
import com.project.auth.domain.usecase.SaveBaseEntity;
import com.project.domain.petowner.PetOwnerSnapshot;
import com.project.gateway.petowner.PetOwnerSnapshotRepository;
import java.util.HashSet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final SaveBaseEntity saveBaseEntity;
    private final PetOwnerSnapshotRepository petOwnerSnapshotRepository;
    private final PasswordEncoder passwordEncoder;

    public Main(SaveBaseEntity saveBaseEntity,
        PetOwnerSnapshotRepository petOwnerSnapshotRepository,
        PasswordEncoder passwordEncoder) {
        this.saveBaseEntity = saveBaseEntity;
        this.petOwnerSnapshotRepository = petOwnerSnapshotRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args)  {
//        saveBaseEntity.save(new BaseEntity("test@gmail.com", passwordEncoder.encode("test")));
//        petOwnerSnapshotRepository.save(new PetOwnerSnapshot("test@gmail.com", "Piotr", "Kosinski",
//            "555444333", new HashSet<>()));
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        applicationContext.getBean(RequestMappingHandlerMapping.class)
            .getHandlerMethods().forEach((key, value) -> System.out.println(key + " " + value));
    }
}
