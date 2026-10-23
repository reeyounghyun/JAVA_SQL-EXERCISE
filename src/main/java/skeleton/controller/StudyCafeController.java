package skeleton.controller;

import skeleton.model.Member;
import skeleton.model.Seat;
import skeleton.model.SeatStat;
import skeleton.model.Usage;
import skeleton.repository.MemberRepository;
import skeleton.repository.SeatRepository;
import skeleton.repository.UsageRepository;
import skeleton.view.StudyCafeView;

import java.util.List;

// Application(입력)과 repository/view 사이의 흐름을 조정한다.
public class StudyCafeController {

    private final MemberRepository memberRepository;
    private final SeatRepository seatRepository;
    private final UsageRepository usageRepository;
    private final StudyCafeView view;

    private Member loginMember;   // 로그인한 회원 (로그아웃 상태면 null)

    public StudyCafeController() {
        memberRepository = new MemberRepository();
        seatRepository = new SeatRepository();
        usageRepository = new UsageRepository();
        view = new StudyCafeView();
    }

    /*
     * 문제 CONTROLLER-1. 회원가입
     *
     * name, phone이 null이거나 비어 있으면 "이름과 휴대폰 번호를 입력하세요."를 출력하고 끝낸다.
     * findByPhone(phone) 결과가 있으면 "이미 등록된 휴대폰 번호입니다."를 출력하고 끝낸다.
     * 중복이 없으면 Member(0, name, phone, 0)를 만들어 save()로 저장한다.
     * 성공하면 "회원가입이 완료되었습니다. 이용권을 충전한 뒤 입실하세요.",
     * 실패하면 "회원가입에 실패했습니다."를 출력한다.
     */
    public void signUp(String name, String phone) {
        // TODO: 입력값과 휴대폰 번호 중복을 확인한 뒤 신규 회원을 저장하세요.
    }

    /*
     * 문제 CONTROLLER-2. 휴대폰 번호 로그인
     *
     * findByPhone(phone)으로 회원을 조회한다.
     * 회원이 있으면 loginMember에 저장하고 아래 메시지를 출력한 뒤 true를 반환한다.
     *   "김철수님 환영합니다. 남은 시간: 5시간 0분"   (남은 시간은 view.formatMinutes() 사용)
     * 회원이 없으면 "등록되지 않은 휴대폰 번호입니다."를 출력하고 false를 반환한다.
     */
    public boolean login(String phone) {
        // TODO: 휴대폰 번호로 로그인 상태를 설정하세요.
        return false;
    }

    /*
     * 문제 CONTROLLER-3. 로그인 회원 정보 새로고침 (private 도우미 메서드)
     *
     * loginMember는 로그인할 때 DB에서 한 번 꺼내 온 "복사본"이다.
     * 충전·퇴실로 DB의 remaining_minutes가 바뀌어도 loginMember 안의 값은 저절로 바뀌지 않는다.
     * 그래서 DB 값을 바꾼 뒤에는 이 메서드를 호출해 최신 정보로 다시 채운다.
     *
     * loginMember의 휴대폰 번호로 findByPhone()을 호출해 loginMember에 다시 저장한다.
     */
    private void refreshLoginMember() {
        // TODO: DB에서 로그인 회원 정보를 다시 조회해 loginMember를 최신으로 바꾸세요.
    }

    /*
     * 문제 CONTROLLER-4. 빈 좌석 보기
     *
     * 빈 좌석 목록을 조회한다.
     * 비어 있으면 "지금은 빈 좌석이 없습니다."를 출력하고 false를 반환한다.
     * 있으면 view.displaySeats()로 출력하고 true를 반환한다.
     * (Application은 입실할 때 이 메서드가 true일 때만 좌석 번호를 입력받는다)
     */
    public boolean showEmptySeats() {
        // TODO: 빈 좌석 목록을 출력하고 빈 좌석이 있는지 반환하세요.
        return false;
    }

    /*
     * 문제 CONTROLLER-5. 입실
     *
     * 아래 순서대로 검사하고, 걸리면 메시지를 출력한 뒤 바로 끝낸다.
     *   1) 남은 시간이 0 이하   → "남은 이용 시간이 없습니다. 먼저 이용권을 충전하세요."
     *   2) 이미 입실 중         → "이미 8번 좌석에 입실 중입니다."   (findCurrentUsage 결과 활용)
     *   3) 없는 좌석 번호       → "존재하지 않는 좌석입니다."
     *   4) 점검 중인 좌석       → "점검 중인 좌석입니다."
     *   5) 다른 사람이 사용 중  → "이미 사용 중인 좌석입니다."
     * 모두 통과하면 checkIn()으로 입실 기록을 저장한다.
     * 성공하면 "7번 좌석(창가석)에 입실했습니다.", 실패하면 "입실에 실패했습니다."를 출력한다.
     */
    public void checkIn(int seatNo) {
        // TODO: 입실 조건을 순서대로 검사한 뒤 입실 기록을 저장하세요.
    }

