package learning.coordination.domain;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "question")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "template", nullable = false)
    private String template;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "difficulties", joinColumns = @JoinColumn(name = "difficulty_id"))
    private List<String> difficulties;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "fields", joinColumns = @JoinColumn(name = "field_id"))
    private List<String> fields;

    @Column(name = "prompt", nullable = false)
    private String prompt;

    @Builder
    public Question(String template, List<String> difficulties, List<String> fields, String prompt) {
        this.template = template;
        this.difficulties = difficulties;
        this.fields = fields;
        this.prompt = prompt;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
