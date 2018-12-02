package com.vitali.cloud.jlong.simplewebservice;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SpringBootApplication
public class SimpleWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleWebServiceApplication.class, args);
    }
}

@Entity
@Data
class Cat {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    Cat() {
    }

    Cat(String name) {
        this.name = name;
    }
}

@RepositoryRestResource
interface CatRepository extends JpaRepository<Cat, Long> {
}
