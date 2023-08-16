package learning.coordination.dto.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectedEnglishKeywords {
    private String difficulty;
    private String field;

    @Builder
    public SelectedEnglishKeywords(String difficulty, String field) {
        this.difficulty = difficulty;
        this.field = field;
    }
}
