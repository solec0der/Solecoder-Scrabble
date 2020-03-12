package ch.solecoder.scrabble.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@Table(name = "translation_keys")
@NoArgsConstructor(force = true)
public class TranslationKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "key")
    private String key;

    @OneToMany(cascade = {CascadeType.ALL},targetEntity = Translation.class, mappedBy = "translationKey", fetch = FetchType.LAZY)
    private List<Translation> translations;

}
