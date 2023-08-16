package learning.coordination.controller;

import learning.coordination.dto.learning_information.CompletedLearningInformation;
import learning.coordination.dto.learning_information.SetMisUnderstandingThingsRequest;
import learning.coordination.dto.learning_information.SetUnderstandingThingsRequest;
import learning.coordination.service.LearningInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LearningInformationController {

    private final LearningInformationService learningInformationService;

    @PostMapping("/api/understanding-things")
    public void setUnderstandingThings(@RequestBody SetUnderstandingThingsRequest setUnderstandingThingsRequest) {
        learningInformationService.setUnderstandingThings(
                setUnderstandingThingsRequest.getId(),
                setUnderstandingThingsRequest.getUnderstandingThings());
    }

    @PostMapping("/api/misunderstanding-things")
    public void setMisUnderstandingThings(@RequestBody SetMisUnderstandingThingsRequest setMisUnderstandingThingsRequest) {
        learningInformationService.setMisUnderstandingThings(
                setMisUnderstandingThingsRequest.getId(),
                setMisUnderstandingThingsRequest.getMisUnderstandingThings());
    }

    @GetMapping("/api/completed-learning-information/{id}")
    public CompletedLearningInformation getCompletedLearningInformation(@PathVariable Long id) {
        CompletedLearningInformation completedLearningInformation = learningInformationService.getCompletedLearningInformation(id);
        return completedLearningInformation;
    }
}
