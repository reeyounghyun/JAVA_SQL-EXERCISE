# 스터디카페 좌석 예약 (JDBC 연습 문제)

팀 프로젝트(OTT 구독 관리)와 같은 구조로 만든 연습 문제입니다.
문제 설명은 `src/main/java/skeleton/Question.java`에 있고, 각 메서드 위 주석이 문제입니다.

## 실행 준비
1. DataGrip(또는 MySQL Workbench)에서 `src/main/resources/db/schema.sql` 실행
2. 이어서 `src/main/resources/db/insert.sql` 실행
3. `src/main/resources/db.properties`에 본인 계정 정보 입력
4. `skeleton.run.Application` 실행

> schema.sql은 테이블을 지우고 다시 만듭니다. 데이터를 처음 상태로 돌리고 싶을 때 1~2번을 다시 실행하면 됩니다.

## 구조
```
skeleton
├─ Question.java          문제 전체 설명
├─ model                  Member, Seat, Usage, SeatStat (제공)
├─ common/JDBCTemplate    DB 연결 (제공)
├─ mapper                 member / seat / usage -query.xml  ← SQL 작성
├─ repository             Member / Seat / Usage Repository  ← REPOSITORY-1~14
├─ view/StudyCafeView                                       ← VIEW-1~5
├─ controller/StudyCafeController                           ← CONTROLLER-1~12
└─ run/Application        메뉴 실행 (제공)
```
