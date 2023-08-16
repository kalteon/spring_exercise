package learning.coordination.service;

import learning.coordination.domain.LearningData;
import learning.coordination.dto.learning_data.CompletedLearningData;
import learning.coordination.repository.LearningDataRepository;
import learning.coordination.service.default_values.LearningDataDefaultValues;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LearningDataService {

    private final LearningDataRepository learningDataRepository;

    public void initLearningData(Long id) {
        LearningData learningData = LearningData.builder()
                .answer(LearningDataDefaultValues.EMPTY_ANSWER)
                .learningTarget(LearningDataDefaultValues.EMPTY_LEARNING_TARGET)
                .material(LearningDataDefaultValues.EMPTY_MATERIAL)
                .build();
        learningData.setId(id);
        learningDataRepository.save(learningData);
    }
    public String findAnswer(Long id) {
        LearningData learningData = findLearningData(id);
        String answer = learningData.getAnswer();
        return answer;
    }
    public void updateAnswer(Long id, String modifiedAnswer) {
        LearningData learningData = findLearningData(id);
        learningData.setAnswer(modifiedAnswer);
        learningDataRepository.save(learningData);
    }
    public void setLearningTarget(Long id, String learningTarget) {
        LearningData learningData = findLearningData(id);
        learningData.setLearningTarget(learningTarget);
        learningDataRepository.save(learningData);
    }
    public void setMaterial(Long id, String material) {
        LearningData learningData = findLearningData(id);
        learningData.setMaterial(material);
        learningDataRepository.save(learningData);
    }
    public CompletedLearningData getCompletedLearningData(Long id) {
        LearningData learningData = findLearningData(id);
        CompletedLearningData completedLearningData = CompletedLearningData.builder()
                .modifiedAnswer(learningData.getAnswer())
                .learningTarget(learningData.getLearningTarget())
                .material(learningData.getMaterial()).build();
        return completedLearningData;
    }

    private LearningData findLearningData(Long id) {
        Optional<LearningData> learningDataOptional = learningDataRepository.findById(id);
        LearningData learningData;
        if (!learningDataOptional.isPresent()) {
            initLearningData(id);
            learningDataOptional = learningDataRepository.findById(id);
        }
        learningData = learningDataOptional.get();
        return learningData;
    }
}
