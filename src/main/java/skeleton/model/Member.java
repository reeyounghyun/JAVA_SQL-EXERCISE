package skeleton.model;

import lombok.*;

// 스터디카페 회원 정보 (제공 코드 - 수정하지 않는다)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private int memberId;
    private String name;
    private String phone;
    private int remainingMinutes;   // 남은 이용 시간(분)
}
