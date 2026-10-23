package skeleton.model;

import lombok.*;

import java.time.LocalDateTime;

// 좌석 이용 기록 (제공 코드 - 수정하지 않는다)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Usage {

    private int usageId;
    private int memberId;
    private int seatNo;
    private String seatType;         // seat_tbl과 JOIN해서 채운다 (JOIN하지 않는 조회에서는 null)
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;  // 아직 이용 중이면 null
    private int usedMinutes;         // 아직 이용 중이면 0
}
