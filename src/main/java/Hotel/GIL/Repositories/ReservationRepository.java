package Hotel.GIL.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Hotel.GIL.models.*;
public interface ReservationRepository  extends JpaRepository<Reservation, Integer> {
}
