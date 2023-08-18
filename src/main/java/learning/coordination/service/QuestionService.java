package learning.coordination.service;

import learning.coordination.domain.Question;
import learning.coordination.dto.question.EnglishKeywords;
import learning.coordination.dto.question.SelectedEnglishKeywords;
import learning.coordination.repository.QuestionRepository;
import learning.coordination.service.default_values.QuestionDefaultValues;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void initQuestion(Long id) {
        Question question = Question.builder()
                .template(QuestionDefaultValues.TEMPLATE)
                .difficulties(QuestionDefaultValues.DIFFICULTIES)
                .fields(QuestionDefaultValues.FIELDS)
                .topics(QuestionDefaultValues.TOPICS)
                .prompt(QuestionDefaultValues.EMPTY_PROMPT)
                .build();
        question.setId(id);
        questionRepository.save(question);
    }
    public void selectPromptById(Long id, SelectedEnglishKeywords selectedEnglishKeywords) {
        String template = findTemplateById(id);
        template = template.replace(QuestionDefaultValues.KEYWORD1, selectedEnglishKeywords.getDifficulty());
        template = template.replace(QuestionDefaultValues.KEYWORD2, selectedEnglishKeywords.getField());
        template = template.replace(QuestionDefaultValues.KEYWORD3, selectedEnglishKeywords.getTopic());
        updatePromptById(id, template);
    }
    public void updatePromptById(Long id, String modifiedPrompt) {
        Question question = findQuestion(id);
        question.setPrompt(modifiedPrompt);
        questionRepository.save(question);
    }
    public String findTemplateById(Long id) {
        Question question = findQuestion(id);
        return question.getTemplate();
    }
    public EnglishKeywords findEnglishKeywordsById(Long id) {
        Question question = findQuestion(id);
        return EnglishKeywords.builder()
                .difficulties(question.getDifficulties())
                .fields(question.getFields())
                .topics(question.getTopics())
                .build();
    }
    public String findPromptById(Long id) {
        Question question = findQuestion(id);
        return question.getPrompt();
    }
    private Question findQuestion(Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        Question question;
        if (!questionOptional.isPresent()) {
            initQuestion(id);
            questionOptional = questionRepository.findById(id);
        }
        question = questionOptional.get();
        return question;
    }
}
