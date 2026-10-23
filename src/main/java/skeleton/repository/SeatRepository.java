package skeleton.repository;

import skeleton.model.Seat;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static skeleton.common.JDBCTemplate.getConnection;

// 좌석 정보 JDBC 처리 담당
public class SeatRepository {

    private static final String QUERY_FILE =
            "src/main/java/skeleton/mapper/seat-query.xml";

    private final Properties prop = new Properties();

    public SeatRepository() {
        try (FileInputStream inputStream = new FileInputStream(QUERY_FILE)) {
            prop.loadFromXML(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("좌석 SQL 파일을 읽지 못했습니다.", e);
        }
    }

    /*
     * 문제 REPOSITORY-6. 빈 좌석 목록 조회
     *
     * 아래 두 조건을 모두 만족하는 좌석을 seat_no 오름차순으로 조회한다.
     *   1) usable이 TRUE인 좌석 (점검 중인 좌석 제외)
     *   2) 지금 누군가 이용 중이 아닌 좌석
     *      = usage_tbl에서 check_out IS NULL인 행의 seat_no 목록에 들어 있지 않은 좌석
     * 힌트: WHERE 절 서브쿼리 + NOT IN
     * 각 행을 Seat 객체로 변환해 List에 담아 반환한다. 결과가 없으면 비어 있는 List를 반환한다.
     * 이 SQL에는 ? 가 없다. (PreparedStatement는 그대로 사용한다)
     * SQL 키: selectEmptySeats
     */
    public List<Seat> findEmptySeats() {
        // TODO: XML의 SQL을 사용해 빈 좌석 목록을 조회하세요.
        return new ArrayList<>();
    }

    /*
     * 문제 REPOSITORY-7. 좌석 번호로 좌석 조회
     *
     * seat_no가 일치하는 좌석을 Seat 객체로 반환한다. (점검 중인 좌석도 조회된다)
     * 조회 결과가 없으면 null을 반환한다.
     * SQL 키: selectSeatByNo
     */
    public Seat findBySeatNo(int seatNo) {
        // TODO: XML의 SQL을 사용해 좌석 하나를 조회하세요.
        return null;
    }

    /*
     * 문제 REPOSITORY-8. 좌석 사용 여부 확인
     *
     * usage_tbl에서 seat_no가 일치하고 check_out IS NULL인 행의 "개수"를 센다.
     * 힌트: SELECT COUNT(*) AS cnt ... → rset.getInt("cnt")
     *       COUNT(*)는 결과가 항상 한 줄이므로 rset.next()를 한 번 호출하면 된다.
     * 개수가 1 이상이면 누군가 사용 중이므로 true, 0이면 false를 반환한다.
     * SQL 키: countSeatInUse
     */
    public boolean isOccupied(int seatNo) {
        // TODO: XML의 SQL을 사용해 좌석이 사용 중인지 확인하세요.
        return false;
    }
}
