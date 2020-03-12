package ch.solecoder.scrabble.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@Table(name = "languages")
@NoArgsConstructor(force = true)
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "display_value")
    private String displayValue;

    @NonNull
    @Column(name = "lang_code")
    private String langCode;

    @NonNull
    @Column(name = "ui_lang_code")
    private String uiLangCode;

    @OneToMany(cascade = {CascadeType.ALL}, targetEntity = Translation.class, mappedBy = "language", fetch = FetchType.LAZY)
    private List<Translation> translations;
}
