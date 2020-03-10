package ch.solecoder.scrabble.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface ScrabbleBaseRepository<T, I extends Serializable> extends JpaRepository<T, I> {
}
