package parkingTicket;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="TicketReservation_table")
public class TicketReservation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private Date reservationDate;

    @PostPersist
    public void onPostPersist(){
        TicketReserved ticketReserved = new TicketReserved();
        BeanUtils.copyProperties(this, ticketReserved);
        ticketReserved.publishAfterCommit();

        System.out.println("##### TicketReserved - onPostPersist : " + ticketReserved.toJson());
    }

    @PreRemove
    public void onPreRemove(){
        TicketReservationCanceled ticketReservationCanceled = new TicketReservationCanceled();
        BeanUtils.copyProperties(this, ticketReservationCanceled);
        ticketReservationCanceled.publishAfterCommit();

        System.out.println("##### TicketReservationCanceled - onPreRemove : " + ticketReservationCanceled.toJson());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
