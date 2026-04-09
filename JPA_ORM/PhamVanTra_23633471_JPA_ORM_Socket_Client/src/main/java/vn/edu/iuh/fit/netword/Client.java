package vn.edu.iuh.fit.netword;

import vn.edu.iuh.fit.constant.JobStatus;
import vn.edu.iuh.fit.dto.JobDTO;
import vn.edu.iuh.fit.dto.SkillDTO;
import vn.edu.iuh.fit.service.JobService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Client {

    private static final String HOST = "PhamTra";
    private static final int PORT = 3471;
    private static JobService jobService;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry(HOST, PORT);
        jobService = (JobService) registry.lookup("jobService");
        System.out.println("=== KẾT NỐI SERVER THÀNH CÔNG ===");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Nhập lựa chọn: ");
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> loadAll();
                    case 2 -> findById();
                    case 3 -> create();
                    case 4 -> update();
                    case 5 -> delete();
                    case 6 -> findBySkillInOpenJobs();
                    case 7 -> countPerJobByCompany();
                    case 0 -> {
                        System.out.println("Thoát chương trình.");
                        running = false;
                    }
                    default -> System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số.");
            }
            if (running) System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("========== MENU ==========");
        System.out.println("--- a) CRUD Job ---");
        System.out.println("  1. Xem tất cả công việc (loadAll)");
        System.out.println("  2. Tìm công việc theo ID (findById)");
        System.out.println("  3. Thêm công việc (create)");
        System.out.println("  4. Cập nhật công việc (update)");
        System.out.println("  5. Xóa công việc (delete)");
        System.out.println("--- b) Truy vấn nâng cao ---");
        System.out.println("  6. Tìm ứng viên có kỹ năng ứng tuyển vào OPEN jobs");
        System.out.println("--- c) Thống kê ---");
        System.out.println("  7. Thống kê đơn ứng tuyển theo công ty");
        System.out.println("  0. Thoát");
        System.out.println("==========================");
    }

    private static void loadAll() throws Exception {
        List<JobDTO> jobs = jobService.getAllJobs();
        if (jobs.isEmpty()) {
            System.out.println("Không có công việc nào.");
            return;
        }
        System.out.printf("%-36s %-30s %-12s %-8s%n", "ID", "Tiêu đề", "Lương", "Trạng thái");
        System.out.println("-".repeat(90));
        for (JobDTO job : jobs) {
            System.out.printf("%-36s %-30s %-12.0f %-8s%n",
                    job.getId(), job.getTitle(), job.getSalary(), job.getStatus());
        }
        System.out.println("Tổng: " + jobs.size() + " công việc.");
    }

    private static void findById() throws Exception {
        System.out.print("Nhập ID công việc: ");
        String id = scanner.nextLine().trim();
        JobDTO job = jobService.getJobById(id);
        if (job == null) {
            System.out.println("Không tìm thấy công việc với ID: " + id);
        } else {
            printJobDetail(job);
        }
    }

    private static void create() throws Exception {
        System.out.println("--- Thêm công việc mới ---");
        JobDTO job = new JobDTO();
        System.out.print("Tiêu đề: ");        job.setTitle(scanner.nextLine().trim());
        System.out.print("Mô tả: ");          job.setDescription(scanner.nextLine().trim());
        System.out.print("Lương: ");           job.setSalary(Double.parseDouble(scanner.nextLine().trim()));
        System.out.print("Trạng thái (OPEN/CLOSED): ");
        job.setStatus(JobStatus.valueOf(scanner.nextLine().trim().toUpperCase()));
        System.out.print("ID Công ty: ");      job.setCompanyId(scanner.nextLine().trim());
        JobDTO saved = jobService.saveJob(job);
        System.out.println("Thêm thành công! ID: " + saved.getId());
    }

    private static void update() throws Exception {
        System.out.print("Nhập ID công việc cần cập nhật: ");
        String id = scanner.nextLine().trim();
        JobDTO job = jobService.getJobById(id);
        if (job == null) {
            System.out.println("Không tìm thấy công việc với ID: " + id);
            return;
        }
        System.out.println("Thông tin hiện tại:");
        printJobDetail(job);
        System.out.println("--- Nhập thông tin mới (Enter để giữ nguyên) ---");

        System.out.print("Tiêu đề [" + job.getTitle() + "]: ");
        String title = scanner.nextLine().trim();
        if (!title.isEmpty()) job.setTitle(title);

        System.out.print("Mô tả [" + job.getDescription() + "]: ");
        String desc = scanner.nextLine().trim();
        if (!desc.isEmpty()) job.setDescription(desc);

        System.out.print("Lương [" + job.getSalary() + "]: ");
        String salary = scanner.nextLine().trim();
        if (!salary.isEmpty()) job.setSalary(Double.parseDouble(salary));

        System.out.print("Trạng thái [" + job.getStatus() + "] (OPEN/CLOSED): ");
        String status = scanner.nextLine().trim();
        if (!status.isEmpty()) job.setStatus(JobStatus.valueOf(status.toUpperCase()));

        jobService.saveJob(job);
        System.out.println("Cập nhật thành công!");
    }

    private static void delete() throws Exception {
        System.out.print("Nhập ID công việc cần xóa: ");
        String id = scanner.nextLine().trim();
        JobDTO job = jobService.getJobById(id);
        if (job == null) {
            System.out.println("Không tìm thấy công việc với ID: " + id);
            return;
        }
        System.out.print("Xác nhận xóa \"" + job.getTitle() + "\"? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            jobService.deleteJob(id);
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Đã hủy.");
        }
    }

    private static void findBySkillInOpenJobs() throws Exception {
        System.out.print("Nhập tên kỹ năng cần tìm: ");
        String skill = scanner.nextLine().trim().toLowerCase();

        List<JobDTO> allJobs = jobService.getAllJobs();
        List<JobDTO> matched = allJobs.stream()
                .filter(j -> j.getStatus() == JobStatus.OPEN)
                .filter(j -> j.getSkills() != null && j.getSkills().stream()
                        .map(SkillDTO::getName)
                        .anyMatch(s -> s.toLowerCase().contains(skill)))
                .toList();

        if (matched.isEmpty()) {
            System.out.println("Không tìm thấy công việc OPEN nào yêu cầu kỹ năng: " + skill);
            return;
        }
        System.out.printf("%-36s %-30s %-20s%n", "Job ID", "Tiêu đề công việc", "Kỹ năng yêu cầu");
        System.out.println("-".repeat(88));
        for (JobDTO job : matched) {
            String skills = job.getSkills().stream()
                    .map(SkillDTO::getName)
                    .reduce("", (a, b) -> a.isEmpty() ? b : a + ", " + b);
            System.out.printf("%-36s %-30s %-20s%n", job.getId(), job.getTitle(), skills);
        }
        System.out.println("Tổng: " + matched.size() + " công việc OPEN phù hợp.");
    }

    private static void countPerJobByCompany() throws Exception {
        System.out.print("Nhập tên công ty: ");
        String companyName = scanner.nextLine().trim();

        List<JobDTO> jobs = jobService.getJobsByCompany(companyName);
        if (jobs.isEmpty()) {
            System.out.println("Không tìm thấy công việc nào của công ty: " + companyName);
            return;
        }
        System.out.printf("%-36s %-30s %-10s%n", "Job ID", "Tiêu đề công việc", "Trạng thái");
        System.out.println("-".repeat(78));
        for (JobDTO job : jobs) {
            System.out.printf("%-36s %-30s %-10s%n",
                    job.getId(), job.getTitle(), job.getStatus());
        }
        System.out.println("Tổng: " + jobs.size() + " vị trí tuyển dụng của \"" + companyName + "\".");
    }

    private static void printJobDetail(JobDTO job) {
        System.out.println("  ID        : " + job.getId());
        System.out.println("  Tiêu đề   : " + job.getTitle());
        System.out.println("  Mô tả     : " + job.getDescription());
        System.out.println("  Lương     : " + job.getSalary());
        System.out.println("  Trạng thái: " + job.getStatus());
        System.out.println("  Công ty   : " + job.getCompanyName() + " (" + job.getCompanyId() + ")");
        if (job.getSkills() != null && !job.getSkills().isEmpty()) {
            System.out.print("  Kỹ năng   : ");
            job.getSkills().forEach(s -> System.out.print(s.getName() + " "));
            System.out.println();
        }
    }
}
