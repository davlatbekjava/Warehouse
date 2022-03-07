package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.InputProduct;
import uz.pdp.model.response.DailyInputProductReport;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InputProductRepository extends JpaRepository<InputProduct,Long> {

    @Query(value = "select p.name, ip.amount,ip.price,(ip.amount*ip.price) as totalPrice, i.date from product p\n" +
            "join input_product ip on p.id = ip.product_id\n" +
            "join input i on ip.input_id = i.id\n" +
            "where date(i.date) = :date" ,nativeQuery = true)
    List<DailyInputProductReport> getDailyInputProducts(LocalDate date);
}
