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
@Table(name = "translation_keys", schema = "scrabble")
@NoArgsConstructor(force = true)
public class TranslationKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "key")
    private String key;

    @OneToMany(cascade = {CascadeType.ALL},targetEntity = Translation.class, mappedBy = "translationKey", fetch = FetchType.LAZY)
    private List<Translation> translations;

}
