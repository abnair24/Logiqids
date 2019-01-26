package response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HistoryResponse {

    public int total_pages;
    public List<Worksheet_history> worksheet_history;
}
