package skeleton.run;

import skeleton.controller.StudyCafeController;

import java.util.NoSuchElementException;
import java.util.Scanner;

// 사용자 입력과 메뉴 실행을 담당한다. (제공 코드 - 수정하지 않는다)
public class Application {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StudyCafeController controller = new StudyCafeController();
            runStartMenu(scanner, controller);
        } catch (IllegalStateException e) {
            System.out.println("데이터베이스 처리 중 오류가 발생했습니다: " + e.getMessage());
            System.out.println("db.properties 설정, schema.sql 실행 여부, XML에 작성한 SQL을 확인하세요.");
            if (e.getCause() != null) {
                System.out.println("원인: " + e.getCause().getMessage());
            }
        } catch (NoSuchElementException e) {
            System.out.println("입력이 종료되어 프로그램을 종료합니다.");
        }
    }

    private static void runStartMenu(Scanner scanner, StudyCafeController controller) {
        while (true) {
            System.out.println();
            System.out.println("===== 스터디카페 좌석 예약 =====");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("9. 프로그램 종료");
            int choice = readInt(scanner, "메뉴 선택: ");

            switch (choice) {
                case 1:
                    signUp(scanner, controller);
                    break;
                case 2:
                    if (login(scanner, controller) && runMainMenu(scanner, controller)) {
                        return;
                    }
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 메뉴 번호를 입력하세요.");
            }
        }
    }

    private static void signUp(Scanner scanner, StudyCafeController controller) {
        System.out.println();
        System.out.println("===== 회원가입 =====");
        System.out.print("이름: ");
        String name = scanner.nextLine().trim();
        System.out.print("휴대폰 번호 (예: 010-1234-5678): ");
        String phone = scanner.nextLine().trim();
        controller.signUp(name, phone);
    }

    private static boolean login(Scanner scanner, StudyCafeController controller) {
        System.out.println();
        System.out.println("===== 로그인 =====");
        System.out.print("휴대폰 번호 (예: 010-1234-5678): ");
        String phone = scanner.nextLine().trim();
        return controller.login(phone);
    }

    // 프로그램을 끝내야 하면 true, 시작 메뉴로 돌아가야 하면 false를 반환한다.
    private static boolean runMainMenu(Scanner scanner, StudyCafeController controller) {
        while (true) {
            System.out.println();
            System.out.println("===== 내 스터디카페 =====");
            System.out.println("1. 빈 좌석 보기");
            System.out.println("2. 입실하기");
            System.out.println("3. 퇴실하기");
            System.out.println("4. 이용권 충전");
            System.out.println("5. 내 남은 시간 확인");
            System.out.println("6. 최근 이용 기록");
            System.out.println("7. 좌석 종류별 이용 통계");
            System.out.println("8. 로그아웃");
            System.out.println("9. 프로그램 종료");
            System.out.println("10. 회원 탈퇴");
            int choice = readInt(scanner, "메뉴 선택: ");

            switch (choice) {
                case 1:
                    controller.showEmptySeats();
                    break;
                case 2: {
                    if (!controller.showEmptySeats()) {
                        break;
                    }
                    int seatNo = readInt(scanner, "입실할 좌석 번호 (취소: 0): ");
                    if (seatNo == 0) {
                        System.out.println("입실을 취소했습니다.");
                        break;
                    }
                    controller.checkIn(seatNo);
                    break;
                }
                case 3:
                    controller.checkOut();
                    break;
                case 4: {
                    int hours = readInt(scanner, "충전할 이용권 (2 / 4 / 8시간): ");
                    controller.charge(hours);
                    break;
                }
                case 5:
                    controller.showMyRemainingTime();
                    break;
                case 6:
                    controller.showMyRecentUsages();
                    break;
                case 7:
                    controller.showMySeatTypeStats();
                    break;
                case 8:
                    controller.logout();
                    return false;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return true;
                case 10: {
                    System.out.print("정말 탈퇴하시겠습니까? 이용 기록도 모두 삭제됩니다. (y/n): ");
                    String confirmation = scanner.nextLine().trim();
                    if (!"y".equalsIgnoreCase(confirmation)) {
                        System.out.println("회원 탈퇴를 취소했습니다.");
                        break;
                    }
                    if (controller.deleteMyAccount()) {
                        return false;
                    }
                    break;
                }
                default:
                    System.out.println("올바른 메뉴 번호를 입력하세요.");
            }
        }
    }

    private static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
            }
        }
    }
}
