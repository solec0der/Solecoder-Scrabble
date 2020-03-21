package ch.solecoder.scrabble.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@Table(name = "letters")
@NoArgsConstructor(force = true)
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "value", length = 1)
    private String value;

    @NotNull
    @Column(name = "score")
    private int score;

    @NotNull
    @Column(name = "joker")
    private boolean joker;
}
