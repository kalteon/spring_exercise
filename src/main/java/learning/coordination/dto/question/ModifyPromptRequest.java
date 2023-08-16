package learning.coordination.dto.question;

import lombok.Getter;

@Getter
public class ModifyPromptRequest {
    private Long id;
    private String modifiedPrompt;
}
