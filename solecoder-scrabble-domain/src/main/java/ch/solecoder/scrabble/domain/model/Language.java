package ch.solecoder.scrabble.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@Table(name = "languages")
@NoArgsConstructor(force = true)
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "language")
    private String language;

    @NonNull
    @Column(name = "lang_code")
    private String langCode;

    @NonNull
    @Column(name = "ui_lang_code")
    private String uiLangCode;

}
