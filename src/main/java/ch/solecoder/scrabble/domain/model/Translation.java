package ch.solecoder.scrabble.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@Table(name = "translations")
@NoArgsConstructor(force = true)
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private TranslationKey translationKey;

    @ManyToOne
    private Language language;

    @NotNull
    @Column(name = "value")
    private String value;
}
