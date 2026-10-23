package skeleton;

public class Question {

    /*
     * Q1. JDBC 기반 스터디카페 좌석 예약 프로그램 완성하기
     *
     * 휴대폰 번호로 로그인한 회원이 이용권(시간)을 충전하고,
     * 빈 좌석을 골라 입실하고, 퇴실하면 이용한 시간만큼 남은 시간이 줄어드는 프로그램이다.
     * 최근 이용 기록과 좌석 종류별 이용 통계도 볼 수 있다.
     * model, common, Application은 완성된 코드로 제공한다.
     *
     * [테이블]
     * - member_tbl : 회원 (remaining_minutes = 남은 이용 시간, 분 단위)
     * - seat_tbl   : 좌석 12개 (일반석 / 창가석 / 1인실, usable = FALSE면 점검 중)
     * - usage_tbl  : 이용 기록 (check_out이 NULL이면 아직 이용 중)
     *
     * [실행 전 준비]
     * 1. resources/db/schema.sql 실행 → 2. resources/db/insert.sql 실행
     * 3. resources/db.properties에 본인 MySQL 계정 정보 입력
     *
     * [구현 순서] (위에서부터 차근차근)
     * 1. mapper/*.xml               : TODO로 비어 있는 SQL 14개 작성
     * 2. repository (14문제)        : REPOSITORY-1 ~ 14
     *    - MemberRepository  : 회원가입, 조회, 충전(더하기 UPDATE), 남은 시간 변경(값 UPDATE), 탈퇴
     *    - SeatRepository    : 빈 좌석(서브쿼리), 좌석 조회, 사용 여부(COUNT)
     *    - UsageRepository   : 입실, 이용 중 조회, 퇴실(TIMESTAMPDIFF), ID 조회,
     *                          최근 기록(JOIN + LIMIT), 좌석 종류별 통계(JOIN + GROUP BY)
     * 3. view/StudyCafeView (5문제)  : VIEW-1 ~ 5
     * 4. controller (12문제)         : CONTROLLER-1 ~ 12
     *
     * [테스트 데이터로 확인해 볼 것]
     * - 김철수 010-1111-1111 : 남은 시간 5시간, 지난 기록 5건 → 6번, 7번 메뉴 확인용
     * - 이영희 010-2222-2222 : 남은 시간 0분 → 충전 전에는 입실이 막혀야 한다
     * - 박민수 010-3333-3333 : 8번 좌석 이용 중 → 다른 좌석 입실, 탈퇴가 막혀야 한다
     * - 최지우 010-4444-4444 : 1시간 남았는데 150분째 이용 중 → 퇴실하면 남은 시간 0분 + 초과 안내
     * - 6번 좌석은 점검 중, 8번·10번 좌석은 사용 중 → 빈 좌석 목록에 나오면 안 된다
     *
     * [제약 사항]
     * - 메서드명, 매개변수, 반환형은 변경하지 않는다.
     * - SQL은 Java 문자열로 쓰지 않고 XML 파일에 작성한다. (entry의 key 이름 유지)
     * - SQL은 PreparedStatement로 실행하고, ? 자리는 setXxx()로 채운다.
     * - JDBC 자원은 try-with-resources로 닫거나, 수업 때처럼 finally에서 close()로 닫는다.
     * - 날짜는 rset.getTimestamp("컬럼").toLocalDateTime()으로 변환한다. (NULL 가능 컬럼 주의)
     * - INSERT, UPDATE, DELETE의 성공 여부는 executeUpdate() 반환값으로 판단한다.
     * - SQLException은 무시하지 말고 원인을 담은 IllegalStateException으로 바꿔 던진다.
     *     throw new IllegalStateException("회원 저장에 실패했습니다.", e);
     * - TODO에 있는 임시 반환문(return false; 등)은 실제 구현으로 교체한다.
     *
     * [새로 나오는 것]
     * - TIMESTAMPDIFF(MINUTE, 시작, 끝) : 두 시각 차이를 분으로 계산 (REPOSITORY-11 설명 참고)
     * - NULL일 수 있는 DATETIME 꺼내기     : UsageRepository 클래스 위 주의 사항 참고
     */
}
