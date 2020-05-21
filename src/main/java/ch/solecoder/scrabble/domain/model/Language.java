package ch.solecoder.scrabble.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@Table(name = "languages", schema = "scrabble")
@NoArgsConstructor(force = true)
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "display_value")
    private String displayValue;

    @NotNull
    @Column(name = "lang_code")
    private String langCode;

    @NotNull
    @Column(name = "ui_lang_code")
    private String uiLangCode;

    @OneToMany(cascade = {CascadeType.ALL}, targetEntity = Translation.class, mappedBy = "language", fetch = FetchType.LAZY)
    private List<Translation> translations;
}
