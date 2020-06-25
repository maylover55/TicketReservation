package parkingTicket;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TicketReservationRepository extends PagingAndSortingRepository<TicketReservation, Long>{

}