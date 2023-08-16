package learning.coordination.service;

import learning.coordination.domain.LearningInformation;
import learning.coordination.dto.learning_information.CompletedLearningInformation;
import learning.coordination.repository.LearningInformationRepository;
import learning.coordination.service.default_values.LearningInformationDefaultValues;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LearningInformationService {

    private final LearningInformationRepository learningInformationRepository;

    public void initLearningInformation(Long id) {
        LearningInformation learningInformation = LearningInformation.builder()
                .understandingThings(LearningInformationDefaultValues.EMPTY_UNDERSTANDING_THINGS)
                .misUnderstandingThings(LearningInformationDefaultValues.EMPTY_MISUNDERSTANDING_THINGS)
                .build();
        learningInformation.setId(id);
        learningInformationRepository.save(learningInformation);
    }
    public void setUnderstandingThings(Long id, String understandingThings) {
        LearningInformation learningInformation = findLearningInformation(id);
        learningInformation.setUnderstandingThings(understandingThings);
        learningInformationRepository.save(learningInformation);
    }
    public void setMisUnderstandingThings(Long id, String  misUnderstandingThings) {
        LearningInformation learningInformation = findLearningInformation(id);
        learningInformation.setMisUnderstandingThings(misUnderstandingThings);
        learningInformationRepository.save(learningInformation);
    }
    public CompletedLearningInformation getCompletedLearningInformation(Long id) {
        LearningInformation learningInformation = findLearningInformation(id);
        CompletedLearningInformation completedLearningInformation = CompletedLearningInformation.builder()
                .understandingThings(learningInformation.getUnderstandingThings())
                .misUnderstandingThings(learningInformation.getMisUnderstandingThings())
                .build();
        return completedLearningInformation;
    }

    private LearningInformation findLearningInformation(Long id) {
        Optional<LearningInformation> learningInformationOptional = learningInformationRepository.findById(id);
        LearningInformation learningInformation;
        if (!learningInformationOptional.isPresent()) {
            initLearningInformation(id);
            learningInformationOptional = learningInformationRepository.findById(id);
        }
        learningInformation = learningInformationOptional.get();
        return learningInformation;
    }
}
