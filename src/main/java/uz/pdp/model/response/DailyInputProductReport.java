package uz.pdp.model.response;

import java.time.LocalDateTime;

public interface DailyInputProductReport {
    String getName();

    Long getAmount();

    Double getPrice();

    Double getTotalPrice();

    LocalDateTime getDate();
}
