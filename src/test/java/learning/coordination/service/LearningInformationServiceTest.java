package learning.coordination.service;

import learning.coordination.dto.learning_information.CompletedLearningInformation;
import learning.coordination.service.default_values.LearningInformationDefaultValues;
import learning.coordination.service.test_values.LearningInformationTestValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LearningInformationServiceTest {

    @Autowired
    private LearningInformationService learningInformationService;

    @BeforeEach
    public void initQuestion() {
        // given
        learningInformationService.initLearningInformation(LearningInformationTestValues.TEST_ID);
    }

    @Test
    public void testSetUnderstandingThings() {
        // when
        learningInformationService.setUnderstandingThings(LearningInformationTestValues.TEST_ID, LearningInformationTestValues.TEST_UNDERSTANDING_THINGS);
        CompletedLearningInformation result = learningInformationService.getCompletedLearningInformation(LearningInformationTestValues.TEST_ID);
        String resultUnderstandingThings = result.getUnderstandingThings();
        // then
        assertThat(resultUnderstandingThings).isEqualTo(LearningInformationTestValues.TEST_UNDERSTANDING_THINGS);
    }

    @Test
    public void testSetMisUnderstandingThings() {
        // when
        learningInformationService.setMisUnderstandingThings(LearningInformationTestValues.TEST_ID,LearningInformationTestValues.TEST_MISUNDERSTANDING_THINGS);
        CompletedLearningInformation result = learningInformationService.getCompletedLearningInformation(LearningInformationTestValues.TEST_ID);
        String resultMisUnderstandingThings = result.getMisUnderstandingThings();
        //
        assertThat(resultMisUnderstandingThings).isEqualTo(LearningInformationTestValues.TEST_MISUNDERSTANDING_THINGS);
    }

    @Test
    public void testGetCompletedLearningInformation() {
        // when
        CompletedLearningInformation expected = CompletedLearningInformation.builder()
                .understandingThings(LearningInformationDefaultValues.EMPTY_UNDERSTANDING_THINGS)
                .misUnderstandingThings(LearningInformationDefaultValues.EMPTY_MISUNDERSTANDING_THINGS)
                .build();
        CompletedLearningInformation result = learningInformationService.getCompletedLearningInformation(LearningInformationTestValues.TEST_ID);
        // then
        assertThat(result.getUnderstandingThings()).isEqualTo(expected.getUnderstandingThings());
        assertThat(result.getMisUnderstandingThings()).isEqualTo(expected.getMisUnderstandingThings());
    }
}