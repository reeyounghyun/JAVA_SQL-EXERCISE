package skeleton.view;

import skeleton.model.Seat;
import skeleton.model.SeatStat;
import skeleton.model.Usage;

import java.time.format.DateTimeFormatter;
import java.util.List;

// 조회 결과와 안내 메시지 출력을 담당한다.
public class StudyCafeView {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MM-dd HH:mm");

    /*
     * 문제 VIEW-1. 분을 "N시간 M분" 글자로 바꾸기
     *
     * 전달받은 minutes를 시간과 분으로 나눠 문자열로 반환한다. (출력하지 않고 return)
     *   130 → "2시간 10분"
     *   120 → "2시간 0분"
     *    45 → "0시간 45분"
     * 힌트: 시간은 몫(/), 분은 나머지(%)
     * 이 메서드는 아래 출력 메서드들과 Controller에서 함께 사용한다.
     */
    public String formatMinutes(int minutes) {
        // TODO: 분을 "N시간 M분" 형식의 문자열로 바꿔 반환하세요.
        return "";
    }

    /*
     * 문제 VIEW-2. 좌석 목록 출력
     *
     * 좌석 하나를 한 줄로 출력한다.
     *   예) [7번] 창가석
     */
    public void displaySeats(List<Seat> seats) {
        // TODO: 좌석 번호와 좌석 종류를 한 줄씩 출력하세요.
    }

    /*
     * 문제 VIEW-3. 최근 이용 기록 출력
     *
     * 이용 기록 하나를 한 줄로 출력한다. 날짜는 DATE_TIME_FORMATTER를 사용한다.
     *   퇴실한 기록:   10-22 13:00 ~ 10-22 15:10 | 7번(창가석) | 2시간 10분
     *   이용 중인 기록: 10-23 14:00 ~ 이용 중      | 8번(창가석)
     * checkOut이 null이면 이용 중인 기록이다.
     * 이용 시간은 formatMinutes()를 사용한다.
     */
    public void displayUsages(List<Usage> usages) {
        // TODO: 이용 기록을 퇴실/이용 중에 맞게 한 줄씩 출력하세요.
    }

    /*
     * 문제 VIEW-4. 좌석 종류별 이용 통계 출력
     *
     * 통계 하나를 한 줄로 출력한다.
     *   예) 창가석: 2회 / 총 4시간 55분
     * 마지막 줄에는 모든 좌석 종류의 이용 시간을 더한 합계를 출력한다.
     *   예) 합계: 10시간 40분
     */
    public void displaySeatTypeStats(List<SeatStat> stats) {
        // TODO: 좌석 종류별 이용 횟수와 시간, 마지막 줄에 합계를 출력하세요.
    }

    /*
     * 문제 VIEW-5. 안내 메시지 출력
     *
     * 전달받은 message를 한 줄에 출력한다.
     */
    public void displayMessage(String message) {
        // TODO: 안내 메시지를 출력하세요.
    }
}
