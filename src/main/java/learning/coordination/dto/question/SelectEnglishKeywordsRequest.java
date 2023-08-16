package learning.coordination.dto.question;

import learning.coordination.dto.question.SelectedEnglishKeywords;
import lombok.Getter;

@Getter
public class SelectEnglishKeywordsRequest {
    private Long id;
    private SelectedEnglishKeywords selectedEnglishKeywords;
}
