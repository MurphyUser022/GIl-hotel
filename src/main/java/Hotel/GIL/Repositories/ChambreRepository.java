package Hotel.GIL.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Hotel.GIL.models.Chambre;

public interface ChambreRepository extends JpaRepository<Chambre, Integer> {
}
