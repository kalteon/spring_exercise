package learning.coordination.service;

import learning.coordination.dto.question.EnglishKeywords;
import learning.coordination.dto.question.SelectedEnglishKeywords;
import learning.coordination.service.default_values.QuestionDefaultValues;
import learning.coordination.service.test_values.QuestionTestValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @BeforeEach
    public void initQuestion() {
        // given
        questionService.initQuestion(QuestionTestValues.TEST_ID);
    }

    @Test
    public void testFindTemplateById() {
        // when
        String resultTemplate = questionService.findTemplateById(QuestionTestValues.TEST_ID);
        // then
        assertThat(resultTemplate).isEqualTo(QuestionDefaultValues.QUESTION_TEMPLATE);
    }

    @Test
    public void testFindEnglishKeywordsById() {
        // when
        EnglishKeywords resultEnglishKeywords = questionService.findEnglishKeywordsById(QuestionTestValues.TEST_ID);
        // then
        assertThat(resultEnglishKeywords.getDifficulties())
                .containsExactlyElementsOf(QuestionDefaultValues.QUESTION_DIFFICULTIES);
        assertThat(resultEnglishKeywords.getFields())
                .containsExactlyElementsOf(QuestionDefaultValues.QUESTION_FIELDS);
    }

    @Test
    public void testFindPromptById() {
        // when
        String resultPrompt = questionService.findPromptById(QuestionTestValues.TEST_ID);
        // then
        assertThat(resultPrompt).isEqualTo(QuestionDefaultValues.EMPTY_PROMPT);
    }
    @Test
    public void testUpdatePromptById() {
        // when
        questionService.updatePromptById(QuestionTestValues.TEST_ID, QuestionTestValues.TEST_PROMPT);
        String resultPrompt = questionService.findPromptById(QuestionTestValues.TEST_ID);
        // then
        assertThat(resultPrompt).isEqualTo(QuestionTestValues.TEST_PROMPT);
    }

    @Test
    public void testSelectPromptById() {
        // given
        SelectedEnglishKeywords selectedEnglishKeywords =
                SelectedEnglishKeywords.builder()
                .difficulty(QuestionDefaultValues.QUESTION_DIFFICULTIES.get(0))
                .field(QuestionDefaultValues.QUESTION_FIELDS.get(0))
                .build();
        // when
        questionService.selectPromptById(QuestionTestValues.TEST_ID, selectedEnglishKeywords);
        String expectedPrompt = QuestionDefaultValues.QUESTION_TEMPLATE;
        expectedPrompt = expectedPrompt.replace(QuestionDefaultValues.KEYWORD1, QuestionDefaultValues.QUESTION_DIFFICULTIES.get(0));
        expectedPrompt = expectedPrompt.replace(QuestionDefaultValues.KEYWORD2, QuestionDefaultValues.QUESTION_FIELDS.get(0));
        // then
        assertThat(questionService.findPromptById(QuestionTestValues.TEST_ID)).isEqualTo(expectedPrompt);
    }
}