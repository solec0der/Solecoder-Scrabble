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
@Table(name = "game_fields")
@NoArgsConstructor(force = true)
public class GameField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "x_position")
    private int xPosition;

    @NotNull
    @Column(name = "y_position")
    private int yPosition;

    @ManyToOne
    private Game game;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "letter_id", referencedColumnName = "id")
    private Letter letter;

    @NotNull
    @Column(name = "field_type")
    @Enumerated(EnumType.STRING)
    private FieldType fieldType;
}
