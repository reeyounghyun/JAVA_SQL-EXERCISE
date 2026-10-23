package skeleton.model;

import lombok.*;

// 좌석 정보 (제공 코드 - 수정하지 않는다)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    private int seatNo;
    private String seatType;   // 일반석 / 창가석 / 1인실
    private boolean usable;    // false면 점검 중
}
