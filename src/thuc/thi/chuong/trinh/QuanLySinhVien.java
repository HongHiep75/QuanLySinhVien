package thuc.thi.chuong.trinh;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import danh.sach.DanhSachBangDiem;
import danh.sach.DanhSachMon;
import danh.sach.DanhSachSinhVien;
import model.BangDiem;
import model.MonHoc;
import model.SinhVien;
import quan.ly.file.FileIODanhSachBangDiem;
import quan.ly.file.FileIODanhSachMonHoc;
import quan.ly.file.FileIODanhSachSinhVien;

public class QuanLySinhVien {
	public static void main(String[] args) {
		showMenu();
	}

	public static void menu() {

		System.out.println("Vui lòng chọn chức năng(nhập số):");
		System.out.println("1. Nhập sinh viên mới ");
		System.out.println("2. Nhập môn học mới ");
		System.out.println("3. Nhập điểm cho sinh viên");
		System.out.println("4. Sắp xếp danh sách bảng điểm");
		System.out.println("5. Tính điểm ");
		System.out.println("0.Thoát");
	}

	public static void showMenu() {
		Scanner sc = new Scanner(System.in);
		DanhSachSinhVien danhSachSinhVien = new DanhSachSinhVien();
		DanhSachMon danhSachMon = new DanhSachMon();
		DanhSachBangDiem danhSachBangDiem = new DanhSachBangDiem();
		int luaChon;
		// vong lap chon chuc nang
		System.out.println("Chào mừng bạn tới ứng dụng quản sinh viên\n");
		do {
			menu();
			luaChon = kiemTraDauVaoInt(sc);
			while (luaChon > 5) {
				System.out.println("Chọn lại chức năng:");
				luaChon = sc.nextInt();
			}

			switch (luaChon) {
			// them sinh vien
			case 1: {
				danhSachSinhVien = FileIODanhSachSinhVien.readFileDanhSachSinhVien();
				// có sự cố khi đọc file thì dừng thực thi
				if (danhSachSinhVien == null) {
					System.out.println("Có sự cố khi đọc file vui lòng thử lại hoặc kiểm tra");
					danhSachSinhVien = new DanhSachSinhVien();
					break;
				}
				danhSachSinhVien.taoDanhSachSinhVien(sc);
			}
				break;
			// them mon hoc
			case 2: {
				danhSachMon = FileIODanhSachMonHoc.readFileDanhSachMonHoc();
				// có sự cố khi đọc file thì dừng thực thi
				if (danhSachMon == null) {
					System.out.println("Có sự cố khi đọc file vui lòng thử lại hoặc kiểm tra");
					danhSachSinhVien = new DanhSachSinhVien();
					break;
				}
				danhSachMon.taoMonHocMoi(sc);
			}
				break;
			// them bang diem
			case 3: {
				danhSachSinhVien = FileIODanhSachSinhVien.readFileDanhSachSinhVien();
				danhSachMon = FileIODanhSachMonHoc.readFileDanhSachMonHoc();
				if (danhSachMon == null || danhSachSinhVien == null) {
					System.out.println("Có sự cố khi đọc file vui lòng thử lại hoặc kiểm tra");
					danhSachSinhVien = new DanhSachSinhVien();
					danhSachSinhVien = new DanhSachSinhVien();
					break;
				}
				danhSachBangDiem = FileIODanhSachBangDiem.readFileDanhSachBangDiem();
				if (danhSachBangDiem == null) {
					System.out.println("Có sự cố khi đọc file vui lòng thử lại hoặc kiểm tra");
					danhSachBangDiem = new DanhSachBangDiem();
					break;
				}
				danhSachBangDiem.themmBangDiem(sc, danhSachSinhVien, danhSachMon);

			}
				break;
			// sap xep bang diem
			case 4: {
				danhSachBangDiem.sapXep(sc);
			}
				break;
			// tinh diem hoc phan
			case 5: {
				danhSachBangDiem.hienThiDiem();
			}
				break;
			}
		} while (luaChon != 0);

	}

	public static double kiemTraDiem(Scanner sc) {
		double input = 0;
		// kiem tra thong tin nhap vao
		input = kiemTraDauVaoDouble(sc);
		while (input < 0 || input > 10) {
			System.out.println("Điểm không lớn hơn 10 và nhỏ hơn 0");
			input = kiemTraDauVaoDouble(sc);
		}
		return input;
	}

//   sử lý ngoại lệ nhập chữ vào ô số
	public static int kiemTraDauVaoInt(Scanner sc) {
		int input = 0;
		// kiem tra thong tin nhap vao
		do {
			try {
				input = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Vui lòng nhập lại số");
			}
			sc.nextLine();
		} while (input == 0);
		return input;

	}

	// kiem tra dau vao String
	public static String kiemTraDauVaoString(Scanner sc) {
		Pattern p = Pattern.compile("[^a-zA-z]$");
		String input = sc.nextLine();
		while (p.matcher(input).find()) {
			System.out.println("Thông tin không chính xác vui lòng nhập lại chỉ được nhập chữ");
			input = sc.nextLine();
		}
		return input;

	}

	// kiem tra input double
	public static double kiemTraDauVaoDouble(Scanner sc) {
		double input = 0;
		// kiem tra thong tin nhap vao
		do {
			try {
				input = sc.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("Vui lòng nhập lại số");
			}
			sc.nextLine();
		} while (input == 0);
		return input;

	}

	// kiem tra so dien thoai
	public static String kiemTraSoDienThoai(Scanner sc) {
		String soDienThoai = null;
		soDienThoai = sc.nextLine();
		Pattern p = Pattern.compile("^[0-9]{10}$");
		Pattern p2 = Pattern.compile("^[0-9]{11}$");
		while (!(p.matcher(soDienThoai + "").find() || p2.matcher(soDienThoai + "").find())) {
			System.out.println("Số điện thoại không đúng (số trong khoảng 10 tới 11 số)");
			soDienThoai = sc.nextLine();
		}
		return soDienThoai;
	}

}
