package uz.pdp.model.response;

import java.time.LocalDateTime;

public interface DailyOutputProductReport {

    String getName();

    Long getAmount();

    Double getPrice();

    Double getTotalPrice();

    LocalDateTime getDate();
}
