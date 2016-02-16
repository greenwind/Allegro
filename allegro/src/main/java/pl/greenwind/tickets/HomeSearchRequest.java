package pl.greenwind.tickets;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class HomeSearchRequest {

    private String from;
    private String fromCode;
    private String to;
    private String toCode;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int days;

}
