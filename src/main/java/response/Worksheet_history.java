package response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Worksheet_history {

    private int id;
    private int number;
    private int score;
    private String date;
    private Topic topic;
    private int xp;
    private int gems;
}
