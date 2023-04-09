package com.example.tp2.repositories;

import com.example.tp2.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Patientrepository extends JpaRepository<Patient,Long> {
    public List<Patient>findByMalade(boolean m);
}
