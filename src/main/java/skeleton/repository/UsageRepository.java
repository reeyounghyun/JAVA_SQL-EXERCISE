package skeleton.repository;

import skeleton.model.SeatStat;
import skeleton.model.Usage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static skeleton.common.JDBCTemplate.getConnection;

/*
 * 좌석 이용 기록 JDBC 처리 담당
 *
 * [주의] check_out 컬럼은 아직 이용 중이면 NULL이다.
 * rset.getTimestamp("check_out")가 null을 돌려줄 수 있으므로
 * 바로 .toLocalDateTime()을 호출하면 NullPointerException이 난다.
 * null인지 먼저 확인하고, null이 아닐 때만 변환해서 setCheckOut()에 넣는다.
 * (used_minutes가 NULL이면 rset.getInt()는 0을 돌려주므로 따로 처리하지 않아도 된다.)
 */
public class UsageRepository {

    private static final String QUERY_FILE =
            "src/main/java/skeleton/mapper/usage-query.xml";

    private final Properties prop = new Properties();

    public UsageRepository() {
        try (FileInputStream inputStream = new FileInputStream(QUERY_FILE)) {
            prop.loadFromXML(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("이용 기록 SQL 파일을 읽지 못했습니다.", e);
        }
    }

    /*
     * 문제 REPOSITORY-9. 입실 (이용 기록 추가)
     *
     * member_id, seat_no만 usage_tbl에 INSERT한다.
     * check_in은 기본값(현재 시각), check_out과 used_minutes는 NULL로 두므로 INSERT 컬럼에서 제외한다.
     * executeUpdate() 결과가 1이면 true를 반환한다.
     * SQL 키: insertUsage
     */
    public boolean checkIn(int memberId, int seatNo) {
        // TODO: XML의 SQL을 사용해 입실 기록을 저장하세요.
        return false;
    }

    /*
     * 문제 REPOSITORY-10. 현재 이용 중인 기록 조회
     *
     * member_id가 일치하고 check_out IS NULL인 이용 기록 한 건을 조회한다.
     * usage_id, member_id, seat_no, check_in을 Usage 객체에 담아 반환한다.
     * (이 조회는 JOIN하지 않으므로 seatType은 비워 둔다)
     * 조회 결과가 없으면(= 입실 중이 아니면) null을 반환한다.
     * SQL 키: selectCurrentUsage
     */
    public Usage findCurrentUsage(int memberId) {
        // TODO: XML의 SQL을 사용해 지금 이용 중인 기록을 조회하세요.
        return null;
    }

    /*
     * 문제 REPOSITORY-11. 퇴실
     *
     * usage_id와 member_id가 모두 일치하고 check_out IS NULL인 행만 UPDATE한다.
     *   - check_out    = 현재 시각 (NOW())
     *   - used_minutes = 입실부터 지금까지 지난 "분"
     *
     * [새 함수] TIMESTAMPDIFF(단위, 시작시각, 끝시각)
     *   두 시각의 차이를 원하는 단위의 정수로 돌려준다. (끝시각 - 시작시각)
     *   예) SELECT TIMESTAMPDIFF(MINUTE, '2026-10-23 13:00:00', '2026-10-23 15:30:00');  → 150
     *   used_minutes는 TIMESTAMPDIFF(MINUTE, check_in, NOW())로 계산한다.
     *
     * executeUpdate() 결과가 1이면 true를 반환한다.
     * SQL 키: updateCheckOut
     */
    public boolean checkOut(int usageId, int memberId) {
        // TODO: XML의 SQL을 사용해 퇴실 시각과 이용 시간을 기록하세요.
        return false;
    }

    /*
     * 문제 REPOSITORY-12. 이용 기록 ID로 조회
     *
     * usage_id가 일치하는 이용 기록을 조회한다.
     * usage_id, member_id, seat_no, check_in, check_out, used_minutes를 Usage에 담아 반환한다.
     * check_out은 NULL일 수 있다. (클래스 위의 주의 사항 참고)
     * 조회 결과가 없으면 null을 반환한다.
     * SQL 키: selectUsageById
     */
    public Usage findUsageById(int usageId) {
        // TODO: XML의 SQL을 사용해 이용 기록 한 건을 조회하세요.
        return null;
    }

    /*
     * 문제 REPOSITORY-13. 최근 이용 기록 10건 조회 (JOIN + LIMIT)
     *
     * 로그인 회원의 이용 기록을 seat_tbl과 JOIN해서 좌석 종류(seat_type)까지 조회한다.
     * 입실 시각(check_in) 내림차순(최근 것이 위)으로 정렬하고 최대 10건만 조회한다.
     * 지금 이용 중인 기록(check_out IS NULL)도 포함한다.
     * Usage의 모든 필드(usageId, memberId, seatNo, seatType, checkIn, checkOut, usedMinutes)를 채운다.
     * 결과가 없으면 비어 있는 List를 반환한다.
     * 힌트: 두 테이블 모두 seat_no 컬럼이 있으므로 별칭(a.seat_no)을 붙여야 헷갈리지 않는다.
     * SQL 키: selectRecentUsages
     */
    public List<Usage> findRecentUsages(int memberId) {
        // TODO: XML의 SQL을 사용해 최근 이용 기록을 좌석 종류와 함께 조회하세요.
        return new ArrayList<>();
    }

    /*
     * 문제 REPOSITORY-14. 좌석 종류별 이용 통계 (JOIN + GROUP BY)
     *
     * 로그인 회원의 "퇴실까지 끝난" 이용 기록(check_out IS NOT NULL)만 대상으로
     * 좌석 종류(seat_type)별로 묶어서 아래 값을 구한다.
     *   - 이용 횟수:     COUNT(*)           AS use_count
     *   - 총 이용 시간: SUM(used_minutes)  AS total_minutes
     * 총 이용 시간 내림차순으로 정렬한다.
     * 각 행을 SeatStat 객체로 변환해 List로 반환한다. 결과가 없으면 비어 있는 List를 반환한다.
     * 힌트: 집계 결과는 AS로 붙인 별칭 이름으로 꺼낸다. → rset.getInt("use_count")
     * SQL 키: selectSeatTypeStats
     */
    public List<SeatStat> findSeatTypeStats(int memberId) {
        // TODO: XML의 SQL을 사용해 좌석 종류별 이용 횟수와 총 이용 시간을 조회하세요.
        return new ArrayList<>();
    }
}
