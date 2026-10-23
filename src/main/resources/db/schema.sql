-- 스터디카페 좌석 예약 프로그램 테이블 생성 스크립트
-- 주의: 실행하면 기존 테이블과 데이터가 모두 지워진다.

CREATE DATABASE IF NOT EXISTS studycafedb DEFAULT CHARACTER SET utf8mb4;

-- 계정 권한이 없다면 관리자 계정에서 아래 주석을 풀고 실행한다. (계정 이름은 본인 것으로)
# GRANT ALL PRIVILEGES ON studycafedb.* TO 'young'@'%';

SET NAMES utf8mb4;
USE studycafedb;

DROP TABLE IF EXISTS usage_tbl;
DROP TABLE IF EXISTS seat_tbl;
DROP TABLE IF EXISTS member_tbl;

-- 회원 (휴대폰 번호로 로그인, 이용권을 충전해 남은 시간(분)을 가진다)
CREATE TABLE member_tbl
(
    member_id         INT AUTO_INCREMENT,
    name              VARCHAR(30) NOT NULL,
    phone             VARCHAR(20) NOT NULL,
    remaining_minutes INT         NOT NULL DEFAULT 0,
    CONSTRAINT pk_member_tbl PRIMARY KEY (member_id),
    CONSTRAINT uq_member_tbl_phone UNIQUE (phone)
);

-- 좌석 (seat_type: 일반석 / 창가석 / 1인실, usable이 FALSE면 점검 중)
CREATE TABLE seat_tbl
(
    seat_no   INT,
    seat_type VARCHAR(20) NOT NULL,
    usable    BOOLEAN     NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_seat_tbl PRIMARY KEY (seat_no)
);

-- 이용 기록 (check_out이 NULL이면 아직 이용 중)
CREATE TABLE usage_tbl
(
    usage_id     INT AUTO_INCREMENT,
    member_id    INT      NOT NULL,
    seat_no      INT      NOT NULL,
    check_in     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    check_out    DATETIME NULL,
    used_minutes INT      NULL,
    CONSTRAINT pk_usage_tbl PRIMARY KEY (usage_id),
    CONSTRAINT fk_usage_tbl_member_tbl
        FOREIGN KEY (member_id) REFERENCES member_tbl (member_id)
            ON DELETE CASCADE,
    CONSTRAINT fk_usage_tbl_seat_tbl
        FOREIGN KEY (seat_no) REFERENCES seat_tbl (seat_no)
);
