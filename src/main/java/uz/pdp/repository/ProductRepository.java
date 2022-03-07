package uz.pdp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Category;
import uz.pdp.entity.Product;
import uz.pdp.model.response.DailyInputProductReport;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCategory(Category category, Pageable pageable);

    Optional<Product> findByName(String name);


}