    /*
     * 문제 CONTROLLER-6. 퇴실
     *
     * 1) findCurrentUsage()가 null이면 "입실 중인 좌석이 없습니다."를 출력하고 끝낸다.
     * 2) checkOut(이용기록ID, 회원ID)를 호출한다. 실패하면 "퇴실 처리에 실패했습니다."를 출력하고 끝낸다.
     * 3) findUsageById()로 방금 퇴실한 기록을 다시 조회해 usedMinutes(이용한 분)를 꺼낸다.
     *    (used_minutes는 DB가 계산했기 때문에 다시 조회해야 알 수 있다)
     * 4) 새 남은 시간 = 로그인 회원의 남은 시간 - 이용한 분
     *    결과가 0보다 작으면 0으로 맞추고, 넘어간 분을 기억해 둔다.
     * 5) updateRemainingMinutes()로 DB에 저장하고 refreshLoginMember()를 호출한다.
     * 6) 아래 메시지를 출력한다.
     *    "퇴실했습니다. 이용 시간: 1시간 30분 / 남은 시간: 6시간 30분"
     *    남은 시간을 넘겨 이용했다면 한 줄 더: "남은 시간보다 1시간 30분 더 이용했습니다."
     */
    public void checkOut() {
        // TODO: 퇴실을 기록하고 이용한 시간만큼 남은 시간을 줄이세요.
    }

    /*
     * 문제 CONTROLLER-7. 이용권 충전
     *
     * hours가 2, 4, 8 중 하나가 아니면 "2, 4, 8시간권만 충전할 수 있습니다."를 출력하고 끝낸다.
     * 시간을 분으로 바꿔(hours * 60) chargeMinutes()를 호출한다.
     * 성공하면 refreshLoginMember()를 호출한 뒤 아래 메시지를 출력한다.
     *   "4시간권을 충전했습니다. 남은 시간: 9시간 0분"
     * 실패하면 "충전에 실패했습니다."를 출력한다.
     */
    public void charge(int hours) {
        // TODO: 이용권 종류를 확인하고 남은 시간을 충전하세요.
    }

    /*
     * 문제 CONTROLLER-8. 내 남은 시간 확인
     *
     * refreshLoginMember()로 최신 정보를 가져온 뒤 "남은 시간: 5시간 0분"을 출력한다.
     * 지금 입실 중이면 한 줄 더 출력한다: "현재 8번 좌석을 이용 중입니다."
     */
    public void showMyRemainingTime() {
        // TODO: 최신 남은 시간과 현재 이용 중인 좌석을 출력하세요.
    }

    /*
     * 문제 CONTROLLER-9. 최근 이용 기록
     *
     * 로그인 회원의 최근 이용 기록을 조회한다.
     * 비어 있으면 "이용 기록이 없습니다."를 출력하고, 있으면 view.displayUsages()로 전달한다.
     */
    public void showMyRecentUsages() {
        // TODO: 최근 이용 기록을 화면에 전달하세요.
    }

    /*
     * 문제 CONTROLLER-10. 좌석 종류별 이용 통계
     *
     * 로그인 회원의 좌석 종류별 통계를 조회한다.
     * 비어 있으면 "퇴실까지 끝난 이용 기록이 없습니다."를 출력하고,
     * 있으면 view.displaySeatTypeStats()로 전달한다.
     */
    public void showMySeatTypeStats() {
        // TODO: 좌석 종류별 통계를 화면에 전달하세요.
    }

    /*
     * 문제 CONTROLLER-11. 회원 탈퇴
     *
     * 지금 입실 중이면 "입실 중에는 탈퇴할 수 없습니다. 먼저 퇴실하세요."를 출력하고 false를 반환한다.
     * deleteById()로 회원을 삭제한다.
     * 성공하면 loginMember를 null로 바꾸고 "회원 탈퇴가 완료되었습니다."를 출력한 뒤 true를 반환한다.
     * 실패하면 "회원 탈퇴에 실패했습니다."를 출력하고 false를 반환한다.
     */
    public boolean deleteMyAccount() {
        // TODO: 입실 여부를 확인한 뒤 회원을 삭제하고 로그인 상태를 해제하세요.
        return false;
    }

    /*
     * 문제 CONTROLLER-12. 로그아웃
     *
     * loginMember를 null로 바꾸고 "로그아웃되었습니다."를 출력한다.
     */
    public void logout() {
        // TODO: 로그인 상태를 해제하세요.
    }
}
