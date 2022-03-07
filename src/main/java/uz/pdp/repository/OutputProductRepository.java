package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.OutputProduct;
import uz.pdp.model.response.DailyOutputProductReport;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OutputProductRepository extends JpaRepository<OutputProduct,Long> {

    @Query(value = "select p.name, op.amount,op.price,(op.amount*op.price) as totalPrice, o.date from product p\n" +
            "join output_product op on p.id = op.product_id\n" +
            "join output o on op.output_id = o.id\n" +
            "where date(o.date) = :date order  by op.amount desc" ,nativeQuery = true)
    List<DailyOutputProductReport> getDailyOutputProducts(LocalDate date);
}
