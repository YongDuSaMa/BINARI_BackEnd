package ydsm.binari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ydsm.binari.model.HospitalData;

public interface HospitalDataRepository extends JpaRepository<HospitalData, Integer> {
    HospitalData findByHospitalAndDoctor(String hospital, String Doctor);
}
