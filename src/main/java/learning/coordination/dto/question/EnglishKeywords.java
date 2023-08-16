package learning.coordination.dto.question;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
public class EnglishKeywords {
    private List<String> difficulties;
    private List<String> fields;

    @Builder
    public EnglishKeywords(List<String> difficulties, List<String> fields) {
        this.difficulties = difficulties;
        this.fields = fields;
    }
}
