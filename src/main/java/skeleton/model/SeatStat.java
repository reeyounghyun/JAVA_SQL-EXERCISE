package skeleton.model;

import lombok.*;

// 좌석 종류별 이용 통계 (GROUP BY 결과 한 줄을 담는다, 제공 코드 - 수정하지 않는다)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SeatStat {

    private String seatType;    // 좌석 종류
    private int useCount;       // 이용 횟수
    private int totalMinutes;   // 총 이용 시간(분)
}
