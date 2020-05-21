package ch.solecoder.scrabble.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@Table(name = "games", schema = "scrabble")
@NoArgsConstructor(force = true)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Game.Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;

    @OneToMany(cascade = {CascadeType.ALL}, targetEntity = GameField.class, mappedBy = "game", fetch = FetchType.LAZY)
    private List<GameField> gameFields = new ArrayList<>();

    public enum Status {
        NOT_STARTED,
        RUNNING,
        FINISHED
    }
}
