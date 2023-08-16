package learning.coordination.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LearningData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "learningTarget", nullable = false)
    private String learningTarget;

    @Column(name = "material", nullable = false)
    private String material;

    @Builder
    public LearningData(String answer, String learningTarget, String material) {
        this.answer = answer;
        this.learningTarget = learningTarget;
        this.material = material;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public void setLearningTarget(String learningTarget) {
        this.learningTarget = learningTarget;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
}
