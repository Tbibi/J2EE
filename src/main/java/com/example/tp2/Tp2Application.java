package com.example.tp2;

import com.example.tp2.entities.Patient;
import com.example.tp2.repositories.Patientrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Tp2Application  implements CommandLineRunner {
    @Autowired
    private Patientrepository patientrepository;
    public static void main(String[] args) {
        SpringApplication.run(Tp2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i=0;i<10;i++){
            patientrepository.save(new Patient(null,"Ahmed",new Date(),false,999));
            patientrepository.save(new Patient(null,"Moha",new Date(),false,210));
            patientrepository.save(new Patient(null,"Ghita",new Date(),true,90));
        }
        Page<Patient> patients = patientrepository.findAll(PageRequest.of(3,3));
        System.out.println("Total pages: "+patients.getTotalPages());
        System.out.println("Total elements: "+patients.getTotalElements());
        System.out.println("num page:"+patients.getNumber());
        List<Patient> content = patients.getContent();
        content.forEach(p->{
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        });
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        Patient patient=patientrepository.findById(3L).orElse(null);
        if(patient!=null){
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }
        patient.setScore(870);
        patientrepository.save(patient);
        patientrepository.deleteById(2L);
    }
}
