package it.spootify.Spootify.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.Spootify.model.Playlist;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> , QueryByExampleExecutor<Playlist>{

}
