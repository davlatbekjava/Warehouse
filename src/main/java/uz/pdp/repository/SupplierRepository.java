package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Supplier;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {

    @Query("select s from Supplier s where s.phoneNumber = ?1")
    Optional<Supplier> findByPhoneNumber(String phoneNumber);

    Optional<Supplier> findByName(String name);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

}
