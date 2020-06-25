package parkingTicket;

import parkingTicket.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    private TicketReservationRepository ticketReservationRepo;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverFullyOccupied_Unavailable(@Payload FullyOccupied fullyOccupied){
        // 일일 주차권 최대 가능 건수를 초과한 경우, parkingLot에서 발생시킨 Event
        // TicketReservation aggregate에서는 별도 biz logic 필요 없으며,
        // ReservationStatus(CQRS) view에서 TicketReservation 상태 확인 가능함
        if(fullyOccupied.isMe()){
            System.out.println("##### listener unavailable : " + fullyOccupied.toJson());

        }
    }
}
