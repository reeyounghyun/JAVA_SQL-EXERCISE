package skeleton.repository;

import skeleton.model.Member;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static skeleton.common.JDBCTemplate.getConnection;

// 회원 정보 JDBC 처리 담당
public class MemberRepository {

    private static final String QUERY_FILE =
            "src/main/java/skeleton/mapper/member-query.xml";

    private final Properties prop = new Properties();

    public MemberRepository() {
        try (FileInputStream inputStream = new FileInputStream(QUERY_FILE)) {
            prop.loadFromXML(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("회원 SQL 파일을 읽지 못했습니다.", e);
        }
    }

    /*
     * 문제 REPOSITORY-1. 회원가입
     *
     * Member의 name, phone을 member_tbl에 INSERT한다.
     * member_id(AUTO_INCREMENT)와 remaining_minutes(기본값 0)는 INSERT 컬럼에서 제외한다.
     * executeUpdate() 결과가 1이면 true를 반환한다.
     * SQL 키: insertMember
     */
    public boolean save(Member member) {
        // TODO: XML의 SQL을 사용해 신규 회원을 저장하세요.
        return false;
    }

    /*
     * 문제 REPOSITORY-2. 휴대폰 번호로 회원 조회
     *
     * phone이 일치하는 회원 한 명을 조회해 Member 객체로 반환한다.
     * 4개 컬럼(member_id, name, phone, remaining_minutes)을 모두 담는다.
     * 조회 결과가 없으면 null을 반환한다.
     * SQL 키: selectMemberByPhone
     */
    public Member findByPhone(String phone) {
        // TODO: XML의 SQL을 사용해 휴대폰 번호가 일치하는 회원을 조회하세요.
        return null;
    }

    /*
     * 문제 REPOSITORY-3. 이용권 충전 (남은 시간에 더하기)
     *
     * member_id가 일치하는 회원의 remaining_minutes에 minutes를 "더한다".
     * 힌트: SET 컬럼 = 컬럼 + ?  (기존 값에 이어서 더하는 UPDATE)
     * executeUpdate() 결과가 1이면 true를 반환한다.
     * SQL 키: updateChargeMinutes
     */
    public boolean chargeMinutes(int memberId, int minutes) {
        // TODO: XML의 SQL을 사용해 남은 시간에 충전 시간을 더하세요.
        return false;
    }

    /*
     * 문제 REPOSITORY-4. 남은 시간 변경 (값으로 바꾸기)
     *
     * member_id가 일치하는 회원의 remaining_minutes를 전달받은 remainingMinutes "값으로 바꾼다".
     * REPOSITORY-3(더하기)과 SET 부분이 어떻게 다른지 비교해 보자.
     * executeUpdate() 결과가 1이면 true를 반환한다.
     * SQL 키: updateRemainingMinutes
     */
    public boolean updateRemainingMinutes(int memberId, int remainingMinutes) {
        // TODO: XML의 SQL을 사용해 남은 시간을 새 값으로 변경하세요.
        return false;
    }

    /*
     * 문제 REPOSITORY-5. 회원 탈퇴
     *
     * member_id가 일치하는 회원을 DELETE한다.
     * 이용 기록(usage_tbl)은 ON DELETE CASCADE로 함께 삭제된다.
     * executeUpdate() 결과가 1이면 true를 반환한다.
     * SQL 키: deleteMemberById
     */
    public boolean deleteById(int memberId) {
        // TODO: XML의 SQL을 사용해 회원을 삭제하세요.
        return false;
    }
}
