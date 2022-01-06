package spotify.core.artist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Integer> {

}
