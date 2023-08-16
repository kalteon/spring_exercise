package learning.coordination.controller;

import learning.coordination.dto.learning_data.CompletedLearningData;
import learning.coordination.dto.learning_data.SetLearningTargetRequest;
import learning.coordination.dto.learning_data.SetMaterialRequest;
import learning.coordination.dto.learning_data.UpdateAnswerRequest;
import learning.coordination.service.LearningDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LearningDataController {

    private final LearningDataService learningDataService;

    @GetMapping("/api/answer/{id}")
    public String findAnswer(@PathVariable Long id) {
        String answer = learningDataService.findAnswer(id);
        return answer;
    }

    @PutMapping("api/answer")
    public void updateAnswer(@RequestBody UpdateAnswerRequest updateAnswerRequest) {
        learningDataService.updateAnswer(
                updateAnswerRequest.getId(),
                updateAnswerRequest.getModifiedAnswer());
    }

    @PostMapping("api/learning-target")
    public void setLearningTarget(@RequestBody SetLearningTargetRequest setLearningTargetRequest) {
        learningDataService.setLearningTarget(
                setLearningTargetRequest.getId(),
                setLearningTargetRequest.getLearningTarget());
    }

    @PostMapping("api/material")
    public void setMaterial(@RequestBody SetMaterialRequest setMaterialRequest) {
        learningDataService.setMaterial(
                setMaterialRequest.getId(),
                setMaterialRequest.getMaterial());
    }

    @GetMapping("api/completed-learning-data/{id}")
    public CompletedLearningData getCompletedLearningData(@PathVariable Long id) {
        CompletedLearningData completedLearningData =  learningDataService.getCompletedLearningData(id);
        return completedLearningData;
    }

}
