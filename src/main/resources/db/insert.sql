-- 테스트용 데이터 (schema.sql 실행 후 실행한다)
SET NAMES utf8mb4;
USE studycafedb;

-- 좌석 12개 (6번은 점검 중)
INSERT INTO seat_tbl (seat_no, seat_type, usable)
VALUES (1, '일반석', TRUE),
       (2, '일반석', TRUE),
       (3, '일반석', TRUE),
       (4, '일반석', TRUE),
       (5, '일반석', TRUE),
       (6, '일반석', FALSE),
       (7, '창가석', TRUE),
       (8, '창가석', TRUE),
       (9, '창가석', TRUE),
       (10, '1인실', TRUE),
       (11, '1인실', TRUE),
       (12, '1인실', TRUE);

-- 회원
INSERT INTO member_tbl (name, phone, remaining_minutes)
VALUES ('김철수', '010-1111-1111', 300), -- 1번: 이용 기록 많음, 지금은 퇴실 상태
       ('이영희', '010-2222-2222', 0),   -- 2번: 남은 시간 없음 (충전 연습용)
       ('박민수', '010-3333-3333', 480), -- 3번: 8번 좌석에 90분 전 입실
       ('최지우', '010-4444-4444', 60);  -- 4번: 10번 좌석에 150분 전 입실 (남은 시간보다 오래 이용)

-- 김철수의 지난 이용 기록 (모두 퇴실 완료)
INSERT INTO usage_tbl (member_id, seat_no, check_in, check_out, used_minutes)
VALUES (1, 2, NOW() - INTERVAL 5 DAY, NOW() - INTERVAL 5 DAY + INTERVAL 120 MINUTE, 120),
       (1, 7, NOW() - INTERVAL 4 DAY, NOW() - INTERVAL 4 DAY + INTERVAL 200 MINUTE, 200),
       (1, 7, NOW() - INTERVAL 3 DAY, NOW() - INTERVAL 3 DAY + INTERVAL 95 MINUTE, 95),
       (1, 11, NOW() - INTERVAL 2 DAY, NOW() - INTERVAL 2 DAY + INTERVAL 180 MINUTE, 180),
       (1, 3, NOW() - INTERVAL 1 DAY, NOW() - INTERVAL 1 DAY + INTERVAL 45 MINUTE, 45);

-- 박민수: 예전 기록 1개 + 지금 이용 중
INSERT INTO usage_tbl (member_id, seat_no, check_in, check_out, used_minutes)
VALUES (3, 12, NOW() - INTERVAL 2 DAY, NOW() - INTERVAL 2 DAY + INTERVAL 240 MINUTE, 240),
       (3, 8, NOW() - INTERVAL 90 MINUTE, NULL, NULL);

-- 최지우: 지금 이용 중
INSERT INTO usage_tbl (member_id, seat_no, check_in, check_out, used_minutes)
VALUES (4, 10, NOW() - INTERVAL 150 MINUTE, NULL, NULL);
